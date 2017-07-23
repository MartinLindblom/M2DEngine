package Logic;

import Miscellaneous.MathExt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Polygon
{
    private List<Vector> vertices;

    private Transform transform;



    public Polygon(List<Vector> vertices)
    {
        this(vertices, null);
    }

    public Polygon(List<Vector> vertices, Transform parent)
    {
        this.vertices = vertices;
        transform = new Transform(parent);
    }



    public static Polygon rectangle(double width, double height)
    {
        return rectangle(width, height, null);
    }

    public static Polygon rectangle(double width, double height, Transform parent)
    {
        return new Polygon(Arrays.asList(new Vector(-width / 2.0, height / 2.0), new Vector(width / 2.0, height / 2.0), new Vector(width / 2.0, -height / 2.0), new Vector(-width / 2.0, -height / 2.0)), parent);
    }



    public List<Vector> getVertices()
    {
        List<Vector> v = new ArrayList<>();

        for (Vector vertex : vertices)
        {
            Vector tmp = vertex.clone();

            tmp.multiply(transform.getScale());
            tmp.rotate(transform.getRotation());
            tmp.add(transform.getPosition());

            v.add(tmp);
        }

        return v;
    }

    public List<Vector> getNormals()
    {
        List<Vector> translatedVertices = getVertices();
        List<Vector> normals = new ArrayList<>();

        int nextVertex = 1;
        for (int i = 0; i < translatedVertices.size(); i++)
        {
            Vector normal = Vector.direction(translatedVertices.get(i), translatedVertices.get(nextVertex)).rotate(MathExt.toRadians(90)).unit();

            boolean isUnique = true;
            for (Vector v : normals)
            {
                double angle = Vector.calculateAngle(normal, v);
                if (MathExt.toDegrees(angle) == 0.0 || MathExt.toDegrees(angle) == 180)
                {
                    isUnique = false;
                }
            }

            if (isUnique)
            {
                normals.add(normal);
            }

            nextVertex = nextVertex < (getVertices().size() - 1) ? nextVertex + 1 : 0;
        }

        return normals;
    }



    public static OverlappingInfo overlaps(Polygon a, Polygon b)
    {
        List<Vector> allAxes = new ArrayList<>();

        allAxes.addAll(a.getNormals());
        allAxes.addAll(b.getNormals());

        List<Vector> verticesA = new ArrayList<>();
        List<Vector> verticesB = new ArrayList<>();
        verticesA.addAll(a.getVertices());
        verticesB.addAll(b.getVertices());



        Vector mtv = new Vector(Double.MAX_VALUE, Double.MAX_VALUE);

        for (Vector axis : allAxes)
        {
            double minA = Double.MAX_VALUE;
            double maxA = -Double.MAX_VALUE;
            double minB = Double.MAX_VALUE;
            double maxB = -Double.MAX_VALUE;

            for (Vector vertex : verticesA)
            {
                double projection = Vector.calculateDotProduct(vertex, axis);

                minA = projection < minA ? projection : minA;
                maxA = projection > maxA ? projection : maxA;
            }

            for (Vector vertex : verticesB)
            {
                double projection = Vector.calculateDotProduct(vertex, axis);

                minB = projection < minB ? projection : minB;
                maxB = projection > maxB ? projection : maxB;
            }

            if (maxA <= minB || maxB <= minA)
            {
                return new OverlappingInfo(false, new Vector(0, 0));
            }
            else
            {
                double projectionOverlap = Math.min(maxA - minB, maxB - minA);
                double direction = maxA < maxB ? -1 : 1;

                Vector translationVector = axis.clone().scale(projectionOverlap * direction);

                mtv = Vector.min(translationVector, mtv);
            }
        }

        return new OverlappingInfo(true, mtv);
    }
}
