package com.cricket.crickais.crickcode;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class ProfileFragment extends Fragment {
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mtabLayout;
    private TabAccessorAdapter mTabAccessorAdapter;
    private RelativeLayout main_page_toolbar1;
    ImageView go;


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_profile,container,false);



        mtabLayout = (TabLayout) rootView.findViewById(R.id.main_tab);
        mViewPager = (ViewPager) rootView.findViewById(R.id.main_tabs_pager);

        mTabAccessorAdapter = new TabAccessorAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mTabAccessorAdapter);

        mtabLayout.setupWithViewPager(mViewPager);
        return rootView;

    }



}
