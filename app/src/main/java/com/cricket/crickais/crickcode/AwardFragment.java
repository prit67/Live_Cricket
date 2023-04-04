package com.cricket.crickais.crickcode;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toolbar;


import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


/**
 * A simple {@link Fragment} subclass.
 */
public class AwardFragment extends Fragment {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mtabLayout;
    private TabAccessorAdapter1 mTabAccessorAdapter;
    private RelativeLayout main_page_toolbar1;
    ImageView go;
    public AwardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_award, container, false);


        mtabLayout = (TabLayout) rootView.findViewById(R.id.main_tab);
        mViewPager = (ViewPager) rootView.findViewById(R.id.main_tabs_pager);

        mTabAccessorAdapter = new TabAccessorAdapter1(getChildFragmentManager());
        mViewPager.setAdapter(mTabAccessorAdapter);

        mtabLayout.setupWithViewPager(mViewPager);
        return rootView;



    }



}