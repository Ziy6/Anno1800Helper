package com.adv.anno1800helper.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adv.anno1800helper.R;

/***************************************************************************************************
 *
 *  A class that setups a layout container to add the building fragment(s) to it
 *
 **************************************************************************************************/

public class BuildingController extends Fragment
{
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.building_controller, container ,false);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.building_container, new BuildingCalculator());
        transaction.commit();

        return view;
    }
}
