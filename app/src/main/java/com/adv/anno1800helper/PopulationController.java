package com.adv.anno1800helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PopulationController extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.population_controller, container, false);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.population_container, new PopulationCalculator());
        transaction.commit();

        return view;
    }
}
