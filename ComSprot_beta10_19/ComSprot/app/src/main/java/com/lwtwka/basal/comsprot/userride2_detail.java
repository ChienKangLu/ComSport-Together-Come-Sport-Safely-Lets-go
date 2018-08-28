package com.lwtwka.basal.comsprot;


import java.util.ArrayList;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.dialog.analystdialog;
import com.lwtwka.basal.comsprot.dialog.titledialog;
import com.lwtwka.basal.comsprot.dialog.userridedialog;
import com.lwtwka.basal.comsprot.tools.WeatherIconMapper;

import android.app.DialogFragment;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class userride2_detail extends ActionBarActivity{
    WebView webview=null;
    String id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userride2_detail);
//////////////
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /////////
        webview=(WebView)findViewById(R.id.webView);
        webview.setWebViewClient(new WebViewClient() {
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
                myDB mydb=new myDB(userride2_detail.this);
                mydb.Connect();
                ArrayList<ArrayList<String>> bikepointlatlon=mydb.bikepointlonlat(id);
                ArrayList<String> center= mydb.zoomppoint(id);
                final String markURL1 = "javascript:centerAt("+center.get(0)+","+center.get(1)+")";
                webview.loadUrl(markURL1);
                for(int i=0;i<bikepointlatlon.size()-1;i++){
                    int a=i;
                    int b=i+1;
                    String lat=bikepointlatlon.get(a).get(0);
                    String lon=bikepointlatlon.get(a).get(1);
                    String lat2=bikepointlatlon.get(b).get(0);
                    String lon2=bikepointlatlon.get(b).get(1);
                    final String markURL2 = "javascript:line("+lat+","+lon+","+lat2+","+lon2+")";
                    webview.loadUrl(markURL2);
                }
                mydb.closedb();

            }
        });
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("file:///android_asset/mht2.html");
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
        }
    }
    Button bt;
