package com.lwtwka.basal.comsprot.basalmainpagehaha;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.joint_sentence;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;

public class joinitem extends ArrayAdapter {
	Context context;
	int resource;
    ArrayList<Integer> color;

    int colordata[]={R.color.j1,R.color.j2,R.color.j3,R.color.j4,R.color.j5,R.color.j6,R.color.j7};
	public joinitem(Context context, int resource) {
		super(context, resource);
		this.context = context;
		this.resource = resource;
        color=new ArrayList<Integer>();
        for(int i=0;i<colordata.length;i++){
            color.add(colordata[i]);
        }

		// TODO Auto-generated constructor stub
	}

	public int getCount() {
		return color.size();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View row =null;


            row = inflater.inflate(R.layout.activity_swipe, parent, false);//jointext
            TextView tx=(TextView)row.findViewById(R.id.text);
            tx.setBackgroundColor(context.getResources().getColor(color.get(position)));
            ///////
            SwipeLayout sample2 = (SwipeLayout) row.findViewById(R.id.haha);
            sample2.setShowMode(SwipeLayout.ShowMode.LayDown);
            sample2.addDrag(SwipeLayout.DragEdge.Right, sample2.findViewWithTag("Bottom2"));
//        sample2.setShowMode(SwipeLayout.ShowMode.PullOut);
            sample2.findViewById(R.id.star).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                            Intent intent = new Intent();
                            intent.setClass(context, joint_sentence.class);
                            context.startActivity(intent);

                }
            });
/*
        sample2.findViewById(R.id.trash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Trash Bin", Toast.LENGTH_SHORT).show();
            }
        });
*/
            sample2.findViewById(R.id.magnifier).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Magnifier", Toast.LENGTH_SHORT).show();
                    color.remove(position);
                    notifyDataSetChanged();
                }
            });

            sample2.findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Yo", Toast.LENGTH_SHORT).show();
                }
            });
            sample2.getSurfaceView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Click on surface", Toast.LENGTH_SHORT).show();
                }
            });

		return row;
	}

}
