package com.lwtwka.basal.comsprot;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.screenshot.CaptureScreeUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class screen_test extends ActionBarActivity {



        private TextView myTextView = null;
        private WebView myWebView = null;
        private ImageView myImageView = null;
        private ImageView myImageView_2 = null;
        private RelativeLayout rl = null;



        private Bitmap mCapture = null;
        private int mCaptureWidth = 500;
        private int mCaptureHeight = 500;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_screen_test);
            init();
            /*
            try {
                String imageUrl="http://163.14.68.47/pic1/basal.JPG";
                ImageView i = (ImageView)findViewById(R.id.myImageView);
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(imageUrl).getContent());
                i.setImageBitmap(bitmap);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            */
        }

        private void init() {
            // TODO Auto-generated method stub
            myImageView = (ImageView)findViewById(R.id.myImageView);
            myImageView_2 = (ImageView)findViewById(R.id.myImageView_2);
            rl = (RelativeLayout)findViewById(R.id.rl);



            myWebView = (WebView)findViewById(R.id.myWebView);
           // myWebView.loadUrl("https://www.google.com.tw/");
            myWebView.loadUrl("file:///android_asset/mht2.html");
            myWebView.getSettings().setJavaScriptEnabled(true);

            myTextView = (TextView) findViewById(R.id.myTextView);
            Button b = (Button) findViewById(R.id.button2);
            b.setOnClickListener(music1);
            myTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    // mCapture = CaptureScreeUtil.capture(mCaptureWidth, mCaptureHeight, myWebView);
                    //capture(myWebView,mCaptureWidth,mCaptureHeight,true,Bitmap.Config.ARGB_8888);
                    mCapture = CaptureScreeUtil.capture2(myWebView, mCaptureWidth, mCaptureHeight, true, Bitmap.Config.ARGB_8888);//CaptureScreeUtil.capture2(mCaptureWidth, mCaptureHeight, myWebView);
                    CaptureScreeUtil.screenshot(screen_test.this, mCapture, "tyrtyr");
                    updateUI();
                }
            });
        }

        private void updateUI() {
            // TODO Auto-generated method stub
            myImageView.setImageBitmap(mCapture);
            myImageView_2.setImageBitmap(CaptureScreeUtil.captureScreen(screen_test.this));/*CaptureScreeUtil.capture(mCaptureWidth,mCaptureHeight,rl)*/
        }
    private View.OnClickListener music1 = new View.OnClickListener() {
        public void onClick(View v) {
            myDB mydb=new myDB(v.getContext());
            mydb.Connect();
            ArrayList<ArrayList<String>> bikepointlatlon=mydb.bikepointlonlat("6");
            ArrayList<String> center= mydb.zoomppoint("6");
            final String markURL1 = "javascript:centerAt("+center.get(0)+","+center.get(1)+")";
           myWebView.loadUrl(markURL1);
            for(int i=0;i<bikepointlatlon.size()-1;i++){
                int a=i;
                int b=i+1;
                String lat=bikepointlatlon.get(a).get(0);
                String lon=bikepointlatlon.get(a).get(1);
                String lat2=bikepointlatlon.get(b).get(0);
                String lon2=bikepointlatlon.get(b).get(1);
                final String markURL2 = "javascript:line("+lat+","+lon+","+lat2+","+lon2+")";
                myWebView.loadUrl(markURL2);
            }
            mydb.closedb();
        }
    };
}
