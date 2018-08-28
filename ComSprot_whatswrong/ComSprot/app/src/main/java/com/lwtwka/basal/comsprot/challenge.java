package com.lwtwka.basal.comsprot;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.basalmainpagehaha.challenge_adpter;
import com.lwtwka.basal.comsprot.basalmainpagehaha.road_shareroad_adapter;
import com.lwtwka.basal.comsprot.database.myDB;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingLeftInAnimationAdapter;

public class challenge extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
        //////////////
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /////////

        ListView L=(ListView)findViewById(R.id.ls);
        challenge_adpter adapter=new challenge_adpter(this,10);
        L.setAdapter(adapter);

        AlphaInAnimationAdapter mAnimAdapter = new AlphaInAnimationAdapter(adapter);
        mAnimAdapter.setAbsListView(L);
        L.setAdapter(mAnimAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_challenge, menu);
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
}
