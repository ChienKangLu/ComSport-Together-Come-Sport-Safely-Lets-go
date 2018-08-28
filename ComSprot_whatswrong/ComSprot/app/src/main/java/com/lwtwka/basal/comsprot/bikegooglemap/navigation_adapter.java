package com.lwtwka.basal.comsprot.bikegooglemap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.urlimageviewhelper.UrlImageViewCallback;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.userDetail;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class navigation_adapter extends ArrayAdapter {
	Context context;
	int resource;
	myDB mydb;
	ArrayList<ArrayList<String>> data=null;
	//ArrayList<ArrayList<ArrayList<String>>>  analystdetail=null;
	public navigation_adapter(Context context, int resource) {
		super(context, resource);
		this.context = context;
		this.resource = resource;

		mydb=new myDB(context);
		mydb.Connect();
		//userDetail user=new userDetail(context);
		data=mydb.selectnav();
/*
		analystdetail=new ArrayList<ArrayList<ArrayList<String>>>();
		for(int i=0;i<analyst.size();i++){
			analystdetail.add(mydb.selectanalystdetail(analyst.get(i).get(0)));
		}
*/
		mydb.closedb();

		//Toast.makeText(context,""+analyst.size(),Toast.LENGTH_LONG).show();
		// TODO Auto-generated constructor stub
	}

	public int getCount() {
		return 10;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View row = inflater.inflate(R.layout.navigation_item, parent, false);//youtubethumbnailview
		TextView start=(TextView)row.findViewById(R.id.start);
		start.setText(data.get(position).get(1));
		TextView end=(TextView)row.findViewById(R.id.end);
		end.setText(data.get(position).get(2));
		TextView title=(TextView)row.findViewById(R.id.title);
		title.setText(data.get(position).get(3));

		//TextView s=(TextView)row.findViewById(R.id.s);
		//s.setText(data.get(position).get(2));
		ImageView startpoint=(ImageView)row.findViewById(R.id.startpoint);
		startpoint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String id = data.get(position).get(0);
				//Toast.makeText(context,""+id,Toast.LENGTH_LONG).show();
				mydb = new myDB(context);
				mydb.Connect();
				ArrayList<ArrayList<String>> loc = mydb.selecstart(id);
				mydb.closedb();

				String lat = loc.get(0).get(0);
				String lon = loc.get(0).get(1);
				Uri gmmIntentUri = Uri.parse("google.navigation:q=" + lat + "," + lon);//google.navigation:q=latitude,longitude
				//google.navigation:q=Taronga+Zoo,+Sydney+Australia
				Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
				mapIntent.setPackage("com.google.android.apps.maps");
				context.startActivity(mapIntent);

			}
		});
		CircleImageView profile_image=(CircleImageView)row.findViewById(R.id.profile_image);
		UrlImageViewHelper.setUrlDrawable(profile_image, "http://163.14.68.47/map/" + "image" + data.get(position).get(4) + ".png", R.drawable.circlepichaha, new UrlImageViewCallback() {
			@Override
			public void onLoaded(ImageView imageView, Bitmap loadedBitmap, String url, boolean loadedFromCache) {
				if (!loadedFromCache) {
					ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
					scale.setDuration(300);
					scale.setInterpolator(new OvershootInterpolator());
					imageView.startAnimation(scale);
				}
			}
		});
		ImageView endpoint=(ImageView)row.findViewById(R.id.endpoint);
		endpoint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String id=data.get(position).get(0);
				//Toast.makeText(context,""+id,Toast.LENGTH_LONG).show();
				mydb=new myDB(context);
				mydb.Connect();
				ArrayList<ArrayList<String>> loc=mydb.selecend(id);
				mydb.closedb();

				String lat=loc.get(0).get(0);
				String lon=loc.get(0).get(1);
				Uri gmmIntentUri = Uri.parse("google.navigation:q="+lat+","+lon);//google.navigation:q=latitude,longitude
				//google.navigation:q=Taronga+Zoo,+Sydney+Australia
				Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
				mapIntent.setPackage("com.google.android.apps.maps");
				context.startActivity(mapIntent);
			}
		});




		return row;
	}

}
