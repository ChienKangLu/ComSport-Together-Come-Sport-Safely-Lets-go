package com.lwtwka.basal.comsprot.basalmainpagehaha;


import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lwtwka.basal.comsprot.R;

class MyPagerAdapter extends PagerAdapter {
    int[] pic;
    Context context;
    public MyPagerAdapter(int[] pic,Context context) {
        this.pic = pic;
        this.context=context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        /*
        ImageView view=new ImageView(context);
        if (position == 0) {
            view.setImageResource(pic[2]);
        } else if (position == ((pic.length+2) - 1)) {
            view.setImageResource(pic[0]);
        } else {
            view.setImageResource(pic[position-1]);
        }

        container.addView(view);
        */
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.layout, container, false);
        ImageView im=(ImageView)row.findViewById(R.id.im);
        if (position == 0) {
            im.setImageResource(pic[pic.length-1]);
        } else if (position == ((pic.length+2) - 1)) {
            im.setImageResource(pic[0]);
        } else {
            im.setImageResource(pic[position-1]);
        }

        container.addView(row);
        return row;
        //return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return pic.length+2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

}
