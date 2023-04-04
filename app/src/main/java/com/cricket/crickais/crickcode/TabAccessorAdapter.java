package com.cricket.crickais.crickcode;

import org.jetbrains.annotations.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAccessorAdapter extends FragmentPagerAdapter {
    public TabAccessorAdapter(FragmentManager fm) {
        super(fm);
    }

    public Fragment getItem(int i) {
        switch(i) {
            case 0:
                Tab1 tab1 = new Tab1();
                return tab1;
            case 1:
                Tab2 tab2 = new Tab2();
                return tab2;
            case 2:
                Tab3 tab3 = new Tab3();
                return tab3;

            default:
                return null;
        }
    }

    public int getCount() {
        return 3;
    }

    @Nullable
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return "Live";
            case 1:
                return "Upcoming";
            case 2:
                return "Completed";

            default:
                return null;
        }
    }
}
