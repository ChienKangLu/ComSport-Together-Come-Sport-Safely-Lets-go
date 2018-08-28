package com.lwtwka.basal.comsprot.basalmainpagehaha;


import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.dialog.titledialog;
import com.lwtwka.basal.comsprot.dialog.userridedialog;
import com.lwtwka.basal.comsprot.joinbike_choosewho;
import com.lwtwka.basal.comsprot.tools.WeatherIconMapper;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;


public class joinbikeActivity extends ActionBarActivity{
    WebView webview=null;
    String id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joinbikeactivity);
//////////////
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /////////


    }
    Button bt;

    @Override
    protected void onResume() {
        super.onResume();
        ListView ls=(ListView)findViewById(R.id.ls);
        ls.setDivider(null);
        joinitem adapter=new joinitem(this,7);
        ls.setAdapter(adapter);

        SwipeLayout sample3 = (SwipeLayout)findViewById(R.id.stop);
        sample3.addDrag(SwipeLayout.DragEdge.Top, sample3.findViewWithTag("Bottom3"));
        sample3.addRevealListener(R.id.bottom_wrapper_child1, new SwipeLayout.OnRevealListener() {
            @Override
            public void onReveal(View child, SwipeLayout.DragEdge edge, float fraction, int distance) {
                View star = child.findViewById(R.id.star);
                float d = child.getHeight() / 2 - star.getHeight() / 2;
                ViewHelper.setTranslationY(star, d * fraction);
                ViewHelper.setScaleX(star, fraction + 0.6f);
                ViewHelper.setScaleY(star, fraction + 0.6f);
                int c = (Integer) evaluate(fraction, Color.parseColor("#dddddd"), Color.parseColor("#4C535B"));
                child.setBackgroundColor(c);
            }
        });
        sample3.findViewById(R.id.bottom_wrapper_child1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(joinbikeActivity.this, joinbike_choosewho.class);
                startActivity(intent);
                // Toast.makeText(joinbikeActivity.this, "Yo!", Toast.LENGTH_SHORT).show();
            }
        });
        sample3.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(joinbikeActivity.this, "Click on surface", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present..menu_userride2_detail
        getMenuInflater().inflate(R.menu.jointbike, menu);

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

                Toast.makeText (joinbikeActivity.this,"delete",Toast.LENGTH_LONG).show();
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
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        return (int) ((startA + (int) (fraction * (endA - startA))) << 24) |
                (int) ((startR + (int) (fraction * (endR - startR))) << 16) |
                (int) ((startG + (int) (fraction * (endG - startG))) << 8) |
                (int) ((startB + (int) (fraction * (endB - startB))));
    }
    /*
    private View.OnClickListener m1 = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(joinbikeActivity.this, joinbike_choosewho.class);
            startActivity(intent);

        }
    };
/*
    private void setupWindowAnimations() {
        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setExitTransition(slide);
    }
    */

}
