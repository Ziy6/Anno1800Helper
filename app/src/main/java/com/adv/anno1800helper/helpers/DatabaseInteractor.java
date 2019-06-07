package com.adv.anno1800helper.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**************************************************************************************************
 *
 * A class which methods interract with the Anno1800 database in the android device
 *
 *************************************************************************************************/

public class DatabaseInteractor
{
    private final String DATABASE_NAME = "Anno1800DataBase.db";
    private Database anno1800DB;
    private SQLiteDatabase sql;
    private Context context;

    public DatabaseInteractor(Context context)
    {
        this.context = context;
    }

    private void openDatabase()
    {
        sql = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
    }

    private void closeDatabase()
    {
        sql.close();
    }

    private boolean needsReplacing(Database database)
    {
        openDatabase();

        if(database.getVersionNumber()==0)
        {
            closeDatabase();
            return true;
        }
        else if(isDBVersionNew())
        {
            closeDatabase();
            return true;
        }
        else
        {
            closeDatabase();
            return false;
        }
    }

    public void initializeDatabase()
    {
        anno1800DB = new Database();

        if(needsReplacing(anno1800DB))
        {
            openDatabase();
            //drop all tables
            sql.execSQL("DROP TABLE IF EXISTS version");
            sql.execSQL("DROP TABLE IF EXISTS population");
            sql.execSQL("DROP TABLE IF EXISTS building");
            addVersionTable();
            openDatabase();

            //recreating tables for population and buildings
            anno1800DB.initializeDatabase();
            ArrayList<Population> populationList = anno1800DB.getPopulationList();
            ArrayList<Building> buildingList = anno1800DB.getBuildingList();

            addPopulationTable(populationList);
            addBuildingTable(buildingList);
        }
    }

    private void addVersionTable()
    {
        openDatabase();

        //recreating version table containing the database's version number
        sql.execSQL("CREATE TABLE version (version_number INTEGER)");
        sql.execSQL("INSERT INTO version (version_number) VALUES (" + anno1800DB.getVersionNumber() + ")");

        closeDatabase();
    }

    private void addPopulationTable(ArrayList<Population> populationList)
    {
        openDatabase();

        //get list of unique resource names that are needed from the entire population
        ArrayList<String> populationResourceList = getPopulationNeedsList(populationList);

        //set up table column names and data types for the population table
        //CREATE TABLE [tableName] ([colmnId] [columnDataTypes]) , ......
        String createPopTableCommand = "CREATE TABLE population (name TEXT";

        //loops through
        for(int i=0; i<populationResourceList.size(); i++)
        {
            createPopTableCommand += ", " + populationResourceList.get(i) + " TEXT";
        }

        createPopTableCommand += ")";

        sql.execSQL(createPopTableCommand);

        //inserts values to the table for all rows
        //INSERT INTO [table_name] ([column_name1] , [column_name2]) VALUES ([row1_col1] , [row1_col2])
        //loops through list of population to insert values for each row, one for each population type8
        for(int i=0; i<populationList.size(); i++)
        {
            String insertPopValuesCommand = "INSERT INTO population VALUES ('" +
                    populationList.get(i).getName() + "'";

            //loop through list of resource needs used following column order
            for(int j=0; j<populationResourceList.size(); j++)
            {
                int matchIndex = -1;

                //loops through all resource needs for each population
                for(int k=0; k<populationList.get(i).getResourceNeeds().size(); k++)
                {
                    //if a match for the column type, save index of current population resource needs list
                    if(populationList.get(i).getResourceNeeds().get(k).getName().equals(populationResourceList.get(j)))
                    {
                        matchIndex = k;
                    }
                }

                if(matchIndex>=0)
                {
                    insertPopValuesCommand += ", '" +
                            populationList.get(i).getResourceNeeds().get(matchIndex).getConsumptionRate() +
                            "'";
                }
                else
                {
                    insertPopValuesCommand += ", '0.0'";
                }
            }
            insertPopValuesCommand += ")";
            sql.execSQL(insertPopValuesCommand);
        }
        closeDatabase();
    }

