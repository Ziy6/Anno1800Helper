package com.adv.anno1800helper.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adv.anno1800helper.R;
import com.adv.anno1800helper.helpers.Building;
import com.adv.anno1800helper.helpers.DatabaseInteractor;

/***************************************************************************************************
 *
 *  A class setups the building calculator results fragment layout and receives input from the
 *  BuildingCalculator fragment to calculate and display results
 *
 **************************************************************************************************/

public class BuildingResults extends Fragment
{
    private static final String TAG = "BUILDINGS_RESULTS";
    private View view;
    private DatabaseInteractor dbInterractor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.building_results, container, false);

        String buildingName;
        dbInterractor = new DatabaseInteractor(getContext());
        buildingName = getArguments().getString(BuildingCalculator.BLD_CALC_BUNDLE_KEY);
        addInitialImage(buildingName);
        listenerSetup();

        return view;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        dbInterractor.closeDatabase();
    }

    //set input building button
    private void addInitialImage(String receivedBuildingName)
    {
        int inputBuildingId = getContext().getResources().getIdentifier(receivedBuildingName,
                "drawable", getContext().getPackageName());
        Button topButton = view.findViewById(R.id.top_button);
        topButton.setBackgroundResource(inputBuildingId);
    }

    private void listenerSetup()
    {
        final EditText inputEditText = view.findViewById(R.id.top_edit_text);

        //when the textEdit is clicked on
        inputEditText.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((EditText) v).setText("");
            }
        });

        //when search bar is clicked on
        inputEditText.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                if(inputEditText.getText().length()>0)
                {
                    //close keyboard
                    InputMethodManager inputMethodManager = (InputMethodManager)
                            getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    //clear the results from previous search if any
                    LinearLayout requiredBuildingContainer = view.findViewById(R.id.required_building_container);
                    requiredBuildingContainer.removeAllViews();

                    //initial inputed building from bundle
                    Building inputBuildingBuilding =
                            dbInterractor.getBuiliding(getArguments().getString(BuildingCalculator.BLD_CALC_BUNDLE_KEY));

                    //start recursive method to get all required buildings
                    if(!inputBuildingBuilding.getFirstRequiredResource().equals("none"))
                    {
                        addRequiredBuildings(inputBuildingBuilding.getFirstRequiredResource(),
                                inputBuildingBuilding.getProductionTime(),
                                Integer.parseInt(inputEditText.getText().toString()));
                    }

                    if(!inputBuildingBuilding.getSecondRequiredResource().equals("none"))
                    {
                        addRequiredBuildings(inputBuildingBuilding.getSecondRequiredResource(),
                                inputBuildingBuilding.getProductionTime(),
                                Integer.parseInt(inputEditText.getText().toString()));
                    }
                }
                return true;
            }
        });
    }

    //recursive method to add results to the bottom of the screen
    private void addRequiredBuildings(String requiredBuildingType, int inputBuildingProductionTime,
                                      int inputBuildingNumber)
    {
        Building building = dbInterractor.getBuiliding(requiredBuildingType);

        //getting initial building id
        int requiredBuildingId = getContext().getResources().getIdentifier(requiredBuildingType,
                "drawable", getContext().getPackageName());

        //container to contain all result containers/layouts
        LinearLayout requiredContainer = view.findViewById(R.id.required_building_container);

        //creating, setting and adding a result layout for components
        LinearLayout requiredInnerContainer = new LinearLayout(getContext());

        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ((LinearLayout.LayoutParams) layoutParams).setMargins(getPxFromDp(20), getPxFromDp(20),
                getPxFromDp(20), getPxFromDp(20));
        requiredInnerContainer.setLayoutParams(layoutParams);

        requiredContainer.addView(requiredInnerContainer);

        //sets up button/image to scrollable layout
        Button button = new Button(getContext());
        button.setGravity(Gravity.CENTER);
        button.setBackgroundResource(requiredBuildingId);
        button.setLayoutParams(new LinearLayout.LayoutParams(getPxFromDp(50), getPxFromDp(50)));

        //sets up text view/number of buildings needed to scrollable layout
        TextView numberOfBuildingsTextView = new TextView(getContext());
        numberOfBuildingsTextView.setGravity(Gravity.CENTER);

        //new parameters for component
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ((LinearLayout.LayoutParams) layoutParams).setMargins(getPxFromDp(15), 0,
                getPxFromDp(15), 0);
        numberOfBuildingsTextView.setLayoutParams(layoutParams);

        //calculate building numbers needed
        int calculatedBuildingsNeeded = calculateNumberNeeded(building.getProductionTime(),
                inputBuildingProductionTime, inputBuildingNumber);
        numberOfBuildingsTextView.setText("Buildings neded: " + calculatedBuildingsNeeded);

        //adds the button and text views
        requiredInnerContainer.addView(button);
        requiredInnerContainer.addView(numberOfBuildingsTextView);

        //(re)start recursive method to get all required buildings
        if(!building.getFirstRequiredResource().equals("none"))
            addRequiredBuildings(building.getFirstRequiredResource(),
                    building.getProductionTime(), calculatedBuildingsNeeded);

        if(!building.getSecondRequiredResource().equals("none"))
            addRequiredBuildings(building.getSecondRequiredResource(),
                    building.getProductionTime(), calculatedBuildingsNeeded);
    }

    //calculate number of buildings needed
    private int calculateNumberNeeded(int requiredBuildingProductionTime,
                                      int inputBuildingProductionTime, int inputBuildingNumber)
    {
        //ciel rounds up
        return (int) Math.ceil((double)inputBuildingNumber * ((double)requiredBuildingProductionTime/
                (double)inputBuildingProductionTime));
    }

    //unit converter
    private int getPxFromDp(int dp)
    {
        int DPI = getContext().getResources().getDisplayMetrics().densityDpi;

        return dp * (DPI/160);
    }
}
