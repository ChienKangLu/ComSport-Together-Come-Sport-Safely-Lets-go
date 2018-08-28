package com.lwtwka.basal.comsprot.mayAsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.adapter.road_race_adapter;
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by leo on 2015/9/1.
 */
public  class race_catch extends AsyncTask<String, Void, Void> {
    ListView mylist;
    ArrayAdapter adapter;
    Context context;
    View v;
    public  myprogreedialog my;
    public race_catch(ListView mylist, Context context, View v) {
        this.mylist=mylist;
        this.context=context;
        this.v=v;
    }
    @Override
    protected void onPreExecute() {
        my= new myprogreedialog(v.getContext());
        my.show();
    }

    protected Void doInBackground(String... urls) {
        String urln="";
        ArrayList<String> datatitle= new ArrayList<String>();//"http://solomo.xinmedia.com"+tds.attr("href")
        ArrayList<String> dataurl= new ArrayList<String>();

        ArrayList<String> innerdatadate= new ArrayList<String>();
        ArrayList<String> datadate= new ArrayList<String>();
        ArrayList<String> datacity= new ArrayList<String>();


        try {
            Document doc = null;
            urln = "http://solomo.xinmedia.com/bike/events";
            doc = Jsoup.connect(urln).get();
            Log.v("roadrace",""+doc);
            for (Element tds : doc.select(".summary h2 a")) {//�Ic.attr("href")lass�̭���td�̭���a(�W�s��
                datatitle.add(tds.text());
                dataurl.add("http://solomo.xinmedia.com"+tds.attr("href"));
                ////////////////////////////
                /*
                String title="";
                String date="";
                String sign="";
                String dis="";
                String note="";
                String urln2="";
                String url="";
                Document doc2 = null;
                urln2 = "http://solomo.xinmedia.com"+tds.attr("href");
                doc2 = Jsoup.connect(urln2).get();
                Elements titleele=doc2.select(".article h1");
                title=(titleele.get(0).text()).toString();
                Elements dateele=doc2.select(".tblstyle1 tbody tr td span");
                date=(dateele.get(0).text()).toString();
                sign=(dateele.get(3).text()).toString();
                Elements disele=doc2.select(".tabB .tblstyle1 tbody tr td span");
                dis=disele.get(2).text().toString();
                note=disele.get(8).text().toString();
                Elements urlele=doc2.select(".tblstyle1 tbody tr td span a");
                try {
                    url = (urlele.get(0).attr("href"));
                    ////////////////////////////
                }catch (Exception e){

                }
                innerdatadate.add(date);
*/
                System.out.println(tds.text());
            }
            for(Element tds : doc.select(".time")){
                datadate.add(tds.text());
            }
            for(Element tds : doc.select(".cityname")){
                datacity.add(tds.text());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        adapter=new road_race_adapter(context,10,datatitle,datadate,datacity,dataurl);
        return null;
    }

    protected void onPostExecute(Void res) {
      //  Log.v("json", result);
        mylist.setAdapter(adapter);
        my.dismiss();
    }

}