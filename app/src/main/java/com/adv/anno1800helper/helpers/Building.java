package com.adv.anno1800helper.helpers;

import java.util.ArrayList;

public class Building
{
    private String name;
    private int productionTime;
    private String firstRequiredResource;
    private String secondRequiredResource;
    private int requiresElectricity;

    /***********************************************************************************************
     *
     *  A class that represents a building type
     *
     **********************************************************************************************/

    public Building(String name, int productionTime, String firstRequiredResource,
                    String secondRequiredResource, int requiresElectricity)
    {
        setName(name);
        setProductionTime(productionTime);
        setFirstRequiredResource(firstRequiredResource);
        setSecondRequiredResource(secondRequiredResource);
        setRequiresElectricity(requiresElectricity);
    }

    public Building()
    {

    }

    protected void setName(String name)
    {
        this.name = name;
    }

    protected void setProductionTime(int productionTime)
    {
        this.productionTime = productionTime;
    }

    protected void setFirstRequiredResource(String firstRequiredResource)
    {
        this.firstRequiredResource = firstRequiredResource;
    }

    protected void setSecondRequiredResource(String secondRequiredResource)
    {
        this.secondRequiredResource = secondRequiredResource;
    }

    protected void setRequiresElectricity(int requiresElectricity)
    {
        this.requiresElectricity = requiresElectricity;
    }

    public String getName()
    {
        return name;
    }

    public int getProductionTime()
    {
        return productionTime;
    }

    public String getFirstRequiredResource()
    {
        return firstRequiredResource;
    }

    public String getSecondRequiredResource()
    {
        return secondRequiredResource;
    }

    public int getRequiresRelectricity()
    {
        return requiresElectricity;
    }
}
