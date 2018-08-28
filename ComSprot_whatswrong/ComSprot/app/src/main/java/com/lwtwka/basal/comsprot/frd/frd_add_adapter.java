package com.lwtwka.basal.comsprot.frd;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.koushikdutta.urlimageviewhelper.UrlImageViewCallback;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.userDetail;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class frd_add_adapter extends ArrayAdapter {
	Context context;
	int resource;
	myDB mydb;
	ArrayList<ArrayList<String>> data=null;
	ArrayList<ArrayList<ArrayList<String>>>  analystdetail=null;
	int pic[]={R.drawable.image44,R.drawable.image47,R.drawable.image54,R.drawable.image103,R.drawable.image104};//11

	public frd_add_adapter(Context context, int resource) {
		super(context, resource);
		this.context = context;
		this.resource = resource;
		mydb=new myDB(context);
		mydb.Connect();
		userDetail user=new userDetail(context);
		data=mydb.selectsend("" + user.id());

		mydb.closedb();
		//Toast.makeText(context,""+analyst.size(),Toast.LENGTH_LONG).show();
		// TODO Auto-generated constructor stub
	}

	public int getCount() {
		return data.size();//analyst.size()
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View row = inflater.inflate(R.layout.frd_add_item, parent, false);//youtubethumbnailview
		CircleImageView im=(CircleImageView)row.findViewById(R.id.profile_image);

		//ImageView roadpic = (ImageView) row.findViewById(R.id.roadpic);
		//String name="image"+truedata.get(position).get(7);
		UrlImageViewHelper.setUrlDrawable(im, "http://163.14.68.47/map/" + "image" + data.get(position).get(1) + ".png", R.drawable.circlepichaha, new UrlImageViewCallback() {
			@Override
			public void onLoaded(ImageView imageView, Bitmap loadedBitmap, String url, boolean loadedFromCache) {
				//if (!loadedFromCache) {
				ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
				scale.setDuration(300);
				scale.setInterpolator(new OvershootInterpolator());
				imageView.startAnimation(scale);
				//}
			}
		});
		CircleImageView send=(CircleImageView)row.findViewById(R.id.send);
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mydb=new myDB(context);
				mydb.Connect();
				userDetail user=new userDetail(context);
				mydb.insertsend("" + user.id(), "" + data.get(position).get(0));
				mydb.closedb();
				data.remove(position);
				notifyDataSetChanged();
				//((ActionBarActivity)context).finish();
			}
		});

		return row;
	}
	private View.OnClickListener music1 = new View.OnClickListener() {
		public void onClick(View v) {
			Toast.makeText(context, "click star~good",
					Toast.LENGTH_LONG).show();


		}
	};

}
