package com.lwtwka.basal.comsprot;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.lwtwka.basal.comsprot.game_package.myAsyTaskf1;
import com.lwtwka.basal.comsprot.mayAsyncTask.DownloadImageTask2;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


public class josontest extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_josontest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_josontest, menu);
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

    @Override
    protected void onResume() {
        super.onResume();
        //jsontask HAHA = new jsontask();"http://maps.google.com/maps/api/geocode/json?latlng=%2023,120%20&language=zh-CN&sensor=true"
        new jsontask()
                .execute("http://maps.google.com/maps/api/geocode/json?latlng=23,120&language=zh-CN&sensor=true");//image38
        //HAHA.execute();
    }

    public  class jsontask extends AsyncTask<String, Void, String> {


        public jsontask() {
        }

        protected String doInBackground(String... urls) {
            String jstring =getJson(urls[0]);
            String back="";
            try {
                JSONObject obj=new JSONObject(jstring);

                //String b=new JSONObject(jstring).getJSONArray("results").getJSONObject(0).getString("formatted_address");
                String b=new JSONObject(jstring).getJSONArray("results").getJSONObject(0).getJSONArray("address_components").getJSONObject(4).getString("long_name");
                back=b;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return back;
        }

        protected void onPostExecute(String result) {
            Log.v("json",result);
        }
        public String getJson(String url) {  //這邊所接進來的url是撈JSON的那個網址
//宣告一個String來存放等等撈到的資料
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
}
