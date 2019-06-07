package com.adv.anno1800helper.helpers;

import java.util.ArrayList;

/***************************************************************************************************
 *
 * Class that contains information to populate the database and methods to retrieve lists of values
 * for the two main tables
 *
 **************************************************************************************************/

public class Database
{
    private final int VERSION_NO = 3;

    private final String FARMER_NAME = "farmer";
    private final String WORKER_NAME = "worker";
    private final String ARTISTAN_NAME = "artistan";
    private final String ENGINEER_NAME = "engineer";
    private final String INVESTOR_NAME = "investor";
    private final String JORNALEROS_NAME = "jornaleros";
    private final String OBREROS_NAME = "obreros";

    private Building lumberJacksHut, sawmill, fishery, potatoFarm, schnappsDistillery, sheepFarm,
            frameworkKnitter, clayPit, brickFactory, grainFarm, flourMill, bakery, ironMine,
            coalMine,furnace, steelworks, pigFarm, renderingWorks, soapFactory, hopsFarm, malthouse,
            brewery, slaughterhouse, sandMine, glassMaker, windowMaker, cattleFarm, redPepperFarm,
            artisanalKitchen, cannery, sewingMachineFactory, cottonPlantation, cottonMill,
            huntingCabin, furDealer, copperMine, zincMine, brassSmeltery, spectacleFactory,
            filamentFactory, lightbulbFactory, bicycleFactory, caoutchoucPlantation, goldMine,
            goldsmith, clock_maker, wineyard, champagneCellar, jeweller, pearlFarm,
            marquetryWorkshop, plantainPlantation, fishOilFactory, friedPlantainKitchen,
            sugarcanePlantation, rumDistillery, alpacaFarm, ponchoDarner, cornFarm, tortillaMaker,
            coffeePlantation, coffeeRoaster, feltProducer, bombinWeaver, tobaccoPlantation,
            cigarFactory, cocaPlantation, sugarRefinery, chocolateFactory, motorAssemblyLine,
            gramophoneFactory, cabAssemblyLine, coachMaker;

    private Population farmer, worker, artistan, engineer, investor, jornaleros, obreros;

    private ArrayList<Population> populationList = new ArrayList<>();
    private ArrayList<Building> buildingList = new ArrayList<>();

    public void initializeDatabase()
    {
        setBuildingList();
        setPopulationList();
    }

    public ArrayList<Population> getPopulationList()
    {
        return populationList;
    }

    public ArrayList<Building> getBuildingList()
    {
        return buildingList;
    }

    public int getVersionNumber()
    {
        return VERSION_NO;
    }

