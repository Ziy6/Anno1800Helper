package com.adv.anno1800helper;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.adv.anno1800helper.fragments.BuildingController;
import com.adv.anno1800helper.fragments.PopulationController;
import com.adv.anno1800helper.adapters.ViewPageAdaptor;
import com.adv.anno1800helper.helpers.Building;
import com.adv.anno1800helper.helpers.DatabaseInteractor;
import com.adv.anno1800helper.helpers.Population;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uiSetup();
    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPageAdaptor mAdaptor = new ViewPageAdaptor(getSupportFragmentManager());
        mAdaptor.addfragment(new PopulationController(), "Population Controller");
        mAdaptor.addfragment(new BuildingController(), "Building Controller");

        viewPager.setAdapter(mAdaptor);
    }
    private void uiSetup()
    {
        ViewPager mViewPager = findViewById(R.id.mContainer);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.mTabLayout);
        tabLayout.setupWithViewPager(mViewPager);
    }
}
