package Logic;

import Miscellaneous.Operators;

/**
 * Contains the logic concerning the position, scale and rotation of a object.
 */
public class Transform
{
    private Transform parent;

    private Vector position;
    private Vector scale;
    private double rotation;



    public Transform()
    {
        this(null);
    }

    public Transform(Transform parent)
    {
        this.parent = parent;
        position = new Vector(0,0);
        scale = new Vector(1,1);
        rotation = 0;
    }



    public Transform getParent()
    {
        return parent;
    }

    public void setParent(Transform parent)
    {
        this.parent = parent;
    }



    public Vector getPosition()
    {
        if (getParent() != null)
        {
            return Vector.operator(position.clone().rotate(getParent().getRotation()).multiply(getParent().getScale()), getParent().getPosition(), Operators.ADDITION);
        }

        return position;
    }

    public Vector getLocalPosition()
    {
        return position;
    }

    public void setPosition(Vector position)
    {
        if (getParent() != null)
        {
            this.position = Vector.direction(getParent().getPosition(), position).rotate(-getParent().getRotation());
        }

        this.position = position;
    }

    public void setLocalPosition(Vector position)
    {
        this.position = position;
    }



    public Vector getScale()
    {
        if (getParent() != null)
        {
            return Vector.operator(getParent().getScale(), scale, Operators.MULTIPLICATION);
        }

        return scale;
    }

    public Vector getLocalScale()
    {
        return scale;
    }

    public void setScale(Vector scale)
    {
        if (getParent() != null)
        {
            this.scale = Vector.operator(scale, getParent().getScale(), Operators.DIVISION);
        }

        this.scale = scale;
    }

    public void setLocalScale(Vector scale)
    {
        this.scale = scale;
    }



    public double getRotation()
    {
        if (getParent() != null)
        {
            return getParent().getRotation() + rotation;
        }

        return rotation;
    }

    public double getLocalRotation()
    {
        return rotation;
    }

    public void setRotation(double rotation)
    {
        if (getParent() != null)
        {
            this.rotation = rotation - getParent().getRotation();
        }

        this.rotation = rotation;
    }

    public void setLocalRotation(double rotation)
    {
        this.rotation = rotation;
    }



    public void translate(Vector translation)
    {
        position.add(translation);
    }

    public void scale(Vector factor)
    {
        scale.multiply(factor);
    }

    public void rotate(double amount)
    {
        rotation += amount;
    }
}
