package com.lwtwka.basal.comsprot.basalmainpagehaha;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.joint_sentence;

import java.util.ArrayList;

public class join_sent_item extends ArrayAdapter {
	Context context;
	int resource;
    ArrayList<Integer> color;
    ArrayList<String> name;
    int colordata[]={R.color.j1,R.color.j2,R.color.j3,R.color.j4,R.color.j5,R.color.j6,R.color.j7};
    String namedata[]={"出來騎車阿廢物","尬車阿","天氣超好的","別宅了","你的公路車在呼喚你","你看不到我的車尾燈","不要這麼懶"};
	public join_sent_item(Context context, int resource) {
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
                ((Activity) context).finish();
            }
        });

		return row;
	}

}
