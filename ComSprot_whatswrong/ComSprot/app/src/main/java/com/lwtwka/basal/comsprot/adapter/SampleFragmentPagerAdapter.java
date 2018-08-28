package com.lwtwka.basal.comsprot.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.lwtwka.basal.comsprot.activity.f1;
import com.lwtwka.basal.comsprot.activity.f2;
import com.lwtwka.basal.comsprot.activity.f3;
import com.lwtwka.basal.comsprot.map;

import java.util.HashMap;


/**
 * Created by leo on 2015/7/14.
 */
public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;//3
    private String tabTitles[] = new String[] { "統計資料", "地圖"};// "Tab3"
    FragmentManager myfm;

    public SampleFragmentPagerAdapter(FragmentManager fm) {
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
            a = new f1();
        }else if(position==1){
            a = new map();
        }else if(position==2){
            a = new f3();
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


}