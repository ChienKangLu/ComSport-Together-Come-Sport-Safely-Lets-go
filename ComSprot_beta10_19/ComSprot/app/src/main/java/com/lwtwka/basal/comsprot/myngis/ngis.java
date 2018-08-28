package com.lwtwka.basal.comsprot.myngis;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.myjson.jsontask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class ngis extends ActionBarActivity {
    ngistask gg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngis);
    }

    @Override
    protected void onResume() {

        super.onResume();

            ngistask gg=new ngistask(this,25.032867, 121.514276,true,0);

            Log.v("ngis", "a1");
            ngistask gg1=new ngistask(this,25.032867, 121.514276,true,0);

            Log.v("ngis", "a2");
            ngistask gg2=new ngistask(this,25.032867, 121.514276,true,0);

        ngistask gg3=new ngistask(this,25.032867, 121.514276,true,0);
        ngistask gg4=new ngistask(this,25.032867, 121.514276,true,0);
        ngistask gg5=new ngistask(this,25.032867, 121.514276,true,0);

            Log.v("ngis", "a3");

        gg.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        gg1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        gg2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        gg3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


/*
        ArrayList<ngistask> batchhaha=new ArrayList<ngistask>();
        batchhaha.add(gg);
        batchhaha.add(gg1);
        batchhaha.add(gg2);
        batchhaha.add(gg3);
*/
        new testupdate(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        Log.v("ngis", "a4");

        gg4.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        gg5.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        gg1.cancel(true);

        gg2.cancel(true);

        gg3.cancel(true);
        gg4.cancel(true);

/*
        for(int i=0;i<batchhaha.size();i++){
            ngistask now= batchhaha.get(i);
           // AsyncTask.Status status = now.getStatus();//status!= AsyncTask.Status.FINISHED
            while(!now.isCancelled()){

            }
            //Log.v("ngis",status.toString());
        }

        Log.v("ngis", "end");
        /*
        for(int i=0;i<ngistask.dbdata.size();i++){
            haha+=ngistask.dbdata.get(i);
            ///Log.v("ngis", ngistask.dbdata.get(i));
        }
        Toast.makeText(this, haha, Toast.LENGTH_LONG).show();
*/
/*
        new ngistask(this,25.032867, 121.514276)
                .execute();

        new ngistask(this,25.032867, 121.514276)
                .execute();
                */
        //Log.v("ngis", ngistask.dbdata.get(0));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ngis, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
