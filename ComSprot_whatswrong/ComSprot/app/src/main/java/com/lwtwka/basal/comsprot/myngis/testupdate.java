package com.lwtwka.basal.comsprot.myngis;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lwtwka.basal.comsprot.progressbar.myprogreedialog;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
////////


/**
 * Created by leo on 2015/9/1.
 */
public  class testupdate extends AsyncTask<String, Void, String[]> {
    Context context;
    public testupdate(Context context) {
        this.context=context;
    }
    @Override
    protected void onPreExecute() {
       // my= new myprogreedialog(context);
       // my.show();
    }
    long start;
    protected String[] doInBackground(String... urls) {
        Log.v("ngis", "good start");
/*
        for( ngistask t:allt){
            try {
                t.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        */
        String haha="";
        for(int i=0;i<ngistask.dbdata.size();i++){
            haha+=ngistask.dbdata.get(i);
        }

        Log.v("ngis", "finaldata=>"+haha);
        String data[]={};
        return data;
    }

    protected void onPostExecute(String[] result) {
        String haha="";
        for(int i=0;i<ngistask.dbdata.size();i++){
            haha+=ngistask.dbdata.get(i);
        }
        Toast.makeText(context,ngistask.dbdata.size()+haha,Toast.LENGTH_LONG).show();
        Log.v("ngis", "good end");
    }
}