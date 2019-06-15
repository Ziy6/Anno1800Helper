package com.adv.anno1800helper.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.adv.anno1800helper.R;

/***************************************************************************************************
 *
 *  A class setups the building calculator fragment layout and handles inputs to be passed to the
 *  BuildingResults class for calculation and the displaying of results
 *
 **************************************************************************************************/

public class BuildingCalculator  extends Fragment implements View.OnClickListener
{
    private final String TAG = "BUILDING_CALCULATOR";
    public static final String BLD_CALC_BUNDLE_KEY = "BUILDING_KEY";
    private Button sawmill_button, schnapps_distillery_button, framework_knitter_button, brick_factory_button,
            bakery_button, steelworks_button, soap_factory, brewery_button, slaughterhouse_button,
            window_maker_button, cannery_button, sewing_machine_factory_button, fur_dealer_button,
            spectacle_factory_button, lightbulb_factory_button, bicycle_factory_button, clockmaker_button,
            champagne_cellar_button, jeweller_button, fried_plantain_kitchen_button, rum_distillery_button,
            poncho_darner_button, coffee_roaster_button, bombin_factory_button, cigar_factory_button,
            chocolate_factory_button, gramophone_factory_button, cab_assembly_line_button;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.building_calculator, container, false);
        initializeButtonListeners();

        return view;
    }

    private void initializeButtonListeners()
    {
        sawmill_button = view.findViewById(R.id.sawmill_button);
        schnapps_distillery_button = view.findViewById(R.id.schnapps_distillery_button);
        framework_knitter_button = view.findViewById(R.id.framework_knitter_button);
        brick_factory_button = view.findViewById(R.id.brick_factory_button);
        bakery_button = view.findViewById(R.id.bakery_button);
        steelworks_button = view.findViewById(R.id.steelworks_button);
        soap_factory = view.findViewById(R.id.soap_factory_button);
        brewery_button = view.findViewById(R.id.brewery_button);
        slaughterhouse_button = view.findViewById(R.id.slaughterhouse_button);
        window_maker_button = view.findViewById(R.id.window_maker_button);
        cannery_button = view.findViewById(R.id.cannery_button);
        sewing_machine_factory_button = view.findViewById(R.id.sewing_machine_factory_button);
        fur_dealer_button = view.findViewById(R.id.fur_dealer_button);
        spectacle_factory_button = view.findViewById(R.id.spectacle_factory_button);
        lightbulb_factory_button = view.findViewById(R.id.lightbulb_factory_button);
        bicycle_factory_button = view.findViewById(R.id.bicycle_factory_button);
        clockmaker_button = view.findViewById(R.id.clockmaker_button);
        champagne_cellar_button = view.findViewById(R.id.champagne_cellar_button);
        jeweller_button = view.findViewById(R.id.jeweller_button);
        fried_plantain_kitchen_button = view.findViewById(R.id.fried_plantain_kitchen_button);
        rum_distillery_button = view.findViewById(R.id.rum_distillery_button);
        poncho_darner_button = view.findViewById(R.id.poncho_darner_button);
        coffee_roaster_button = view.findViewById(R.id.coffee_roaster_button);
        bombin_factory_button = view.findViewById(R.id.bombin_weaver_button);
        cigar_factory_button = view.findViewById(R.id.cigar_factory_button);
        chocolate_factory_button = view.findViewById(R.id.chocolate_factory_button);
        gramophone_factory_button = view.findViewById(R.id.gramophone_factory_button);
        cab_assembly_line_button = view.findViewById(R.id.cab_assembly_line_button);

        sawmill_button.setOnClickListener(this);
        schnapps_distillery_button.setOnClickListener(this);
        framework_knitter_button.setOnClickListener(this);
        brick_factory_button.setOnClickListener(this);
        bakery_button.setOnClickListener(this);
        steelworks_button.setOnClickListener(this);
        soap_factory.setOnClickListener(this);
        brewery_button.setOnClickListener(this);
        slaughterhouse_button.setOnClickListener(this);
        window_maker_button.setOnClickListener(this);
        cannery_button.setOnClickListener(this);
        sewing_machine_factory_button.setOnClickListener(this);
        fur_dealer_button.setOnClickListener(this);
        spectacle_factory_button.setOnClickListener(this);
        lightbulb_factory_button.setOnClickListener(this);
        bicycle_factory_button.setOnClickListener(this);
        clockmaker_button.setOnClickListener(this);
        champagne_cellar_button.setOnClickListener(this);
        jeweller_button.setOnClickListener(this);
        fried_plantain_kitchen_button.setOnClickListener(this);
        rum_distillery_button.setOnClickListener(this);
        poncho_darner_button.setOnClickListener(this);
        coffee_roaster_button.setOnClickListener(this);
        bombin_factory_button.setOnClickListener(this);
        cigar_factory_button.setOnClickListener(this);
        chocolate_factory_button.setOnClickListener(this);
        gramophone_factory_button.setOnClickListener(this);
        cab_assembly_line_button.setOnClickListener(this);

        sawmill_button.setBackgroundResource(R.drawable.sawmill);
        schnapps_distillery_button.setBackgroundResource(R.drawable.schnapps_distillery);
        framework_knitter_button.setBackgroundResource(R.drawable.framework_knitter);
        brick_factory_button.setBackgroundResource(R.drawable.brick_factory);
        bakery_button.setBackgroundResource(R.drawable.bakery);
        steelworks_button.setBackgroundResource(R.drawable.steelworks);
        soap_factory.setBackgroundResource(R.drawable.soap_factory);
        brewery_button.setBackgroundResource(R.drawable.brewery);
        slaughterhouse_button.setBackgroundResource(R.drawable.slaughterhouse);
        window_maker_button.setBackgroundResource(R.drawable.window_maker);
        cannery_button.setBackgroundResource(R.drawable.cannery);
        sewing_machine_factory_button.setBackgroundResource(R.drawable.sewing_machine_factory);
        fur_dealer_button.setBackgroundResource(R.drawable.fur_dealer);
        spectacle_factory_button.setBackgroundResource(R.drawable.spectacle_factory);
        lightbulb_factory_button.setBackgroundResource(R.drawable.lightbulb_factory);
        bicycle_factory_button.setBackgroundResource(R.drawable.bicycle_factory);
        clockmaker_button.setBackgroundResource(R.drawable.clock_maker);
        champagne_cellar_button.setBackgroundResource(R.drawable.champagne_cellar);
        jeweller_button.setBackgroundResource(R.drawable.jeweller);
        fried_plantain_kitchen_button.setBackgroundResource(R.drawable.fried_plaintain_kitchen);
        rum_distillery_button.setBackgroundResource(R.drawable.rum_distillery);
        poncho_darner_button.setBackgroundResource(R.drawable.poncho_darner);
        coffee_roaster_button.setBackgroundResource(R.drawable.coffee_roaster);
        bombin_factory_button.setBackgroundResource(R.drawable.bombin_weaver);
        cigar_factory_button.setBackgroundResource(R.drawable.cigar_factory);
        chocolate_factory_button.setBackgroundResource(R.drawable.chocolate_factory);
        gramophone_factory_button.setBackgroundResource(R.drawable.gramophone_factory);
        cab_assembly_line_button.setBackgroundResource(R.drawable.cab_assembly_line);
    }

    @Override
    public void onClick(View v)
    {
        String buildingType = " ";

        switch (v.getId())
        {
            case R.id.sawmill_button:
                buildingType = "sawmill";
                break;
            case R.id.schnapps_distillery_button:
                buildingType = "schnapps_distillery";
                break;
            case R.id.framework_knitter_button:
                buildingType = "framework_knitter";
                break;
            case R.id.brick_factory_button:
                buildingType = "brick_factory";
                break;
            case R.id.bakery_button:
                buildingType = "bakery";
                break;
            case R.id.steelworks_button:
                buildingType = "steelworks";
                break;
            case R.id.soap_factory_button:
                buildingType = "soap_factory";
                break;
            case R.id.brewery_button:
                buildingType = "brewery";
                break;
            case R.id.slaughterhouse_button:
                buildingType = "slaughterhouse";
                break;
            case R.id.window_maker_button:
                buildingType = "window_maker";
                break;
            case R.id.cannery_button:
                buildingType = "cannery";
                break;
            case R.id.sewing_machine_factory_button:
                buildingType = "sewing_machine_factory";
                break;
            case R.id.fur_dealer_button:
                buildingType = "fur_dealer";
                break;
            case R.id.spectacle_factory_button:
                buildingType = "spectacle_factory";
                break;
            case R.id.lightbulb_factory_button:
                buildingType = "lightbulb_factory";
                break;
            case R.id.bicycle_factory_button:
                buildingType = "bicycle_factory";
                break;
            case R.id.clockmaker_button:
                buildingType = "clockmaker";
                break;
            case R.id.champagne_cellar_button:
                buildingType = "champagne_cellar";
                break;
            case R.id.jeweller_button:
                buildingType = "jeweller";
                break;
            case R.id.fried_plantain_kitchen_button:
                buildingType = "fried_plantain_kitchen";
                break;
            case R.id.rum_distillery_button:
                buildingType = "rum_distillery";
                break;
            case R.id.poncho_darner_button:
                buildingType = "poncho_darner";
                break;
            case R.id.coffee_roaster_button:
                buildingType = "coffee_roaster";
                break;
            case R.id.bombin_weaver_button:
                buildingType = "bombin_weaver";
                break;
            case R.id.cigar_factory_button:
                buildingType = "cigar_factory";
                break;
            case R.id.chocolate_factory_button:
                buildingType = "chocolate_factory";
                break;
            case R.id.gramophone_factory_button:
                buildingType = "gramophone_factory";
                break;
            case R.id.cab_assembly_line_button:
                buildingType = "cab_assembly_line";
                break;
        }
        changeView(buildingType);
    }

    private void changeView(String buildingType)
    {
        BuildingResults buildingResults = new BuildingResults();
        Bundle args = new Bundle();
        args.putString(BLD_CALC_BUNDLE_KEY, buildingType); //key used to retrieve info
        buildingResults.setArguments(args);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.building_container, buildingResults);
        transaction.addToBackStack(TAG);
        transaction.commit();
    }
}
