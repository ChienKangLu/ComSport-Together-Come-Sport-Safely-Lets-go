package com.lwtwka.basal.comsprot.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import de.hdodenhof.circleimageview.CircleImageView;

public class road_shareroad_adapter extends ArrayAdapter {
	Context context;
	int resource;
	ArrayList<ArrayList<String>> data=null;

	public ArrayList<DownloadImageTask> task;
	public ArrayList<DownloadImageTask2> task2;
	/*
/////////////////
private static final int CORE_POOL_SIZE = 5;
	private static final int MAXIMUM_POOL_SIZE = 128;
	private static final int KEEP_ALIVE = 10;

	private static final BlockingQueue<Runnable> sWorkQueue =
			new LinkedBlockingQueue<Runnable>(10);

	private static final ThreadFactory sThreadFactory = new ThreadFactory() {
		private final AtomicInteger mCount = new AtomicInteger(1);

		public Thread newThread(Runnable r) {
			return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
		}
	};
	ThreadPoolExecutor sExecutor;
	*/
	public road_shareroad_adapter(Context context, int resource) {
		super(context, resource);
		this.context = context;
		this.resource = resource;
		myDB mydb=new myDB(context);
		mydb.Connect();
		data=mydb.selectallbikerecordshare();
		task=new ArrayList<DownloadImageTask>();
		task2=new ArrayList<DownloadImageTask2>();

	//	sExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,
	//			MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, sWorkQueue, sThreadFactory, new ThreadPoolExecutor.DiscardOldestPolicy());

		// TODO Auto-generated constructor stub
	}

	public int getCount() {
			return data.size();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View row = inflater.inflate(R.layout.shareroad_item, parent, false);//youtubethumbnailview
		ImageView roadpic=(ImageView)row.findViewById(R.id.roadpic);
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
		/*
		DownloadImageTask taska=new DownloadImageTask(roadpic);
				taska.execute("http://163.14.68.47/map/" + "image" + data.get(position).get(1) + ".png");*/
		TextView title=(TextView)row.findViewById(R.id.title);
		title.setText(data.get(position).get(2));
		TextView descript=(TextView)row.findViewById(R.id.descript);
		String startname=data.get(position).get(6);
		String endname=data.get(position).get(7);
		descript.setText(startname+"-"+endname);
		TextView dist=(TextView)row.findViewById(R.id.dis);
		TextView id=(TextView)row.findViewById(R.id.id);
		id.setText(data.get(position).get(0));
		String dis=data.get(position).get(3);
		String change="";
		if(Double.parseDouble(dis)<0.1){//0.01
			change=""+(int)(Double.parseDouble(dis)*1000)+"m";
		}else{
			DecimalFormat df = new DecimalFormat("##.0");
			change=""+Double.parseDouble(df.format(Double.parseDouble(dis)))+"km";
		}
		dist.setText(change);
		TextView go=(TextView)row.findViewById(R.id.go);
		go.setText("路線由 "+data.get(position).get(4)+" 建立");

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
		//lalalalalal
		//DownloadImageTask2 taskb=new DownloadImageTask2(profile_image);
		//taskb.execute("http://163.14.68.47/map/" + "image" + data.get(position).get(5) + ".png");//image38
		//task.add(taska);
		//task2.add(taskb);
		/*
		try {
			btask.executeOnExecutor(sExecutor, "http://163.14.68.47/map/" + "image" + data.get(position).get(5) + ".png");//image38

		}catch(RejectedExecutionException e){
			sExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,
					MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, sWorkQueue, sThreadFactory, new ThreadPoolExecutor.DiscardOldestPolicy());

			btask.executeOnExecutor(sExecutor, "http://163.14.68.47/map/" + "image" + data.get(position).get(5) + ".png");//image38

		}
	*/

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


}
