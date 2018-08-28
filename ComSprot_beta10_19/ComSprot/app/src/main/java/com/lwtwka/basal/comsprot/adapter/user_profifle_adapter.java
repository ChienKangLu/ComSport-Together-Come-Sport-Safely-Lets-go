package com.lwtwka.basal.comsprot.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.userDetail;

public class user_profifle_adapter extends ArrayAdapter {
	Context context;
	int resource;
	String title[]={"身高","體重","BMI","年齡","email"};
	String value[]={"170 cm","70 kg","23","21 歲",""};
	public user_profifle_adapter(Context context, int resource) {
		super(context, resource);
		this.context = context;
		this.resource = resource;
		userDetail user =new userDetail(context);
		value[0]=user.height();
		value[1]=""+user.weight();
		value[2]="";
		value[3]=user.age();
		value[4]=user.email();
		// TODO Auto-generated constructor stub
	}

	public int getCount() {
			return title.length;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View row = inflater.inflate(R.layout.user_profile_item, parent, false);//youtubethumbnailview
		TextView a=(TextView)row.findViewById(R.id.title);
		TextView b=(TextView)row.findViewById(R.id.value);
		a.setText(""+title[position]);
		b.setText(""+value[position]);
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
	/*
	private View.OnClickListener music1 = new View.OnClickListener() {
		public void onClick(View v) {
			Toast.makeText(context, "click star~good",
					Toast.LENGTH_LONG).show();


		}
	};
	*/

}
