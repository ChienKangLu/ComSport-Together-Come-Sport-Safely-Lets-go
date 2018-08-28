package com.lwtwka.basal.comsprot.game_package;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
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
import com.lwtwka.basal.comsprot.mayAsyncTask.DownloadImageTask;
import com.lwtwka.basal.comsprot.mayAsyncTask.DownloadImageTask2;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class rank_adapter extends ArrayAdapter {
	Context context;
	int resource;
	ArrayList<ArrayList<String>> data;
	public rank_adapter(Context context, int resource,String id) {
		super(context, resource);
		this.context = context;
		this.resource = resource;
		myDB mydb=new myDB(context);
		mydb.Connect();
		data=mydb.selectallrank(id);
		mydb.closedb();
		// TODO Auto-generated constructorub
	}

	public int getCount() {
		return data.size();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View row = inflater.inflate(R.layout.rank_item, parent, false);//youtubethumbnailview
		ImageView fav=(ImageView)row.findViewById(R.id.n);
		if(position!=0){
			fav.setVisibility(View.GONE);
		}
		//ImageView pic=(ImageView)row.findViewById(R.id.pic);


		CircleImageView profile_image=(CircleImageView)row.findViewById(R.id.pic);
		UrlImageViewHelper.setUrlDrawable(profile_image, "http://163.14.68.47/map/" + "image" + data.get(position).get(1) + ".png", R.drawable.circlepichaha, new UrlImageViewCallback() {
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

		//new DownloadImageTask2(pic)
		//		.execute("http://163.14.68.47/map/" + "image" + data.get(position).get(1) + ".png");



		TextView num=(TextView)row.findViewById(R.id.ranknumber);
		num.setText(""+(position+1));
		TextView name=(TextView)row.findViewById(R.id.name);
		name.setText(data.get(position).get(2).toString());
		TextView time=(TextView)row.findViewById(R.id.time);
		time.setText(data.get(position).get(3).toString());

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
