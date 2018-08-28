package com.lwtwka.basal.comsprot;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lwtwka.basal.comsprot.mayAsyncTask.race_catch;
import com.lwtwka.basal.comsprot.mayAsyncTask.race_catch_detail;


public class race_detail extends ActionBarActivity {

    String url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_detail);
        //////////////
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /////////
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            url = bundle.getString("url");
        }

        TextView name=(TextView)findViewById(R.id.name);
        TextView date=(TextView)findViewById(R.id.date);
        TextView descript=(TextView)findViewById(R.id.descript);
        TextView dis=(TextView)findViewById(R.id.dis);
        TextView asign=(TextView)findViewById(R.id.asign);
        TextView urltx=(TextView)findViewById(R.id.url);
        ImageView im=(ImageView)findViewById(R.id.roadpic);
        TextView title=(TextView)findViewById(R.id.title);
        new race_catch_detail(url,this,name,date,descript,dis,asign,urltx,im,title)
                .execute();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_race_detail, menu);
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
            default:
                flag=super.onOptionsItemSelected(item);
                break;
        }
        return  flag;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
