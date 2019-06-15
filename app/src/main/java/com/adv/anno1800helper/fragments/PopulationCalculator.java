package com.adv.anno1800helper.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.adv.anno1800helper.R;
import com.adv.anno1800helper.helpers.DatabaseInteractor;
import com.adv.anno1800helper.helpers.Population;
import com.adv.anno1800helper.helpers.PopulationBuilding;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

/***************************************************************************************************
 *
 *  A class setups the population calculator fragment layout and handles inputs, calculation and
 *  updates
 *
 **************************************************************************************************/

public class PopulationCalculator extends Fragment implements View.OnClickListener,
        TextView.OnEditorActionListener
{
    private DatabaseInteractor databaseInteractor;
    private ArrayList<EditText> editTextList = new ArrayList<>();
    private EditText farmerEditText, workerEditText, artistanEditText, engineerEditText,
            investorEditText, jornalerosEditText, obrerosEditText;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.population_calculator, container, false);
        databaseInteractor = new DatabaseInteractor(getContext());
        setupListeners();

        return view;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        databaseInteractor.closeDatabase();
    }

    private void setupListeners()
    {
        farmerEditText = view.findViewById(R.id.farmer_edit_text);
        workerEditText = view.findViewById(R.id.worker_edit_text);
        artistanEditText = view.findViewById(R.id.artistan_edit_text);
        engineerEditText = view.findViewById(R.id.engineer_edit_text);
        investorEditText = view.findViewById(R.id.investor_edit_text);
        jornalerosEditText = view.findViewById(R.id.jornalero_edit_text);
        obrerosEditText = view.findViewById(R.id.obrero_edit_text);

        editTextList.add(farmerEditText);
        editTextList.add(workerEditText);
        editTextList.add(artistanEditText);
        editTextList.add(engineerEditText);
        editTextList.add(investorEditText);
        editTextList.add(jornalerosEditText);
        editTextList.add(obrerosEditText);

        for(int i=0; i<editTextList.size(); i++)
        {
            editTextList.get(i).setOnClickListener(this);           //reset the editTexts to ""
            editTextList.get(i).setOnEditorActionListener(this);    //calculate results
        }
    }

    @Override
    public void onClick(View v)
    {
        ((EditText) v).setText("");
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
    {
        if (actionId == EditorInfo.IME_ACTION_DONE) //done button clicked
        {
            boolean firstCall = true;

            for(int i=0; i<editTextList.size(); i++)    //loops through all textEdits for population
            {
                int populationNumber;

                if(editTextList.get(i).getText().toString().equals(""))
                {
                    populationNumber = 0;
                }
                else
                {
                    populationNumber = Integer.parseInt(editTextList.get(i).getText().toString());
                }

                switch(i)
                {
                    case 0:
                        addResourceNumbers("farmer", populationNumber,
                                firstCall);
                        firstCall = false;
                        break;

                    case 1:
                        addResourceNumbers("worker", populationNumber,
                                firstCall);
                        break;

                    case 2:
                        addResourceNumbers("artistan", populationNumber,
                                firstCall);
                        break;

                    case 3:
                        addResourceNumbers("engineer", populationNumber,
                                firstCall);
                        break;

                    case 4:
                        addResourceNumbers("investor", populationNumber,
                                firstCall);

                        break;

                    case 5:
                        addResourceNumbers("jornalero", populationNumber,
                                firstCall);
                        break;

                    case 6:

                        addResourceNumbers("obrero", populationNumber,
                                firstCall);
                        break;
                }
            }

            //hide soft keyboard
            InputMethodManager imm =
                    (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            return true;
        }
        return false;
    }

    //calculate tonnage and calls 'addToViews()' method
    private void addResourceNumbers(String populationType, int populationNumber, boolean firstCall)
    {
        Population population = databaseInteractor.getPopulation(populationType);
        ArrayList<PopulationBuilding> resourceNeedsList =  population.getResourceNeeds();

        for(int i=0; i<resourceNeedsList.size(); i++)
        {
            //firstCall needed to reset all resource textViews in/passed to 'addToViews()' method
            if(resourceNeedsList.get(i).getConsumptionRate()>0.00 || firstCall==true)
            {
                double tonnage = calculateTonnage(resourceNeedsList.get(i).getConsumptionRate(),
                        populationNumber);

                int productionTime = resourceNeedsList.get(i).getProductionTime();

                addToViews(resourceNeedsList.get(i).getName(), tonnage, productionTime, firstCall);
            }
        }
    }

    //calculate number of buildings add values to resource textViews
    private void addToViews(String resourceType, double tonnage, int productionTime,
                            boolean firstCall)
    {
        //getting view Ids for current resource type
        int tonnageId = getResources().getIdentifier("population_" + resourceType +
                        "_tonnage_textview", "id", getContext().getPackageName());

        int buildingId = getResources().getIdentifier("population_" + resourceType +
                "_building_textview", "id", getContext().getPackageName());

        TextView tonnageTextView = view.findViewById(tonnageId);
        TextView buildingTextView = view.findViewById(buildingId);

        //reset values
        if(firstCall==true)
        {
            tonnageTextView.setText("0.0 Tonnes");
            buildingTextView.setText("0 Buildings");
        }

        double oldTonnage = Double.parseDouble(tonnageTextView.getText().toString().substring(0,
                tonnageTextView.length()-7));
        int buildingNumber = calculateBuildingNumber(productionTime, tonnage + oldTonnage);

        //change view displayed numbers
        tonnageTextView.setText(roundNumbers(tonnage + oldTonnage, 4)
                + " Tonnes");
        buildingTextView.setText(buildingNumber + " Buildings");
    }

    private int calculateBuildingNumber(int productionTime, double tonnage)
    {
        return (int) Math.ceil((double) productionTime/60.0 * tonnage); //ciel rounds up
    }

    private double calculateTonnage(double resourceConsumption, int populationNumber)
    {
        return resourceConsumption * populationNumber;
    }

    private String roundNumbers(double number, int roundBy)
    {
        String decimalPlace = "#.#";

        for(int i=1; i<roundBy; i++)
        {
            decimalPlace += "#";
        }

        DecimalFormat df = new DecimalFormat(decimalPlace);
        df.setRoundingMode(RoundingMode.CEILING);

        return df.format(number);
    }
}
