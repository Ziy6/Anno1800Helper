package com.adv.anno1800helper.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.adv.anno1800helper.R;

public class BuildingResults extends Fragment
{

    private static final String TAG = "BUILDINGS_RESULTS";
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.building_results, container, false);
        addResult(getArguments().getString(BuildingCalculator.BLD_CALC_BUNDLE_KEY));

        return view;
    }

    //set input building button
    private void addResult(String receivedBuildingName)
    {
        int inputBuildingId = getContext().getResources().getIdentifier(receivedBuildingName,
                "drawable", getContext().getPackageName());
        Button topButton = view.findViewById(R.id.top_button);
        topButton.setBackgroundResource(inputBuildingId);
    }

    private String getPassedString(String key)
    {
        String passedString = "";

        Bundle bundle = this.getArguments();

        if(bundle!=null)
        {
            passedString  = bundle.getString(key);
        }

        return passedString;
    }
}
