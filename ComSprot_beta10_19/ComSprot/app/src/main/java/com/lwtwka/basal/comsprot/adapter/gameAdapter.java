package com.lwtwka.basal.comsprot.adapter;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.road_race;
import com.lwtwka.basal.comsprot.shareroad;
import com.lwtwka.basal.comsprot.trycard;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by leo on 2015/7/14.
 */
public class gameAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT =10;//3
    FragmentManager myfm;
    ArrayList<ArrayList<String>> data;
    public gameAdapter(FragmentManager fm,Context context) {
        super(fm);
        myfm=fm;
        myDB mydb=new myDB(context);
        mydb.Connect();
        data=mydb.selectshowgamebikerecord();
        mydb.closedb();

    }
    HashMap mFragmentTags = new HashMap<Integer,String>();//refresh
    @Override
    public int getCount() {
        Log.v("gamehaha",""+data.size());
        return data.size();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment a=null;
        a = new trycard();
        Bundle bundle = new Bundle();
        bundle.putString("totaltime", ""+data.get(position).get(0));//position
        bundle.putString("totaldis", ""+data.get(position).get(1));//position
        bundle.putString("avgspeed", ""+data.get(position).get(2));//position
        bundle.putString("totalcal", ""+data.get(position).get(3));//position
        bundle.putString("maxspeed", ""+data.get(position).get(4));//position
        bundle.putString("maxalt", ""+data.get(position).get(5));//position
        bundle.putString("avgalt", ""+data.get(position).get(6));//position
        bundle.putString("title", ""+data.get(position).get(7));//position
        bundle.putString("pic", ""+data.get(position).get(8));//position
        bundle.putString("name", ""+data.get(position).get(9));//position
        bundle.putString("id", ""+data.get(position).get(10));//position
        bundle.putString("realdis", ""+data.get(position).get(11));//position
        a.setArguments(bundle);
        return a;
    }

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