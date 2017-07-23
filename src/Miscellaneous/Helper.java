package Miscellaneous;

import java.util.ArrayList;
import java.util.List;

public class Helper
{
    public static List<Double> createListOfDoubles(double value, int numberOf)
    {
        List<Double> values = new ArrayList<>();

        for (int i = 0; i < numberOf; i++)
        {
            values.add(value);
        }

        return values;
    }
}
