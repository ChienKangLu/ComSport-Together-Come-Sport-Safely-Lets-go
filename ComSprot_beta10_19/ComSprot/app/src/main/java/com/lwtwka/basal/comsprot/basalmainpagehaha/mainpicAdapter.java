package com.lwtwka.basal.comsprot.basalmainpagehaha;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.trycard;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by leo on 2015/7/14.
 */
public class mainpicAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT =10;//3
    FragmentManager myfm;
    ArrayList<ArrayList<String>> data;
    int pic[]={R.drawable.bs1,R.drawable.bs2,R.drawable.bs3,R.drawable.bs4,R.drawable.bs5};
    public mainpicAdapter(FragmentManager fm, Context context) {
        super(fm);
        myfm=fm;
        myDB mydb=new myDB(context);
        mydb.Connect();
       // data=mydb.selectshowgamebikerecord();
        mydb.closedb();
    }
    HashMap mFragmentTags = new HashMap<Integer,String>();//refresh
    @Override
    public int getCount() {
       // Log.v("gamehaha",""+data.size());
        return pic.length;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment a=null;
        a = new mainpic();
        Bundle bundle = new Bundle();
        bundle.putInt("pic",pic[position]);
        a.setArguments(bundle);
        return a;
    }
/*
    @Override
    public float getPageWidth(int position) {
        return (float) 0.9;
    }
*/
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return ""+(position+1);
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