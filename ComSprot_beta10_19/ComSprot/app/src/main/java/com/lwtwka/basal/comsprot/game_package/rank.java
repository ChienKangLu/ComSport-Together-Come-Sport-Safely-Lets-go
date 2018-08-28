package com.lwtwka.basal.comsprot.game_package;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.adapter.sos_module1_adapter;
import com.lwtwka.basal.comsprot.userride2_detail;


/**
 * A simple {@link Fragment} subclass.
 */
public class rank extends ActionBarActivity {

    String id;

    public rank() {
        // Required empty public constructor
    }

    View view;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank);
        //////////////
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
/////////////////
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
        }

        Log.v("id2", id);
    }

    @Override
    public void onResume() {
        super.onResume();

        MyListView L=(MyListView)findViewById(R.id.list1);
        rank_adapter adapter=new rank_adapter(this,10,id);
        L.setAdapter(adapter);

        L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent();
                intent.setClass(rank.this, userride2_detail.class);
                startActivity(intent);
                // TODO Auto-generated method stub
                // TextView title = (TextView) ((RelativeLayout) view).getChildAt(0);

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean flag=false;
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