    private void setBuildingList()
    {
        lumberJacksHut          = new Building("lumberjacks_hut",       15, "none",                 "none",                 0);
        sawmill                 = new Building("sawmill",               15, "lumberjacks_hut",      "none",                 0);
        fishery                 = new Building("fishery",               30, "none",                 "none",                 0);
        potatoFarm              = new Building("potato_farm",           30, "none",                 "none",                 0);
        schnappsDistillery      = new Building("schnapps_distillery",   30, "potato_farm",          "none",                 0);
        sheepFarm               = new Building("sheep_farm",            30, "none",                 "none",                 0);
        frameworkKnitter        = new Building("framework_knitter",     30, "sheep_farm",           "none",                 0);
        clayPit                 = new Building("clay_pit",              30, "none",                 "none",                 0);
        brickFactory            = new Building("brick_factory",         60, "clay_pits",            "none",                 0);
        grainFarm               = new Building("grain_farm",            60, "none",                 "none",                 0);
        flourMill               = new Building("flour_mill",            30, "grain_farm",           "none",                 0);
        bakery                  = new Building("bakery",                60, "flour_mill",           "none",                 0);
        ironMine                = new Building("iron_mine",             15, "none",                 "none",                 0);
        coalMine                = new Building("coal_mine",             15, "none",                 "none",                 0);
        furnace                 = new Building("furnace",               30, "coal_mine",            "iron_mine",            0);
        steelworks              = new Building("steelworks",            45, "furnace",              "none",                 0);
        pigFarm                 = new Building("pig_farm",              60, "none",                 "none",                 0);
        renderingWorks          = new Building("rendering_works",       60, "pig_farm",             "none",                 0);
        soapFactory             = new Building("soap_factory",          30, "rendering_works",      "none",                 0);
        hopsFarm                = new Building("hops_farm",             90, "none",                 "none",                 0);
        malthouse               = new Building("malthouse",             30, "grain_farm",           "none",                 0);
        brewery                 = new Building("brewery",               60, "malthouse",            "hops_farm",            0);
        slaughterhouse          = new Building("slaughterhouse",        60, "pig_farm",             "none",                 0);
        sandMine                = new Building("sand_mine",             30, "none",                 "none",                 0);
        glassMaker              = new Building("glass_maker",           30, "sand_mine",            "none",                 0);
        windowMaker             = new Building("window_maker",          60, "glass_maker",          "lumberjacks_hut",      0);
        cattleFarm              = new Building("cattle_farm",           120,"none",                 "none",                 0);
        redPepperFarm           = new Building("red_pepper_farm",       120,"none",                 "none",                 0);
        artisanalKitchen        = new Building("artisanal_kitchen",     120,"cattle_farm",          "red_pepper_farm",      0);
        cannery                 = new Building("cannery",               90, "artisanal_kitchen",    "iron_mine",            0);
        sewingMachineFactory    = new Building("sewing_machine_factory",30, "furnace",              "lumberjacks_hut",      0);
        cottonMill              = new Building("cotton_mill",           30, "cotton_plantation",    "none",                 0);
        cottonPlantation        = new Building("cotton_plantation",     60, "none",                 "none",                 0);
        huntingCabin            = new Building("hunting_cabin",         60, "cotton_mill",          "hunting_cabin",        0);
        furDealer               = new Building("fur_dealer",            30, "none",                 "none",                 0);
        copperMine              = new Building("copper_mine",           30, "none",                 "none",                 0);
        zincMine                = new Building("zinc_mine",             30, "none",                 "none",                 0);
        brassSmeltery           = new Building("brass_smeltery",        60, "zinc_mine",            "copper_mine",          0);
        spectacleFactory        = new Building("spectacle_factory",     90, "glass_maker",          "brass_smeltery",       0);
        filamentFactory         = new Building("filament_factory",      60, "coal_mine",            "none",                 0);
        lightbulbFactory        = new Building("lightbulb_factory",     60, "filament_factory",     "none",                 0);
        bicycleFactory          = new Building("bicycle_factory",       30, "furnace",              "caoutchouc_plantation",0);
        caoutchoucPlantation    = new Building("caoutchouc_plantation", 60, "none",                 "none",                 0);
        goldMine                = new Building("gold_mine",             150,"none",                 "none",                 0);
        goldsmith               = new Building("goldsmith",             60, "coal_mine",            "gold_mine",            0);
        clock_maker              = new Building("clock_maker",            90, "goldsmith",            "glass_maker",          0);
        wineyard                = new Building("wineyard",              120,"none",                 "none",                 0);
        champagneCellar         = new Building("champagne_cellar",      30, "glass_maker",          "wineyard",             0);
        jeweller                = new Building("jeweller",              30, "goldsmith",            "pearl_farm",           0);
        pearlFarm               = new Building("pearl_farm",            30, "none",                 "none",                 0);
        marquetryWorkshop       = new Building("marquetry_workshop",    120,"lumberjacks_hut",      "none",                 0);
        plantainPlantation      = new Building("plantain_plantation",   30, "none",                 "none",                 0);
        fishOilFactory          = new Building("fish_oil_factory",      30, "none",                 "none",                 0);
        friedPlantainKitchen    = new Building("fried_plantain_kitchen",30, "plantain_plantation",  "fish_oil_factory",     0);
        sugarcanePlantation     = new Building("sugarcane_plantation",  30, "none",                 "none",                 0);
        rumDistillery           = new Building("rum_distillery",        30, "sugar_cane_plantation","lumberjacks_hut",      0);
        alpacaFarm              = new Building("alpaca_farm",           30, "none",                 "none",                 0);
        ponchoDarner            = new Building("poncho_darner",         30, "alpaca_farm",          "none",                 0);
        cornFarm                = new Building("corn_farm",             60, "none",                 "none",                 0);
        tortillaMaker           = new Building("tortilla_maker",        30, "corn_farm",            "cattle_farm",          0);
        coffeePlantation        = new Building("coffee_plantation",     60, "none",                 "none",                 0);
        coffeeRoaster           = new Building("coffee_roaster",        30, "coffee_plantation",    "none",                 0);
        feltProducer            = new Building("felt_producer",         30, "alpaca_farm",          "none",                 0);
        bombinWeaver            = new Building("bombin_weaver",         30, "cotton_mill",          "felt_producer",        0);
        tobaccoPlantation       = new Building("tobacco_plantation",    120,"none",                 "none",                 0);
        cigarFactory            = new Building("cigar_factory",         30, "tobacco_plantation",   "marquetry_workshop",   0);
        cocaPlantation          = new Building("coca_plantation",       30, "none",                 "none",                 0);
        sugarRefinery           = new Building("sugar_refinery",        30, "sugar_plantation",     "none",                 0);
        chocolateFactory        = new Building("chocolate_factory",     30, "sugar_refinery",       "coca_plantation",      0);
        motorAssemblyLine       = new Building("motor_assembly_line",   60, "furnace",              "brass_smeltery",       1);
        gramophoneFactory       = new Building("gramophone_factory",    120,"marquetry_workshop",   "brass_smeltery",       1);
        cabAssemblyLine         = new Building("cab_assembly_line",     60, "motor_assembly_line",  "coach_maker",          1);
        coachMaker              = new Building("coach_maker",           120,"lumberjacks_hut",      "caoutchouc_plantation",1);

        buildingList.add(lumberJacksHut);
        buildingList.add(sawmill);
        buildingList.add(fishery);
        buildingList.add(potatoFarm);
        buildingList.add(schnappsDistillery);
        buildingList.add(sheepFarm);
        buildingList.add(frameworkKnitter);
        buildingList.add(clayPit);
        buildingList.add(brickFactory);
        buildingList.add(grainFarm);
        buildingList.add(flourMill);
        buildingList.add(bakery);
        buildingList.add(ironMine);
        buildingList.add(coalMine);
        buildingList.add(furnace);
        buildingList.add(steelworks);
        buildingList.add(pigFarm);
        buildingList.add(renderingWorks);
        buildingList.add(soapFactory);
        buildingList.add(hopsFarm);
        buildingList.add(malthouse);
        buildingList.add(brewery);
        buildingList.add(slaughterhouse);
        buildingList.add(sandMine);
        buildingList.add(glassMaker);
        buildingList.add(windowMaker);
        buildingList.add(cattleFarm);
        buildingList.add(redPepperFarm);
        buildingList.add(artisanalKitchen);
        buildingList.add(cannery);
        buildingList.add(sewingMachineFactory);
        buildingList.add(cottonMill);
        buildingList.add(cottonPlantation);
        buildingList.add(huntingCabin);
        buildingList.add(furDealer);
        buildingList.add(copperMine);
        buildingList.add(zincMine);
        buildingList.add(brassSmeltery);
        buildingList.add(spectacleFactory);
        buildingList.add(filamentFactory);
        buildingList.add(lightbulbFactory);
        buildingList.add(bicycleFactory);
        buildingList.add(caoutchoucPlantation);
        buildingList.add(goldMine);
        buildingList.add(goldsmith);
        buildingList.add(clock_maker);
        buildingList.add(wineyard);
        buildingList.add(champagneCellar);
        buildingList.add(jeweller);
        buildingList.add(pearlFarm);
        buildingList.add(marquetryWorkshop);
        buildingList.add(plantainPlantation);
        buildingList.add(fishOilFactory);
        buildingList.add(friedPlantainKitchen);
        buildingList.add(sugarcanePlantation);
        buildingList.add(rumDistillery);
        buildingList.add(alpacaFarm);
        buildingList.add(ponchoDarner);
        buildingList.add(cornFarm);
        buildingList.add(tortillaMaker);
        buildingList.add(coffeePlantation);
        buildingList.add(coffeeRoaster);
        buildingList.add(feltProducer);
        buildingList.add(bombinWeaver);
        buildingList.add(tobaccoPlantation);
        buildingList.add(cigarFactory);
        buildingList.add(cocaPlantation);
        buildingList.add(sugarcanePlantation);
        buildingList.add(chocolateFactory);
        buildingList.add(motorAssemblyLine);
        buildingList.add(gramophoneFactory);
        buildingList.add(cabAssemblyLine);
        buildingList.add(coachMaker);
    }