/*
    @Override
    protected void onStart() {
        super.onStart();
        webview.post(new Runnable() {
            @Override
            public void run() {
                myDB mydb=new myDB(userride2_detail.this);
                mydb.Connect();
                ArrayList<ArrayList<String>> bikepointlatlon=mydb.bikepointlonlat(id);
                ArrayList<String> center= mydb.zoomppoint(id);
                final String markURL1 = "javascript:centerAt("+center.get(0)+","+center.get(1)+")";
                webview.loadUrl(markURL1);
                for(int i=0;i<bikepointlatlon.size()-1;i++){
                    int a=i;
                    int b=i+1;
                    String lat=bikepointlatlon.get(a).get(0);
                    String lon=bikepointlatlon.get(a).get(1);
                    String lat2=bikepointlatlon.get(b).get(0);
                    String lon2=bikepointlatlon.get(b).get(1);
                    final String markURL2 = "javascript:line("+lat+","+lon+","+lat2+","+lon2+")";
                    webview.loadUrl(markURL2);
                }
                mydb.closedb();
            }
        });

    }
*/
    @Override
    protected void onResume() {
        super.onResume();


///////////////////////////////////////////////////////////////////////
        ImageView im=(ImageView)findViewById(R.id.bikeimg);
        im.setOnClickListener(music1);
        ImageView locationim=(ImageView)findViewById(R.id.location);
        locationim.setOnClickListener(location);
        ImageView shareim=(ImageView)findViewById(R.id.share);
        shareim.setOnClickListener(share);

        ImageView gameim=(ImageView)findViewById(R.id.game);
        gameim.setOnClickListener(game);

        //  final String markURL2 = "javascript:line("+lat+","+lon+","+lat2+","+lon2+")";
        //final String markURL1 = "javascript:centerAt("+25.03623899+","+121.514951+")";
       // webview.loadUrl(markURL1);
        myDB mydb=new myDB(this);
        mydb.Connect();
        ArrayList<String> data=mydb.selectshowtotalbikerecord(id);
        TextView totaltime=(TextView)findViewById(R.id.totaltime);
        TextView totaldis =(TextView)findViewById(R.id.totaldis);
        TextView avgspeed =(TextView)findViewById(R.id.average);
        TextView totalcal =(TextView)findViewById(R.id.cal);
        TextView maxspeed =(TextView)findViewById(R.id.maxspeed);
        TextView maxalt =(TextView)findViewById(R.id.maxalt);
        TextView avgalt =(TextView)findViewById(R.id.avgalt);
        TextView temp =(TextView)findViewById(R.id.temp);
        TextView grade =(TextView)findViewById(R.id.grade);
        TextView totalcount =(TextView)findViewById(R.id.totalcount);

        totaltime.setText(""+data.get(0));
        totaldis.setText(""+data.get(1));
        avgspeed.setText(""+data.get(2));
        totalcal.setText("" + data.get(3));
        maxspeed.setText(""+data.get(4));
        maxalt.setText(""+data.get(5));
        avgalt.setText(""+data.get(6));
        temp.setText(""+data.get(7));
        grade.setText(""+data.get(8));
        totalcount.setText(""+data.get(9)+"個");

        ArrayList<String> data2=mydb.selectuserdetail(id);
        TextView title =(TextView)findViewById(R.id.roadname);
        TextView date =(TextView)findViewById(R.id.date);
        TextView week =(TextView)findViewById(R.id.week);
        TextView start_time =(TextView)findViewById(R.id.start_time);
        title.setText(""+data2.get(0));
        date.setText(""+data2.get(1));
        week.setText("" + data2.get(2));
        start_time.setText("" + data2.get(3));

        ImageView im2=(ImageView)findViewById(R.id.weather);
        im2.setImageResource(WeatherIconMapper.getWeatherResource(data2.get(4), 0));
        /////////////////////////////////////////
        ImageView share=(ImageView)findViewById(R.id.share);
        ImageView game=(ImageView)findViewById(R.id.game);
        if(mydb.getgame(id).get(0).equals("0")){//0-->1
            game.setImageResource(R.drawable.userrude_challenge_closed);
        }else{
            game.setImageResource(R.drawable.userrude_challenge_opened);
        }
        if(mydb.getshare(id).get(0).equals("0")){//0-->1
            share.setImageResource(R.drawable.userrude_share_closed);
        }else{
            share.setImageResource(R.drawable.userrude_share_opened);
        }








        /////////////////////////////////////////
        mydb.closedb();
        title.setOnClickListener(changetitle);

        ImageView favorite=(ImageView)findViewById(R.id.favorite);
        String b=data.get(10);
        if(b.equals("1")){//變成喜歡0-->1
            favorite.setImageResource(R.drawable.favorite2_2);
            //favt.setText("" + 1);
           // mydb.updatefav("1", idt);
        }else{//變成不喜歡1-->0
            favorite.setImageResource(R.drawable.favorite2);
           // favt.setText("" + 0);
           // mydb.updatefav("0", idt);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_userride2_detail, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean flag =false;
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                flag=true;
                break;
            case R.id.box:

                Toast.makeText (userride2_detail.this,"delete",Toast.LENGTH_LONG).show();
                myDB mydb=new myDB(this);
                mydb.Connect();
                mydb.deletbikerecord(id);
                onBackPressed();
                flag=true;
                break;
            default:
                flag=super.onOptionsItemSelected(item);
                break;
        }
        return  flag;
    }
    private View.OnClickListener music1 = new View.OnClickListener() {
        public void onClick(View v) {
            myDB mydb=new myDB(v.getContext());
            mydb.Connect();
            ArrayList<ArrayList<String>> bikepointlatlon=mydb.bikepointlonlat(id);
            ArrayList<String> center= mydb.zoomppoint(id);
            final String markURL1 = "javascript:centerAt("+center.get(0)+","+center.get(1)+")";
            webview.loadUrl(markURL1);
            for(int i=0;i<bikepointlatlon.size()-1;i++){
                int a=i;
                int b=i+1;
                String lat=bikepointlatlon.get(a).get(0);
                String lon=bikepointlatlon.get(a).get(1);
                String lat2=bikepointlatlon.get(b).get(0);
                String lon2=bikepointlatlon.get(b).get(1);
                final String markURL2 = "javascript:line("+lat+","+lon+","+lat2+","+lon2+")";
                webview.loadUrl(markURL2);
            }
            mydb.closedb();
        }
    };
    private View.OnClickListener location = new View.OnClickListener() {
        public void onClick(View v) {
            DialogFragment dialog = new userridedialog();
            Bundle args = new Bundle();
            args.putString("bikerecordid",""+id);
            dialog.setArguments(args);
            dialog.show(getFragmentManager(), "tag");
        }
    };

    private View.OnClickListener share = new View.OnClickListener() {
        public void onClick(View v) {
            ImageView share=(ImageView)findViewById(R.id.share);
            myDB mydb=new myDB(v.getContext());
            mydb.Connect();
            Log.v("idid", "" + mydb.getshare(id).get(0));
            if(mydb.getshare(id).get(0).equals("0")){//0-->1
                mydb.updateshare("1", id);
                share.setImageResource(R.drawable.userrude_share_opened);
            }else{
                mydb.updateshare("0", id);
                share.setImageResource(R.drawable.userrude_share_closed);
            }
            mydb.closedb();
        }
    };

    private View.OnClickListener changetitle = new View.OnClickListener() {
        public void onClick(View v) {
            DialogFragment dialog = new titledialog();
            Bundle args = new Bundle();
            args.putString("bikerecordid", "" + id);
            dialog.setArguments(args);
            dialog.show(getFragmentManager(), "tag");
        }
    };

    private View.OnClickListener game = new View.OnClickListener() {
        public void onClick(View v) {
            ImageView game=(ImageView)findViewById(R.id.game);
            myDB mydb=new myDB(v.getContext());
            mydb.Connect();
            //Log.v("idid", "" + mydb.getshare(id).get(0));
            if(mydb.getgame(id).get(0).equals("0")){//0-->1
                mydb.updategame("1", id);
                game.setImageResource(R.drawable.userrude_challenge_opened);
            }else{
                mydb.updategame("0", id);
                game.setImageResource(R.drawable.userrude_challenge_closed);
            }
            mydb.closedb();
        }
    };
    /*
    ImageView share=(ImageView)findViewById(R.id.share);
        ImageView game=(ImageView)findViewById(R.id.game);
    * if(mydb.getgame(id).get(0).equals("0")){//0-->1
            game.setImageResource(R.drawable.userrude_challenge_closed);
        }else{
            game.setImageResource(R.drawable.userrude_challenge_opened);
        }
        if(mydb.getshare(id).get(0).equals("0")){//0-->1
            share.setImageResource(R.drawable.userrude_share_closed);
        }else{
            share.setImageResource(R.drawable.userrude_share_opened);
        }*/
}
