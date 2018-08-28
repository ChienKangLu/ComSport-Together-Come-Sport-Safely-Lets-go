package com.lwtwka.basal.comsprot.mayAsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lwtwka.basal.comsprot.myngis.ngistask;
////////


/**
 * Created by leo on 2015/9/1.
 */
public  class showalert extends AsyncTask<String, Void, String[]> {
    Context context;
    TextView showtx;
    public showalert(Context context,TextView showtx) {
        this.context=context;
        this.showtx=showtx;
    }
    @Override
    protected void onPreExecute() {
       // my= new myprogreedialog(context);
       // my.show();

        showtx.setVisibility(View.VISIBLE);
    }
    long start;
    protected String[] doInBackground(String... urls) {
        Log.v("ngis", "good start");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String data[]={};
        return data;
    }

    protected void onPostExecute(String[] result) {
        //Toast.makeText(context,ngistask.dbdata.size()+haha,Toast.LENGTH_LONG).show();
        showtx.setVisibility(View.INVISIBLE);
        Log.v("ngis", "good end");
    }
}