    private void setPopulationList()
    {
        ArrayList<PopulationBuilding> farmerReqList = new ArrayList();
        farmerReqList.add(new PopulationBuilding(fishery,               0.0025));
        farmerReqList.add(new PopulationBuilding(frameworkKnitter,      0.0034));
        farmerReqList.add(new PopulationBuilding(schnappsDistillery,    0.0031));

        farmer = new Population(FARMER_NAME, farmerReqList);

        ArrayList<PopulationBuilding> workerReqList = new ArrayList<>();
        workerReqList.add(new PopulationBuilding(fishery,               0.0025));
        workerReqList.add(new PopulationBuilding(frameworkKnitter,      0.0034));
        workerReqList.add(new PopulationBuilding(slaughterhouse,        0.0010));
        workerReqList.add(new PopulationBuilding(bakery,                0.0009));
        workerReqList.add(new PopulationBuilding(soapFactory,           0.0004));
        workerReqList.add(new PopulationBuilding(schnappsDistillery,    0.0031));
        workerReqList.add(new PopulationBuilding(brewery,               0.0008));

        worker = new Population(WORKER_NAME, workerReqList);

        ArrayList<PopulationBuilding> artistanReqList = new ArrayList<>();
        artistanReqList.add(new PopulationBuilding(slaughterhouse,      0.0013));
        artistanReqList.add(new PopulationBuilding(bakery,              0.0012));
        artistanReqList.add(new PopulationBuilding(soapFactory,         0.0012));
        artistanReqList.add(new PopulationBuilding(cannery,             0.0012));
        artistanReqList.add(new PopulationBuilding(sewingMachineFactory,0.0012));
        artistanReqList.add(new PopulationBuilding(furDealer,           0.0012));
        artistanReqList.add(new PopulationBuilding(brewery,             0.0012));
        artistanReqList.add(new PopulationBuilding(rumDistillery,       0.0012));

        artistan = new Population(ARTISTAN_NAME, artistanReqList);

        ArrayList<PopulationBuilding> engineerReqList = new ArrayList<>();
        engineerReqList.add(new PopulationBuilding(cannery,             0.0005));
        engineerReqList.add(new PopulationBuilding(sewingMachineFactory,0.0014));
        engineerReqList.add(new PopulationBuilding(furDealer,           0.0013));
        engineerReqList.add(new PopulationBuilding(spectacleFactory,    0.0002));
        engineerReqList.add(new PopulationBuilding(coffeeRoaster,       0.0012));
        engineerReqList.add(new PopulationBuilding(lightbulbFactory,    0.0003));
        engineerReqList.add(new PopulationBuilding(rumDistillery,       0.0029));
        engineerReqList.add(new PopulationBuilding(bicycleFactory,      0.0006));
        engineerReqList.add(new PopulationBuilding(clock_maker,          0.0002));

        engineer = new Population(ENGINEER_NAME, engineerReqList);

        ArrayList<PopulationBuilding> investorReqList = new ArrayList<>();
        investorReqList.add(new PopulationBuilding(spectacleFactory,    0.0004));
        investorReqList.add(new PopulationBuilding(coffeeRoaster,       0.0019));
        investorReqList.add(new PopulationBuilding(lightbulbFactory,    0.0005));
        investorReqList.add(new PopulationBuilding(champagneCellar,     0.0005));
        investorReqList.add(new PopulationBuilding(cigarFactory,        0.0004));
        investorReqList.add(new PopulationBuilding(chocolateFactory,    0.0011));
        investorReqList.add(new PopulationBuilding(cabAssemblyLine,     0.0001));
        investorReqList.add(new PopulationBuilding(bicycleFactory,      0.0010));
        investorReqList.add(new PopulationBuilding(clock_maker,          0.0003));
        investorReqList.add(new PopulationBuilding(jeweller,            0.0004));
        investorReqList.add(new PopulationBuilding(gramophoneFactory,   0.0001));

        investor = new Population(INVESTOR_NAME, investorReqList);

        ArrayList<PopulationBuilding> jornalerosReqList = new ArrayList<>();
        jornalerosReqList.add(new PopulationBuilding(friedPlantainKitchen,  0.0029));
        jornalerosReqList.add(new PopulationBuilding(ponchoDarner,          0.0014));
        jornalerosReqList.add(new PopulationBuilding(rumDistillery,         0.0025));

        jornaleros = new Population(JORNALEROS_NAME, jornalerosReqList);

        ArrayList<PopulationBuilding> obrerosReqList = new ArrayList<>();
        obrerosReqList.add(new PopulationBuilding(friedPlantainKitchen,     0.0029));
        obrerosReqList.add(new PopulationBuilding(ponchoDarner,             0.0025));
        obrerosReqList.add(new PopulationBuilding(tortillaMaker,            0.0014));
        obrerosReqList.add(new PopulationBuilding(coffeeRoaster,            0.0006));
        obrerosReqList.add(new PopulationBuilding(bombinWeaver,             0.0013));
        obrerosReqList.add(new PopulationBuilding(sewingMachineFactory,     0.0013));
        obrerosReqList.add(new PopulationBuilding(rumDistillery,            0.0014));
        obrerosReqList.add(new PopulationBuilding(brewery,                  0.0013));
        obrerosReqList.add(new PopulationBuilding(cigarFactory,             0.0006));

        obreros = new Population(OBREROS_NAME, obrerosReqList);

        populationList.add(farmer);
        populationList.add(worker);
        populationList.add(artistan);
        populationList.add(engineer);
        populationList.add(investor);
        populationList.add(jornaleros);
        populationList.add(obreros);
    }
}