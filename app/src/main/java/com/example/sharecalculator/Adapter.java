package com.example.sharecalculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class Adapter extends FragmentPagerAdapter {
    public Adapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new BuyFragment();
        } else if (position==1) {
            return new SellFragment();
        }else {
            return new SharesFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "BUY";
        } else if (position==1) {
            return "SELL";
        }else {
            return "MY SHARES";
        }
    }
}
