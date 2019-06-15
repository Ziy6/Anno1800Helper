package com.adv.anno1800helper.helpers;

/***************************************************************************************************
 *
 *  A class that represents a building type with a consumption rate for a given population
 *
 **************************************************************************************************/

public class PopulationBuilding extends Building
{
    private double consumptionRate;

    public PopulationBuilding(String name, int productionTime, String firstRequiredResource,
                              String secondRequiredResource, double consumptionRate,
                              int requiresElectricity)
    {
        super(name, productionTime, firstRequiredResource, secondRequiredResource,
                requiresElectricity);

        this.consumptionRate = consumptionRate;
    }

    //second constructor that takes values from Building object which class it is extended from
    public PopulationBuilding(Building building, double consumptionRate)
    {
        super(building.getName(), building.getProductionTime(), building.getFirstRequiredResource(),
                building.getSecondRequiredResource(), building.getRequiresRelectricity());

        this.consumptionRate = consumptionRate;
    }

    public double getConsumptionRate()
    {
        return consumptionRate;
    }
}
