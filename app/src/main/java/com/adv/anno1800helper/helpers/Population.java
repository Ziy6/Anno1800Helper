package com.adv.anno1800helper.helpers;

import java.util.ArrayList;

/***************************************************************************************************
 *
 * A class that represents a population type
 *
 **************************************************************************************************/

public class Population
{
    private String name;
    private ArrayList<PopulationBuilding> resourceNeeds;

    public Population(String name, ArrayList<PopulationBuilding> resourceNeeds)
    {
        this.name = name;
        this.resourceNeeds = resourceNeeds;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<PopulationBuilding> getResourceNeeds()
    {
        return resourceNeeds;
    }
}
