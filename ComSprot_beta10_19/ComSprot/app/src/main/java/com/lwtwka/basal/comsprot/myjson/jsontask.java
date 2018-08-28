package com.lwtwka.basal.comsprot.myjson;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.adapter.sos_module1_adapter;
import com.lwtwka.basal.comsprot.adapter.sos_module2_adapter;
import com.lwtwka.basal.comsprot.adapter.sos_module3_adapter;
import com.lwtwka.basal.comsprot.progressbar.myprogreedialog;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;

/**
 * Created by leo on 2015/9/1.
 */
public  class jsontask extends AsyncTask<String, Void, String[]> {

    ListView mylist;
    int n;
    ArrayAdapter adapter;
    Context context;
    View v;
    public  myprogreedialog my;
    public jsontask(ListView mylist,int n,Context context,View v) {
        this.mylist=mylist;
        this.n=n;
        this.context=context;
        this.v=v;

    }
    @Override
    protected void onPreExecute() {
        if(n==1){
            my= new myprogreedialog(v.getContext());
            my.show();
        }
    }

    protected String[] doInBackground(String... urls) {
        String jstring =getJson(urls[0]);
        String data[]=new String[2];
        String b="";
        try {
            JSONObject obj=new JSONObject(jstring);
            //String b=new JSONObject(jstring).getJSONArray("results").getJSONObject(0).getString("formatted_address");

            b=new JSONObject(jstring).getJSONArray("results").getJSONObject(0).getJSONArray("address_components").getJSONObject(3).getString("long_name");//��
            data[0]=new JSONObject(jstring).getJSONArray("results").getJSONObject(0).getJSONArray("address_components").getJSONObject(3).getString("long_name");//��

            data[1]=new JSONObject(jstring).getJSONArray("results").getJSONObject(0).getJSONArray("address_components").getJSONObject(4).getString("long_name");//��


            if(n==1){
                adapter=new sos_module1_adapter(context,10,data);
            }else if(n==2){
                adapter=new sos_module2_adapter(context,10,data);
            }else if(n==3){
                adapter=new sos_module3_adapter(context,10,data);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    protected void onPostExecute(String[] result) {
      //  Log.v("json", result);
        mylist.setAdapter(adapter);
        if(n==1){
            if(((sos_module1_adapter)adapter).data.size()>0){

            }else{
                ImageView im=(ImageView)v.findViewById(R.id.hi);
                im.setVisibility(View.VISIBLE);
            }
        }else if(n==2){
            if(((sos_module2_adapter)adapter).data.size()>0){

            }else{
                ImageView im=(ImageView)v.findViewById(R.id.hi);
                im.setVisibility(View.VISIBLE);
            }
        }else if(n==3){
            if(((sos_module3_adapter)adapter).data.size()>0){

            }else{
                ImageView im=(ImageView)v.findViewById(R.id.hi);
                im.setVisibility(View.VISIBLE);
            }
        }
        if(n==1) {
            my.dismiss();
        }
    }
    public String getJson(String url) {
        String result = "";
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        HttpResponse response;
        try {
            response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf8"), 9999999);
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        }
        catch (ClientProtocolException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
        return result;
    }

}