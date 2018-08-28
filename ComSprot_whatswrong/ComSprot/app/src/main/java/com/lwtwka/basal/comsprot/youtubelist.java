package com.lwtwka.basal.comsprot;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.adapter.listitem;


public class youtubelist extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtubelist);
//////////////
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
/////////////////

		MyListView L=(MyListView)findViewById(R.id.list1);

			listitem adapter=new listitem(this,10);
			L.setAdapter(adapter);

			L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
				}
			});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_youtubelist, menu);
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
