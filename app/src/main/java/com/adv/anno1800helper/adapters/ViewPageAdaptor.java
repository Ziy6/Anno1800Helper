package com.adv.anno1800helper.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPageAdaptor extends FragmentPagerAdapter
{
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentListTitles = new ArrayList<>();

    public ViewPageAdaptor(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return fragmentListTitles.get(position);
    }

    @Override
    public int getCount()
    {
        return fragmentListTitles.size();
    }

    public void addfragment(Fragment fragment, String title)
    {
        fragmentListTitles.add(title);
        fragmentList.add(fragment);
    }
}