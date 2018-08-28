package com.lwtwka.basal.comsprot;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.astuetz.PagerSlidingTabStrip;
import com.lwtwka.basal.comsprot.adapter.RoadAdapter;
import com.lwtwka.basal.comsprot.adapter.gameAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class game extends Fragment {


    ViewPager viewPager;
    public game() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }
    @Override
    public void onResume() {
        super.onResume();
        //
        /*
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

			/*根據螢幕大小改變數量*/
        /*
        int gridViewEntrySize = dip2px(this,165);//64
        int gridViewSpacing = dip2px(this, 10);//10
        int numColumns = (display.getWidth() - gridViewSpacing) / (gridViewEntrySize + gridViewSpacing);
        */

        //


        viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
           //viewPager.setPadding(100, 0, 100, 0);
           //viewPager.setClipToPadding(false);
        /*
        *
        * */
        //pager settings
        //int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20 * 2, getResources().getDisplayMetrics());
       // viewPager.setPageMargin(-margin);

       // viewPager.setPadding(70, 0, 70, 0);
      //  viewPager.setClipToPadding(false);

        //viewPager.setPageMargin(10);

        /*
        *
        */
        viewPager.setAdapter(new gameAdapter(getChildFragmentManager(),getActivity()));
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

        //PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) getActivity().findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
       // tabsStrip.setViewPager(viewPager);
      //  viewPager.setTag("aaa");
    }
}
