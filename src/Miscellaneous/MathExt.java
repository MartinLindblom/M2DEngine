package Miscellaneous;

public class MathExt
{
    public static double pythagorasC(double a, double b)
    {
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

    public static double map(double inStart, double inEnd, double outStart, double outEnd, double value)
    {
        value = clamp(inStart, inEnd, value);

        double k = (outEnd - outStart) / (inEnd - inStart);
        double m = outStart - (inStart * k);

        return (k * value) + m;
    }

    public static double clamp(double firstLimit, double secondLimit, double value)
    {
        double lowerLimit = firstLimit < secondLimit ? firstLimit : secondLimit;
        double upperLimit = firstLimit < secondLimit ? secondLimit : firstLimit;

        if (value < lowerLimit)
        {
            return lowerLimit;
        }
        else if (value > upperLimit)
        {
            return upperLimit;
        }

        return value;
    }

    public static double toRadians(double degrees)
    {
        return degrees * (Math.PI / 180.0);
    }

    public static double toDegrees(double radians)
    {
        return radians * (180.0 / Math.PI);
    }
}
