package com.adv.anno1800helper.helpers;

public class Consumption
{
    private String name = "";
    private double tonnage = 0.0;

    public Consumption(String name, double tonnage)
    {
        this.name = name;
        this.tonnage = tonnage;
    }

    public void addTonnage(double tonnage)
    {
        this.tonnage += tonnage;
    }

    public double getTonnage()
    {
        return tonnage;
    }

    public String getName()
    {
        return name;
    }
}
