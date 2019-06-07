package com.adv.anno1800helper.fragments;

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
import com.adv.anno1800helper.helpers.Consumption;
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
    private EditText farmerEditText, workerEditText, artistanEditText, engineerEditText,
            investorEditText, jornalerosEditText, obrerosEditText;

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
            ArrayList<Consumption> consumptionList = new ArrayList<>();
            for(int i=0; i<editTextList.size(); i++)
            {
                //editText not empty
                if(editTextList.get(i).getText().length()>0)
                {
                    int populationNumber = Integer.parseInt(editTextList.get(i).getText().toString());

                    switch(i)
                    {
                        case 0:
                            addToConsumptionList("farmer", populationNumber,
                                    consumptionList);
                            break;

                        case 1:
                            addToConsumptionList("worker", populationNumber,
                                    consumptionList);
                            break;

                        case 2:
                            addToConsumptionList("artistan", populationNumber,
                                    consumptionList);
                            break;

                        case 3:
                            addToConsumptionList("engineer", populationNumber,
                                    consumptionList);
                            break;

                        case 4:
                            addToConsumptionList("investor", populationNumber,
                                    consumptionList);
                            break;

                        case 5:
                            addToConsumptionList("jornaleros", populationNumber,
                                    consumptionList);
                            break;

                        case 6:
                            addToConsumptionList("obreros", populationNumber,
                                    consumptionList);
                            break;
                    }
                }
            }


        }
    };

    private void addToConsumptionList(String populationType, int populationNumber,
                                      ArrayList<Consumption> consumptionList)
    {
        Population population = databaseInteractor.getPopulation(populationType);
        ArrayList<PopulationBuilding> resourceNeedsList =  population.getResourceNeeds();

        //loop through current population's needs list, add new consumption tonnages to list
        for(int i=0; i<resourceNeedsList.size(); i++)
        {
            if(consumptionList.size()!=0)
            {
                //if current population resource already exists in global list, return index
                int consumptionIndex = existsIn(consumptionList, resourceNeedsList.get(i).getName());

                if(consumptionIndex>=0)
                {
                    consumptionList.get(consumptionIndex).addTonnage
                            (calculateTonnage(resourceNeedsList.get(i).getConsumptionRate(),
                                    populationNumber));
                }
                else
                {
                    consumptionList.add(new Consumption(resourceNeedsList.get(i).getName(),
                            calculateTonnage(resourceNeedsList.get(i).getConsumptionRate(),
                                    populationNumber)));
                }
            }
            else
            {
                consumptionList.add(new Consumption(resourceNeedsList.get(i).getName(),
                        calculateTonnage(resourceNeedsList.get(i).getConsumptionRate(),
                                populationNumber)));
            }
        }
        addToViews(consumptionList);
    }

    private void addToViews(ArrayList<Consumption> consumptionList)
    {
        //loop through global list and add to views
        for(int i=0; i<consumptionList.size(); i++)
        {
            //getting the 2 views for current resource
            int tonnageId = getResources().getIdentifier("population_" +
                           consumptionList.get(i).getName() + "_tonnage_textview", "id",
                    getContext().getPackageName());

            int buildingId = getResources().getIdentifier("population_" +
                            consumptionList.get(i).getName() + "_building_textview", "id",
                    getContext().getPackageName());

            TextView tonnageTextView = view.findViewById(tonnageId);
            TextView buildingTextView = view.findViewById(buildingId);

            //calculate final building number and add
            int buildingsNeeed =
                    calculateBuildingNumber(databaseInteractor.
                                    getBuiliding(consumptionList.get(i).getName()).getProductionTime(),
                            consumptionList.get(i).getTonnage());

            tonnageTextView.setText(roundNumbers(consumptionList.get(i).getTonnage(), 1)
                    + " Tonnes");
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

    private int existsIn(ArrayList<Consumption> list, String name)
    {
        for(int i=0; i<list.size(); i++)
        {
            if(list.get(i).equals(name))
            {
                return i;
            }
        }
        return -1;
    }

    private String roundNumbers(double numbers, int roundBy)
    {
        String decimalPlace = "#.#";

        for(int i=1; i<roundBy; i++)
        {
            decimalPlace += "#";
        }

        DecimalFormat df = new DecimalFormat(decimalPlace);
        df.setRoundingMode(RoundingMode.CEILING);

        return df.format(numbers);
    }
}
