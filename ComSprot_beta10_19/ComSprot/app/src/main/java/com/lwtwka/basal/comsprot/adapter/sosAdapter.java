package com.lwtwka.basal.comsprot.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.lwtwka.basal.comsprot.road_race;
import com.lwtwka.basal.comsprot.shareroad;
import com.lwtwka.basal.comsprot.sos_module1;
import com.lwtwka.basal.comsprot.sos_module2;
import com.lwtwka.basal.comsprot.sos_module3;

import java.util.HashMap;


/**
 * Created by leo on 2015/7/14.
 */
public class sosAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT =3;//3
    private String tabTitles[] = new String[] { "派出所", "便利商店","旅遊中心"};// "Tab3"
    FragmentManager myfm;

    public sosAdapter(FragmentManager fm) {
        super(fm);
        myfm=fm;
    }
    HashMap mFragmentTags = new HashMap<Integer,String>();//refresh
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment a=null;
        if(position==0) {
             a = new sos_module1();
        }else if(position==1){
            a = new shareroad();
            a = new sos_module2();
           // a = new road_race();
           // a = new map();
        }else if(position==2){
            a = new sos_module3();
            // a = new road_race();
            // a = new map();
        }
        return a;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {//refresh
        Object obj = super.instantiateItem(container, position);
            if (obj instanceof Fragment) {
                    // record the fragment tag here.
                    Fragment f = (Fragment) obj;
                    String tag = f.getTag();
                    mFragmentTags.put(position, tag);
                }
            return obj;

    }
    public Fragment getFragment(int position) {//refresh
                String tag = (String) mFragmentTags.get(position);
                if (tag == null)
                        return null;
        return myfm.findFragmentByTag(tag);
        }

/*
    @Override
    public float getPageWidth(int position) {
        return 0.9f;
    }
    */
}