package com.cricket.crickais.crickcode;

import org.jetbrains.annotations.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAccessorAdapter1 extends FragmentPagerAdapter {
    public TabAccessorAdapter1(FragmentManager fm) {
        super(fm);
    }

    public Fragment getItem(int i) {
        switch(i) {
            case 0:
                fmatch tab1 = new fmatch();
                return tab1;
            case 1:
                fsquad tab2 = new fsquad();
                return tab2;
            case 2:
                pt2 tab3 = new pt2();
                return tab3;
            case 3:
                fstat tab31 = new fstat();
                return tab31;
            case 4:
                fvenu tab444 = new fvenu();
                return tab444;

            case 5:
                Tab444 tab445 = new Tab444();
                return tab445;

            default:
                return null;
        }
    }

    public int getCount() {
        return 6;
    }

    @Nullable
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return "Matches";
            case 1:
                return "Squads";
            case 2:
                return "Point Table";
            case 3:
                return "Stats";
            case 4:
                return "Venue";
            case 5:
                return "News";

            default:
                return null;
        }
    }
}
