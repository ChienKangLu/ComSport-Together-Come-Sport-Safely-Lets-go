package com.lwtwka.basal.comsprot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.lwtwka.basal.comsprot.adapter.RoadAdapter;
import com.lwtwka.basal.comsprot.adapter.SampleFragmentPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class road extends Fragment {

    ViewPager viewPager;

    public road() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_road, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
        viewPager.setAdapter(new RoadAdapter(getChildFragmentManager()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) getActivity().findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
        viewPager.setTag("aaa");
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
