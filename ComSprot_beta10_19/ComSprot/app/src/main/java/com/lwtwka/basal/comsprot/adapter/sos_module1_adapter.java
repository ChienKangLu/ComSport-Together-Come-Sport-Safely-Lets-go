package com.lwtwka.basal.comsprot.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.basalmainpagehaha.joinbikeActivity;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.myjson.jsontask;
import com.lwtwka.basal.comsprot.sosmap;

import java.util.ArrayList;

public class sos_module1_adapter extends ArrayAdapter {
	Context context;
	int resource;
	public ArrayList<ArrayList<String>> data;
	public sos_module1_adapter(Context context, int resource,String[]  place) {
		super(context, resource);
		place[0]=place[0].replace("台","臺");
		place[1]=place[1].replace("台","臺");
		Log.v("json",place[0]+","+place[1]);
		this.context = context;
		this.resource = resource;
		myDB mydb=new myDB(context);
		mydb.Connect();
		data=mydb.selectpolice(place);
		mydb.closedb();
		/*
		if(data.size()==0){
			ImageView hi=(ImageView)((Activity)context).findViewById(R.id.hi);
			hi.setVisibility(View.VISIBLE);
		}else{
			ImageView hi=(ImageView)((Activity)context).findViewById(R.id.hi);
			hi.setVisibility(View.GONE);
		}
		*/
		// TODO Auto-generated constructor stub
	}

	public int getCount() {
		return data.size();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View row = inflater.inflate(R.layout.sos_module1_item, parent, false);//youtubethumbnailview
		TextView title=(TextView)row.findViewById(R.id.title);
		title.setText(""+data.get(position).get(0));

		TextView address=(TextView)row.findViewById(R.id.address);
		address.setText(""+data.get(position).get(1));


		TextView phone=(TextView)row.findViewById(R.id.phone);
		phone.setText(""+data.get(position).get(2));

		ImageView gmap=(ImageView)row.findViewById(R.id.gmap);
		gmap.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(context, sosmap.class);
				Bundle bundle = new Bundle();
				bundle.putString("type", "0");
				bundle.putString("address",data.get(position).get(1));
				//將Bundle物件assign給intent
				intent.putExtras(bundle);
				context.startActivity(intent);
			}
		});

		//ImageView fav=(ImageView)row.findViewById(R.id.favorite);
		//fav.setOnClickListener(music1);


		//TextView textViewTitle = (TextView) row.findViewById(R.id.textView);
		//TextView textViewTitle1 = (TextView) row.findViewById(R.id.textView1);



		//LinearLayout ll = (LinearLayout)((Activity) context).findViewById(R.id.frame);
		//TextView tt=(TextView) row.findViewById(R.id.textView);
		//ll.removeAllViews();
		//tt.setText("gg");
		//ll.addView(tt);
		//tt.setText("hahahaha");
		//ll.addView(tt);
		
		
		//String title=(position+1)+". e";
		
		
		/*CharSequence aa=Html.fromHtml("<font color=\"#ff0000\">e</font>");*/
		//String example="‧  egg[ɛg] (n.) 蛋";
		//textViewTitle.setText(title);
		//textViewTitle1.setText(example+"\n"+example+"\n"+example);


		//textViewTitle1.setText(""+position);
		return row;
	}
	private View.OnClickListener music1 = new View.OnClickListener() {
		public void onClick(View v) {
			Toast.makeText(context, "click star~good",
					Toast.LENGTH_LONG).show();


		}
	};
/*
	private View.OnClickListener gomap = new View.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(context, sosmap.class);
			Bundle bundle = new Bundle();
			bundle.putString("address",data.get(position).get(1));
			//將Bundle物件assign給intent
			intent.putExtras(bundle);
			context.startActivity(intent);
		}
	};
*/
}