    private void addBuildingTable(ArrayList<Building> buildingList)
    {
        openDatabase();

        //setup column names and value types in building table
        //CREATE TABLE [tableName] ([colmnId] [columnDataTypes]) , ......
        String createBuildingTableCommand = "CREATE TABLE building (name TEXT, production_time INTEGER, " +
        "first_required_resource TEXT, second_required_resource TEXT, requires_electricity INTEGER)";

        sql.execSQL(createBuildingTableCommand);

        //Insert row values of buildings from a building list
        //INSERT INTO [table_name] ([column_name1] , [column_name2]) VALUES ([row1_col1] , [row1_col2])
        for(int i=0; i<buildingList.size(); i++)
        {
            String insertBuildingTableCommand = "INSERT INTO building VALUES (" +
                    "'"  + buildingList.get(i).getName() + "'" +
                    ", " + buildingList.get(i).getProductionTime() +
                    ", '" + buildingList.get(i).getFirstRequiredResource() + "'" +
                    ", '" + buildingList.get(i).getSecondRequiredResource() + "'" +
                    ", " + buildingList.get(i).getRequiresRelectricity() + ")";

            sql.execSQL(insertBuildingTableCommand);
        }
        closeDatabase();
    }

    private boolean isDBVersionNew()
    {
        openDatabase();

        boolean isNew = false;
        Cursor query = sql.rawQuery("SELECT version_number FROM version;", null);

        if(query.moveToFirst())
        {
            int databaseVersion = query.getInt(0);

            if (anno1800DB.getVersionNumber() > databaseVersion)
            {
                isNew = true;
            }
        }
        query.close();
        closeDatabase();
        return isNew;
    }

    private ArrayList<String> getPopulationNeedsList(ArrayList<Population> populationList)
    {
        ArrayList<String> returnedNeedsList = new ArrayList<>();

        //get first population object in list
        for(int x=0; x<populationList.size(); x++)
        {
            ArrayList<PopulationBuilding> populationResourceNeedsList = populationList.get(x).getResourceNeeds();

            //loop through the list of needed resources for each population type
            for(int i=0; i<populationResourceNeedsList.size(); i++)
            {
                //if resource not already in list, add to list
                if(!returnedNeedsList.contains(populationResourceNeedsList.get(i).getName()))
                {
                    returnedNeedsList.add(populationResourceNeedsList.get(i).getName());
                }
            }
        }
        return returnedNeedsList;
    }

    public Population getPopulation(String populationName)
    {
        ArrayList<PopulationBuilding> populationBuildingList = new ArrayList<>();
        openDatabase();

        //get row
        Cursor popQuery= sql.rawQuery("SELECT * FROM population WHERE name = '" +
                populationName + "'", null);

        //loop through the columns of a row
        for(int i=0; i<popQuery.getColumnCount(); i++)
        {
            if(popQuery.moveToFirst() && i!=0)
            {
                if(!popQuery.isNull(i))
                {
                    Building building = getBuiliding(popQuery.getColumnName(i));
                    double consumptionRate = Double.parseDouble(popQuery.getString(i));

                    populationBuildingList.add(new PopulationBuilding(building, consumptionRate));
                }
            }

        }

        popQuery.close();
        closeDatabase();

        return new Population(populationName, populationBuildingList);
    }

    public Building getBuiliding(String buildingName)
    {
        openDatabase();

        int productionTime = 0;
        String firstRequiredResource = "";
        String secondRequiredResource = "";
        int requiresElectricity = 0;

        //getting all variables for a building object
        Cursor productionTimeQuery = sql.rawQuery("SELECT production_time FROM building " +
                "WHERE name = '" + buildingName + "';", null);

        Cursor firstRequiredResourceQuery = sql.rawQuery("SELECT first_required_resource " +
                "FROM building WHERE name = '" + buildingName + "';", null);

        Cursor secondRequiredResourceQuery = sql.rawQuery("SELECT second_required_resource " +
                "FROM building WHERE name = '" + buildingName + "';", null);

        Cursor requireElectricityQuery = sql.rawQuery("SELECT requires_electricity " +
                "FROM building WHERE name = '" + buildingName + "';", null);

        if(productionTimeQuery.moveToFirst() && firstRequiredResourceQuery.moveToFirst() &&
                secondRequiredResourceQuery.moveToFirst() && requireElectricityQuery.moveToFirst())
        {
            if(!productionTimeQuery.isNull(0) &&
                    !firstRequiredResourceQuery.isNull(0) &&
                    !secondRequiredResourceQuery.isNull(0) &&
                    !requireElectricityQuery.isNull(0))
            {
                productionTime = productionTimeQuery.getInt(0);
                firstRequiredResource = firstRequiredResourceQuery.getString(0);
                secondRequiredResource = secondRequiredResourceQuery.getString(0);
                requiresElectricity = requireElectricityQuery.getInt(0);
            }

        }

        productionTimeQuery.close();
        firstRequiredResourceQuery.close();
        secondRequiredResourceQuery.close();
        requireElectricityQuery.close();
        closeDatabase();

        return new Building(buildingName, productionTime, firstRequiredResource,
                secondRequiredResource, requiresElectricity);
    }
}
