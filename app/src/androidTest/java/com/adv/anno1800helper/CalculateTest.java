package com.adv.anno1800helper;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CalculateTest
{
    private static double roundNumbers(double numbers, int roundBy)
    {
        String decimalPlace = "#.#";

        for(int i=1; i<roundBy; i++)
        {
            decimalPlace += "#";
        }

        DecimalFormat df = new DecimalFormat(decimalPlace);
        df.setRoundingMode(RoundingMode.CEILING);

        System.out.println(df.format(numbers));

        return Double.parseDouble(df.format(numbers));
    }

    public static void main(String[] args)
    {
        System.out.println(roundNumbers(1234.56789, 4));
    }

}
