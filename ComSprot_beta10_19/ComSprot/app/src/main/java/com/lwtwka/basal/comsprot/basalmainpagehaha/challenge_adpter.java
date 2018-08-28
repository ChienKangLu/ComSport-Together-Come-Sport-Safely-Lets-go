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

import java.text.DecimalFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class challenge_adpter extends ArrayAdapter {
	Context context;
	int resource;
	//ArrayList<ArrayList<String>> data=null;
	int pic[]={R.drawable.image44,R.drawable.image47,R.drawable.image54,R.drawable.image103,R.drawable.image104};//11
	public challenge_adpter(Context context, int resource) {
		super(context, resource);
		this.context = context;
		this.resource = resource;
		/*
		myDB mydb=new myDB(context);
		mydb.Connect();
		data=mydb.selectallbikerecordshare2();
		*/
		// TODO Auto-generated constructor stub
	}
	int count=10;
	public int getCount() {
			return 2+count*2+1+2*2;////1)2-->頭尾,2)-->每個的自己加上一個分隔unit,3)-->尾巴的unit,4)最後兩個自己加上一個分隔unit
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			View row=null ;
		if(position==0) {//0
			row = inflater.inflate(R.layout.challenge_list1, parent, false);//youtubethumbnailview
			ImageView im=(ImageView)row.findViewById(R.id.im);
			im.setImageResource(pic[position%5]);
		}else if(position%4==0){//4 8 12 16
			row = inflater.inflate(R.layout.challenge_list3, parent, false);
			ImageView im=(ImageView)row.findViewById(R.id.im);
			im.setImageResource(pic[position%5]);
		}
		else if(position%2!=0){//1 3 5 7 9
			row = inflater.inflate(R.layout.challenge_unit, parent, false);
		}
		else if(position%2==0) {//2 4 6 8 10
			row = inflater.inflate(R.layout.challenge_list2, parent, false);
			ImageView im=(ImageView)row.findViewById(R.id.im);
			im.setImageResource(pic[position%5]);
		}
		if(position==getCount()-2||position==getCount()-3){//最後一個
			row = inflater.inflate(R.layout.challenge_nobody, parent, false);
		}

		if(position==getCount()-1){//最後一個
			row = inflater.inflate(R.layout.challenge_botton, parent, false);
		}
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
