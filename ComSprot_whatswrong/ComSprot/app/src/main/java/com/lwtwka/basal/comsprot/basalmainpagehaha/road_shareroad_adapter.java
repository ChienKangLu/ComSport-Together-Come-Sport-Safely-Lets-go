package com.lwtwka.basal.comsprot.basalmainpagehaha;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
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

import java.text.DecimalFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class road_shareroad_adapter extends ArrayAdapter {
	Context context;
	int resource;
	ArrayList<ArrayList<String>> data=null;
	public road_shareroad_adapter(Context context, int resource) {
		super(context, resource);
		this.context = context;
		this.resource = resource;
		myDB mydb=new myDB(context);
		mydb.Connect();
		data=mydb.selectallbikerecordshare2();
		// TODO Auto-generated constructor stub
	}

	public int getCount() {
			return data.size();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			View row=null ;

			if(position!=data.size()) {
				row = inflater.inflate(R.layout.shareroad_main_item, parent, false);//youtubethumbnailview
			ImageView roadpic = (ImageView) row.findViewById(R.id.roadpic);
			//String name="image"+truedata.get(position).get(7);
				UrlImageViewHelper.setUrlDrawable(roadpic, "http://163.14.68.47/map/" + "image" + data.get(position).get(1) + ".png", R.drawable.map_loading1, new UrlImageViewCallback() {
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

			//new DownloadImageTask(roadpic)
			//		.execute("http://163.14.68.47/map/" + "image" + data.get(position).get(1) + ".png");
			TextView title = (TextView) row.findViewById(R.id.title);
			title.setText(data.get(position).get(2));
			TextView descript = (TextView) row.findViewById(R.id.descript);
			String startname = data.get(position).get(6);
			String endname = data.get(position).get(7);
			descript.setText(startname + "-" + endname);
			TextView dist = (TextView) row.findViewById(R.id.dis);
			TextView id = (TextView) row.findViewById(R.id.id);
			id.setText(data.get(position).get(0));
			String dis = data.get(position).get(3);
			String change = "";
			if (Double.parseDouble(dis) < 0.1) {//0.01
				change = "" + (int) (Double.parseDouble(dis) * 1000) + "m";
			} else {
				DecimalFormat df = new DecimalFormat("##.0");
				change = "" + Double.parseDouble(df.format(Double.parseDouble(dis))) + "km";
			}
			dist.setText(change);
			TextView go = (TextView) row.findViewById(R.id.go);
			go.setText(data.get(position).get(4));
				CircleImageView profile_image=(CircleImageView)row.findViewById(R.id.profile_image);
				UrlImageViewHelper.setUrlDrawable(profile_image, "http://163.14.68.47/map/" + "image" + data.get(position).get(5) + ".png", R.drawable.personal_icon_non, new UrlImageViewCallback() {
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


                String totaltime = data.get(position).get(8);
                String totaldis = data.get(position).get(3);
                /*
                String avgspeed = bundle.getString("avgspeed");
                String totalcal = bundle.getString("totalcal");
                String maxspeed = bundle.getString("maxspeed");
                String maxalt = bundle.getString("maxalt");
                String avgalt = bundle.getString("avgalt");
                */
                String realdis =totaldis;

                //A~B~C~D~E~F
                //20~40~60~80~100
                int score1=0;
                int score2=0;
                double scoretime=counttime(totaltime);
                if(scoretime>=0&&scoretime<5){
                    score1=10;
                }else if(scoretime>=5&&scoretime<10){
                    score1=20;
                }else if(scoretime>=10&&scoretime<15){
                    score1=30;
                }else if(scoretime>=15&&scoretime<20){
                    score1=40;
                }else if(scoretime>=20){
                    score1=50;
                }
                double scoredis=Double.parseDouble(realdis);
                if(scoredis>=0&&scoredis<0.1){
                    score2=10;
                }else if(scoredis>=0.1&&scoredis<0.5){
                    score2=20;
                }else if(scoredis>=0.5&&scoredis<1){
                    score2=30;
                }else if(scoredis>=1&&scoredis<1.5){
                    score2=40;
                }else if(scoredis>=1.5){
                    score2=50;
                }
                int finalscore=score1+score2;
                Log.v("score", "" + finalscore);
                //A~B~C~D~E~F
                //20~40~60~80~100
                //~~~~~~~~~~~~~~~~~~``
               // ImageView level=(ImageView)row.findViewById(R.id.catgr);
                ImageView star1=(ImageView)row.findViewById(R.id.star1);
                ImageView star2=(ImageView)row.findViewById(R.id.star2);
                ImageView star3=(ImageView)row.findViewById(R.id.star3);
                ImageView star4=(ImageView)row.findViewById(R.id.star4);
                ImageView star5=(ImageView)row.findViewById(R.id.star5);

                if(finalscore>=0&&finalscore<20){//1
                    //level.setImageResource(R.drawable.veryeasy_icon);
                    star5.setImageResource(R.drawable.favorite2);
                    star4.setImageResource(R.drawable.favorite2);
                    star3.setImageResource(R.drawable.favorite2);
                    star2.setImageResource(R.drawable.favorite2);

                }else if(finalscore>=20&&finalscore<40){//2v
                   // level.setImageResource(R.drawable.easy_icon);
                    star5.setImageResource(R.drawable.favorite2);
                    star4.setImageResource(R.drawable.favorite2);
                    star3.setImageResource(R.drawable.favorite2);
                }else if(finalscore>=40&&finalscore<60){//3v
                    //level.setImageResource(R.drawable.com_icon);
                    star5.setImageResource(R.drawable.favorite2);
                    star4.setImageResource(R.drawable.favorite2);
                }else if(finalscore>=60&&finalscore<80){//4
                    //level.setImageResource(R.drawable.hard_icon);
                    star5.setImageResource(R.drawable.favorite2);
                }else if(finalscore>=80){//5v
                    //level.setImageResource(R.drawable.veryhard_icon);
                }

                /////////
                ///////////
                //////////////
                ////////////////////
			//new DownloadImageTask2(profile_image)
					//			.execute("http://farm8.staticflickr.com/7315/9046944633_881f24c4fa_s.jpg")
			//		.execute("http://163.14.68.47/map/" + "image" + data.get(position).get(5) + ".png");//image38*/


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
		}/*else{
				row = inflater.inflate(R.layout.lastpage, parent, false);//youtubethumbnailview
			}*/
		return row;
	}
	private View.OnClickListener music1 = new View.OnClickListener() {
		public void onClick(View v) {
			Toast.makeText(context, "click star~good",
					Toast.LENGTH_LONG).show();


		}
	};
	/*
	* final WebView w=(WebView)row.findViewById(R.id.roadpic);
		w.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return super.shouldOverrideUrlLoading(view, url);

			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				myDB mydb = new myDB(context);
				mydb.Connect();
				ArrayList<ArrayList<String>> bikepointlatlon = mydb.bikepointlonlat("6");
				ArrayList<String> center = mydb.zoomppoint("6");
				final String markURL1 = "javascript:centerAt(" + center.get(0) + "," + center.get(1) + ")";
				w.loadUrl(markURL1);
				for (int i = 0; i < bikepointlatlon.size() - 1; i++) {
					int a = i;
					int b = i + 1;
					String lat = bikepointlatlon.get(a).get(0);
					String lon = bikepointlatlon.get(a).get(1);
					String lat2 = bikepointlatlon.get(b).get(0);
					String lon2 = bikepointlatlon.get(b).get(1);
					final String markURL2 = "javascript:line(" + lat + "," + lon + "," + lat2 + "," + lon2 + ")";
					w.loadUrl(markURL2);
				}
				mydb.closedb();

			}
		});
		w.getSettings().setJavaScriptEnabled(true);
		w.loadUrl("file:///android_asset/mht3.html");*/

    public double counttime(String a){
        //String a="00'00'20";
        String time[]=a.split(":");
        int second=0;
        for(int i=0;i<3;i++){
            int b=Integer.parseInt(time[i]);
            if(i==0){
                b=b*60*60;
            }else if(i==1){
                b=b*60;
            }else{
                b=b;
            }
            second+=b;
        }
        double second2=(double)second/(60);
        System.out.println(second2);//單位分鐘
        return second2;
    }
}
