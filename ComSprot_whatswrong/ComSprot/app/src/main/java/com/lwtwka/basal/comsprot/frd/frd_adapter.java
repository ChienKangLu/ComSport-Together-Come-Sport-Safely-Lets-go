package com.lwtwka.basal.comsprot.frd;

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
import com.lwtwka.basal.comsprot.userDetail;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class frd_adapter extends ArrayAdapter {
	Context context;
	int resource;
	myDB mydb;
	//ArrayList<ArrayList<String>> analyst=null;
	//ArrayList<ArrayList<ArrayList<String>>>  analystdetail=null;
	ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
	int pic[]={R.drawable.image44,R.drawable.image47,R.drawable.image54,R.drawable.image103,R.drawable.image104};//11

	public frd_adapter(Context context, int resource) {
		super(context, resource);
		this.context = context;
		this.resource = resource;
		mydb=new myDB(context);
		mydb.Connect();
		userDetail user=new userDetail(context);

		data=mydb.selectfrd(""+user.id());
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
		View row = inflater.inflate(R.layout.frd_item, parent, false);//youtubethumbnailview
		CircleImageView im=(CircleImageView)row.findViewById(R.id.profile_image);

		//ImageView roadpic = (ImageView) row.findViewById(R.id.roadpic);
		//String name="image"+truedata.get(position).get(7);
		UrlImageViewHelper.setUrlDrawable(im, "http://163.14.68.47/map/" + "image" + data.get(position).get(2) + ".png",R.drawable.circlepichaha, new UrlImageViewCallback() {
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

		CircleImageView online=(CircleImageView)row.findViewById(R.id.online);
		if(data.get(position).get(3).equals("0")){//下線
			online.setImageResource(R.drawable.online2);
		 }else{
			online.setImageResource(R.drawable.online1);
		}



		//im.setImageResource(pic[position%5]);
		//TextView roadname=(TextView)row.findViewById(R.id.roadname);
		//TextView date=(TextView)row.findViewById(R.id.date);

		return row;
	}
	private View.OnClickListener music1 = new View.OnClickListener() {
		public void onClick(View v) {
			Toast.makeText(context, "click star~good",
					Toast.LENGTH_LONG).show();


		}
	};

}
