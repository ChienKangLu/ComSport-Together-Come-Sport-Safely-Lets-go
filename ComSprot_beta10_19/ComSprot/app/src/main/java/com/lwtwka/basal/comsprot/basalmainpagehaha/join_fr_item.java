package com.lwtwka.basal.comsprot.basalmainpagehaha;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.joinbike_choosewho;
import com.lwtwka.basal.comsprot.joint_sentence;

import java.util.ArrayList;

public class join_fr_item extends ArrayAdapter {
	Context context;
	int resource;
    ArrayList<Integer> color;
    ArrayList<String> name;
    int colordata[]={R.color.j1,R.color.j2,R.color.j3,R.color.j4,R.color.j5,R.color.j6,R.color.j7};
    String namedata[]={"陸建綱","王慧縈","柯浩元","蔡穎珊","吳宛穎","鄭麗珍老師","你好"};
	public join_fr_item(Context context, int resource) {
		super(context, resource);
		this.context = context;
		this.resource = resource;
        color=new ArrayList<Integer>();
        for(int i=0;i<colordata.length;i++){
            color.add(colordata[i]);
        }

        name=new ArrayList<String>();
        for(int i=0;i<namedata.length;i++){
            name.add(namedata[i]);
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


            row = inflater.inflate(R.layout.jointext, parent, false);//jointext
            TextView tx=(TextView)row.findViewById(R.id.text);
            tx.setText(name.get(position));
            tx.setBackgroundColor(context.getResources().getColor(color.get(position)));
            tx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(context, joint_sentence.class);
                    ((Activity) context).finish();
                    context.startActivity(intent);
                }
            });

		return row;
	}

}
