package com.lwtwka.basal.comsprot.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lwtwka.basal.comsprot.R;

import java.util.ArrayList;

public class road_race_adapter extends ArrayAdapter {
	Context context;
	int resource;
	ArrayList<String> datatitle;
	ArrayList<String> datadataurl;
	ArrayList<String> datacity;
	ArrayList<String> dataurl;
	public road_race_adapter(Context context, int resource,ArrayList<String> datatitle,ArrayList<String> datadataurl,ArrayList<String> datacity,ArrayList<String> dataurl) {
		super(context, resource);
		this.context = context;
		this.resource = resource;
		this.datatitle=datatitle;
		this.datadataurl=datadataurl;
		this.datacity=datacity;
		this.dataurl=dataurl;
		// TODO Auto-generated constructor stub
	}

	public int getCount() {
		return datatitle.size();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View row = inflater.inflate(R.layout.road_race_item, parent, false);//youtubethumbnailview
		TextView title=(TextView)row.findViewById(R.id.title);
		title.setText(datatitle.get(position));
		TextView date=(TextView)row.findViewById(R.id.date);
		date.setText(datadataurl.get(position).toString().replaceAll("  ", ""));
		TextView dis=(TextView)row.findViewById(R.id.dis);
		dis.setText(datacity.get(position));
		TextView url=(TextView)row.findViewById(R.id.url);
		url.setText(dataurl.get(position));

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

}
