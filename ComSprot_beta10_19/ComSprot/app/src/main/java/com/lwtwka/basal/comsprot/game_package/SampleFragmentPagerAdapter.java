package com.lwtwka.basal.comsprot.game_package;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;


import java.util.HashMap;


/**
 * Created by leo on 2015/7/14.
 */
public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;//3
    private String tabTitles[] = new String[] { "挑戰", "地圖" ,"AR"};// "Tab3"
    FragmentManager myfm;
    String id;
    public SampleFragmentPagerAdapter(FragmentManager fm,String id) {
        super(fm);
        myfm=fm;
        this.id=id;
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
            Bundle bundle = new Bundle();
            bundle.putString("id", id);
            a.setArguments(bundle);
        }else if(position==1){
            a = new map();
        }else if(position==2){
            a = new map();
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