package com.lwtwka.basal.comsprot.frd;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.database.myDB;


public class checkActivity extends ActionBarActivity{
    WebView webview=null;
    String id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkactivity);
//////////////
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /////////
        ListView ls=(ListView)findViewById(R.id.ls);
        ls.setDivider(null);
        frd_check_adapter adapter=new frd_check_adapter(this,7);
        ls.setAdapter(adapter);


    }
    Button bt;

    @Override
    protected void onResume() {
        super.onResume();


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

                Toast.makeText (checkActivity.this,"delete",Toast.LENGTH_LONG).show();
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
