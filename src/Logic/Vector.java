package Logic;

import Miscellaneous.MathExt;
import Miscellaneous.Operators;

/**
 * Contains logic for a {@link Vector} in 2 dimensions.
 */
public class Vector
{
    private double x;
    private double y;



    /**
     * Constructs a new {@link Vector} using the values passed in to the constructor.
     * @param x X-component to be used.
     * @param y Y-component to be used.
     */
    public Vector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }


    /**
     * Used in order to avoid reference.
     * @return A clone of this vector
     */
    public Vector clone()
    {
        return new Vector(getX(), getY());
    }



    /**
     * Used to perform an operation (+, -, *, /) on two vectors that does not change the value of any of the involved vectors.
     * @param a First vector, will be used first if operation is order specific.
     * @param b Second vector, will be used second if operation is order specific. If any of the components of b is 0 division operation will not be performed.
     * @param operator The type of operator to use.
     * @return Result of the operation as a {@link Vector}.
     */
    public static Vector operator(Vector a, Vector b, Operators operator)
    {
        Vector result = new Vector(0, 0);

        switch (operator)
        {
            case ADDITION:
                result = new Vector(a.getX() + b.getX(), a.getY() + b.getY());
                break;

            case SUBTRACTION:
                result = new Vector(a.getX() - b.getX(), a.getY() - b.getY());
                break;

            case MULTIPLICATION:
                result = new Vector(a.getX() * b.getX(), a.getY() * b.getY());
                break;

            case DIVISION:
                if (b.getX() != 0 && b.getY() != 0)
                {
                    result = new Vector(a.getX() / b.getX(), a.getY() / b.getY());
                }
                break;
        }

        return result;
    }

    /**
     * Calculates the distance between two vectors.
     * @param a First {@link Vector}.
     * @param b Second {@link Vector}.
     * @return Distance between the two vectors.
     */
    public static double calculateDistance(Vector a, Vector b)
    {
        return Vector.operator(a, b, Operators.SUBTRACTION).getMagnitude();
    }

    /**
     * Calculates the dot-product of two vectors. As a projected on b.
     * @param a First {@link Vector}.
     * @param b Second {@link Vector}.
     * @return Dot-product between the two vectors.
     */
    public static double calculateDotProduct(Vector a, Vector b)
    {
        return (a.getX() * b.unit().getX()) + (a.getY() * b.unit().getY());
    }

    /**
     * Calculates the angle between two vectors.
     * @param a First {@link Vector}.
     * @param b Second {@link Vector}.
     * @return Angle between the two vectors.
     */
    public static double calculateAngle(Vector a, Vector b)
    {
        return Math.acos(calculateDotProduct(a.unit(), b.unit()));
    }

    /**
     * Returns the longer of two vectors
     * @param a First {@link Vector}.
     * @param b Second {@link Vector}.
     * @return The longer one of two vectors.
     */
    public static Vector max(Vector a, Vector b)
    {
        return a.getMagnitude() >= b.getMagnitude() ? a : b;
    }

    /**
     * Returns the shorter of two vectors.
     * @param a First {@link Vector}.
     * @param b Second {@link Vector}.
     * @return The shorter one of the two vectors.
     */
    public static Vector min(Vector a, Vector b)
    {
        return a.getMagnitude() <= b.getMagnitude() ? a : b;
    }

    /**
     * Returns a {@link Vector} that goes from one point to another.
     * @param from Starting point to go from.
     * @param to End point to go to.
     * @return The direction from the start to the end.
     */
    public static Vector direction(Vector from, Vector to)
    {
        return operator(from, to, Operators.SUBTRACTION);
    }



    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }



    /**
     * Gives the magnitude of this {@link Vector}.
     * @return Magnitude of this {@link Vector}.
     */
    public double getMagnitude()
    {
        return MathExt.pythagorasC(getX(), getY());
    }


    /**
     * Adds another {@link Vector} to this one.
     * @param other The {@link Vector} to add with.
     * @return This vector after addition.
     */
    public Vector add(Vector other)
    {
        setX(getX() + other.getX());
        setY(getY() + other.getY());

        return this;
    }

    /**
     * Subtracts another {@link Vector} from this one.
     * @param other The {@link Vector} to subtract with.
     * @return This vector after subtraction.
     */
    public Vector subtract(Vector other)
    {
        setX(getX() - other.getX());
        setY(getY() - other.getY());

        return this;
    }

    /**
     * Multiplies this with another {@link Vector}.
     * @param other The {@link Vector} to multiply with.
     * @return This vector after multiplication.
     */
    public Vector multiply(Vector other)
    {
        setX(getX() * other.getX());
        setY(getY() * other.getY());

        return this;
    }

    /**
     * Divides this with another {@link Vector}, if and only if the other vector's components are not 0.
     * @param other The {@link Vector} to divide with.
     * @return This vector after division.
     */
    public Vector divide(Vector other)
    {
        if (other.getX() != 0 && other.getY() != 0)
        {
            setX(getX() / other.getX());
            setY(getY() / other.getY());
        }

        return this;
    }

    /**
     * Scales this {@link Vector}
     * @param value
     * @return
     */
    public Vector scale(double value)
    {
        setX(getX() * value);
        setY(getY() * value);

        return this;
    }

    /**
     * Normalizes this vector.
     * @return This {@link Vector} after normalization.
     */
    public Vector normalize()
    {
        double magnitude = getMagnitude();

        if (magnitude != 0)
        {
            setX(getX() / magnitude);
            setY(getY() / magnitude);
        }

        return this;
    }

    /**
     * Gives the unit vector of this vector without changing this vector
     * @return This vectors unit vector
     */
    public Vector unit()
    {
        return this.clone().normalize();
    }

    /**
     * Rotates this vector around the point (0, 0) by an angle.
     * @param angle Amount to rotate
     * @return this vector after rotation
     */
    public Vector rotate(double angle)
    {
        double oldX = getX();
        double oldY = getY();

        setX((oldX * Math.cos(angle)) - (oldY * Math.sin(angle)));
        setY((oldX * Math.sin(angle)) + (oldY * Math.cos(angle)));

        return this;
    }



    @Override
    public String toString()
    {
        return "(" + getX() + ", " + getY() + ")";
    }

    @Override
    public boolean equals(Object other)
    {
        if (other instanceof Vector)
        {
            return getX() == ((Vector)other).getX() && getY() == ((Vector)other).getY();
        }

        return false;
    }
}
