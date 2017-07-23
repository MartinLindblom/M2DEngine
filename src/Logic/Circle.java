package Logic;

import java.util.ArrayList;
import java.util.List;

public class Circle
{
    private double radius;
    private Transform transform;



    public Circle(double radius, Vector position)
    {
        this(radius, position, null);
    }

    public Circle(double radius, Vector position, Transform parent)
    {
        this.radius = radius;
        transform = new Transform(parent);
        transform.setPosition(position.clone());
    }



    public double getRadius()
    {
        return radius * transform.getScale().getX();
    }

    public Vector getPosition()
    {
        return transform.getPosition();
    }

    public Vector getAxis(Polygon p)
    {
        Vector closest = new Vector(Double.MAX_VALUE, Double.MAX_VALUE);

        for (Vector vertex : p.getVertices())
        {
            double distance = Vector.calculateDistance(getPosition(), vertex);

            closest = distance < closest.getMagnitude() ? vertex : closest;
        }

        return closest.unit();
    }



    public static OverlappingInfo overlaps(Circle a, Circle b)
    {
        double distance = Vector.calculateDistance(a.getPosition(), b.getPosition());

        if (distance < a.getRadius() + b.getRadius())
        {
            return new OverlappingInfo(true, new Vector(0, 0));
        }

        return new OverlappingInfo(false, new Vector(0, 0));
    }

    public static OverlappingInfo overlaps(Circle a, Polygon b)
    {
        List<Vector> allAxes = new ArrayList<>();
        allAxes.add(a.getAxis(b));
        allAxes.addAll(b.getNormals());

        List<Vector> verticesB = new ArrayList<>();
        verticesB.addAll(b.getVertices());



        Vector mtv = new Vector(Double.MAX_VALUE, Double.MAX_VALUE);

        for (Vector axis : allAxes)
        {
            double minA = Vector.calculateDotProduct(a.getPosition(), axis) - a.getRadius();
            double maxA = Vector.calculateDotProduct(a.getPosition(), axis) + a.getRadius();
            double minB = Double.MAX_VALUE;
            double maxB = -Double.MAX_VALUE;

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
