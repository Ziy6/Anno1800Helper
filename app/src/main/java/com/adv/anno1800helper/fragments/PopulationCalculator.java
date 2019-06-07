package com.adv.anno1800helper.fragments;


import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.adv.anno1800helper.R;
import com.adv.anno1800helper.helpers.DatabaseInteractor;
import com.adv.anno1800helper.helpers.Population;
import com.adv.anno1800helper.helpers.PopulationBuilding;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PopulationCalculator extends Fragment implements View.OnClickListener,
        TextView.OnEditorActionListener
{
    private DatabaseInteractor databaseInteractor;
    private ArrayList<EditText> editTextList = new ArrayList<>();
    private View view;
    private EditText farmerEditText, workerEditText, artistanEditText, engineerEditText, investorEditText,
            jornalerosEditText, obrerosEditText;

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

    private void setupListeners()
    {
        farmerEditText = view.findViewById(R.id.farmer_edit_text);
        workerEditText = view.findViewById(R.id.worker_edit_text);
        artistanEditText = view.findViewById(R.id.artistan_edit_text);
        engineerEditText = view.findViewById(R.id.engineer_edit_text);
        investorEditText = view.findViewById(R.id.investor_edit_text);
        jornalerosEditText = view.findViewById(R.id.jornaleros_edit_text);
        obrerosEditText = view.findViewById(R.id.obreros_edit_text);

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
            editTextList.get(i).setOnEditorActionListener(this);    //move focus to a new editText
            editTextList.get(i).addTextChangedListener(keyWatcher); //calculate results
        }
    }

    @Override
    public void onClick(View v)
    {
        ((EditText) v).setText("");
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
    {return false;}

    private TextWatcher keyWatcher = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {}

        @Override
        public void afterTextChanged(Editable s)
        {
            for(int i=0; i<editTextList.size(); i++)
            {
                //editText not empty
                if(editTextList.get(i).getText().length()>0)
                {
                    int populationNumber = Integer.parseInt(editTextList.get(i).getText().toString());

                    switch(i)
                    {
                        case 0:
                            addResults("farmer", populationNumber);
                            break;

                        case 1:
                            addResults("worker", populationNumber);
                            break;

                        case 2:
                            addResults("artistan", populationNumber);
                            break;

                        case 3:
                            addResults("engineer", populationNumber);
                            break;

                        case 4:
                            addResults("investor", populationNumber);
                            break;

                        case 5:
                            addResults("jornaleros", populationNumber);
                            break;

                        case 6:
                            addResults("obreros", populationNumber);
                            break;
                    }
                }
            }
        }
    };

    private void addResults(String populationType, int populationNumber)
    {
        Population population = databaseInteractor.getPopulation(populationType);
        ArrayList<PopulationBuilding> resourceNeedsList = population.getResourceNeeds();

        //loop through all population editTexts
        for(int i=0; i<resourceNeedsList.size(); i++)
        {
            int buildingId = getResources().getIdentifier("population_" +
                            resourceNeedsList.get(i).getName() + "_building_textview", "id",
                    getContext().getPackageName());

            int tonnageId = getResources().getIdentifier("population_" +
                            resourceNeedsList.get(i).getName() + "_tonnage_textview", "id",
                    getContext().getPackageName());

            TextView tonnageTextView = view.findViewById(tonnageId);
            TextView buildingTextView = view.findViewById(buildingId);

            double tonnageNeeded =
                    Double.parseDouble(tonnageTextView.getText().toString().
                            substring(0, tonnageTextView.getText().toString().length()-7))
                    + calculateTonnage(resourceNeedsList.get(i).getConsumptionRate(),
                            populationNumber);

            int buildingsNeeed =
                    calculateBuildingNumber(resourceNeedsList.get(i).getProductionTime(),
                            tonnageNeeded);

            tonnageTextView.setText(tonnageNeeded + " Tonnes");
            buildingTextView.setText(buildingsNeeed + " Buildings");
        }
    }

    private int calculateBuildingNumber(int productionTime, double tonnage)
    {
        return (int) Math.ceil((double) productionTime/60.0 * tonnage);
    }

    private double calculateTonnage(double resourceConsumption, int populationNumber)
    {
        return resourceConsumption * populationNumber;
    }

    private String roundNumbers(double numbers, int roundBy)
    {
        String decimalPlace = "#.#";
        for(int i=1; i<roundBy; i++)
            decimalPlace += "#";

        DecimalFormat df = new DecimalFormat(decimalPlace);
        df.setRoundingMode(RoundingMode.CEILING);

        return df.format(numbers);
    }

}
