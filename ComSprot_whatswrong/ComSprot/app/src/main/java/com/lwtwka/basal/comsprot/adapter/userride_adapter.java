package com.lwtwka.basal.comsprot.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeThumbnailLoader;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.userDetail;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class userride_adapter extends ArrayAdapter {
	Context context;
	int resource;
	ArrayList<ArrayList<String>> data=null;
	myDB mydb=null;
	public userride_adapter(Context context, int resource) {
		super(context, resource);
		this.context = context;
		this.resource = resource;
		userDetail user=new userDetail(context);
		mydb=new myDB(context);
		mydb.Connect();
		data=mydb.selectallbikerecord(user.id());
		mydb.closedb();

	}

	public int getCount() {
			return data.size();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View row = inflater.inflate(R.layout.userride_item, parent, false);//youtubethumbnailview
		TextView date=(TextView)row.findViewById(R.id.date);
		TextView time=(TextView)row.findViewById(R.id.time);;
		TextView kcal=(TextView)row.findViewById(R.id.kcal);;
		TextView distance=(TextView)row.findViewById(R.id.distance);
		TextView id=(TextView)row.findViewById(R.id.id);
		TextView favt=(TextView)row.findViewById(R.id.fav);
		//2015-08-04 00:28:53.000
		date.setText(""+data.get(position).get(0));
		time.setText("總耗費時間 "+data.get(position).get(1));
		kcal.setText(""+data.get(position).get(2)+" cal");
		String dis=data.get(position).get(3);
		String change="";
		if(Double.parseDouble(dis)<0.1){//0.01
			change=""+(int)(Double.parseDouble(dis)*1000)+"m";
		}else{
			DecimalFormat df = new DecimalFormat("##.0");
			change=""+Double.parseDouble(df.format(Double.parseDouble(dis)))+"km";
		}
		distance.setText(""+change);

		id.setText(""+data.get(position).get(4));
		favt.setText(""+data.get(position).get(5));

		ImageView fav=(ImageView)row.findViewById(R.id.favorite);
		fav.setOnClickListener(music1);

		if(favt.getText().toString().equals("0")){//不喜歡
			fav.setBackgroundResource(R.drawable.favorite2);
		}else{//喜歡
			fav.setBackgroundResource(R.drawable.favorite2_2);
		}

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
			myDB mydb=new myDB(v.getContext());
			mydb.Connect();
			//fav_good
			View parentRow = (View) v.getParent();

			ImageView fav=(ImageView)v.findViewById(R.id.favorite);
			fav.setBackgroundResource(R.drawable.fav_good);

			TextView id=(TextView)parentRow.findViewById(R.id.id);
			String idt=id.getText().toString();
			TextView favt=(TextView)parentRow.findViewById(R.id.fav);
			String b=favt.getText().toString();
			if(b.equals("0")){//變成喜歡0-->1
				fav.setBackgroundResource(R.drawable.favorite2_2);
				favt.setText("" + 1);
				mydb.updatefav("1", idt);
			}else{//變成不喜歡1-->0
				fav.setBackgroundResource(R.drawable.favorite2);
				favt.setText("" + 0);
				mydb.updatefav("0", idt);
			}
		//	Toast.makeText(context, "click star~good,"+a+","+b,
		//			Toast.LENGTH_LONG).show();


			mydb.closedb();
		}
	};

}
