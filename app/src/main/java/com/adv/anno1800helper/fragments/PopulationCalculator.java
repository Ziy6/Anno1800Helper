package com.adv.anno1800helper.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.adv.anno1800helper.R;

public class PopulationCalculator extends Fragment implements View.OnClickListener
{
    private View view;
    private EditText farmerEditText, workerRditText, artistanEditText, engineerEditText, investorEditText,
            jornalerosEditText, obrerosEditText;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.population_calculator, container, false);
        setupListeners();

        return view;
    }

    private void setupListeners()
    {
        farmerEditText = view.findViewById(R.id.farmer_edit_text);
        workerRditText = view.findViewById(R.id.worker_edit_text);
        artistanEditText = view.findViewById(R.id.artistan_edit_text);
        engineerEditText = view.findViewById(R.id.engineer_edit_text);
        investorEditText = view.findViewById(R.id.investor_edit_text);
        jornalerosEditText = view.findViewById(R.id.jornaleros_edit_text);
        obrerosEditText = view.findViewById(R.id.obreros_edit_text);

        farmerEditText.setOnClickListener(this);
        workerRditText.setOnClickListener(this);
        artistanEditText.setOnClickListener(this);
        engineerEditText.setOnClickListener(this);
        investorEditText.setOnClickListener(this);
        jornalerosEditText.setOnClickListener(this);
        obrerosEditText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        ((EditText) v).setText("0");
    }
}
