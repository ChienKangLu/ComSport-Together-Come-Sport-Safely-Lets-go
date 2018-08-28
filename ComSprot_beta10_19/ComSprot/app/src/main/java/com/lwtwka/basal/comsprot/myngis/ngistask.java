package com.lwtwka.basal.comsprot.myngis;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.adapter.sos_module1_adapter;
import com.lwtwka.basal.comsprot.adapter.sos_module2_adapter;
import com.lwtwka.basal.comsprot.adapter.sos_module3_adapter;
import com.lwtwka.basal.comsprot.mayAsyncTask.showalert;
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
import java.io.StringReader;
import java.math.BigDecimal;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
////////



/**
 * Created by leo on 2015/9/1.
 */
public  class ngistask extends AsyncTask<String, Void, String[]> {


    ArrayAdapter adapter;
    Context context;
    public  myprogreedialog my;
    double lat;
    double lon;
    public static HashMap<Integer,Double> dbdata;
    public static double max;
    public static double total;
    boolean whichone;
    public static double fore=0;
    public int num;
    public boolean goodsave=false;
    static{
        dbdata=new HashMap<Integer,Double>();
    }
    public ngistask( Context context,double lat,double lon,boolean whichone,int num) {
        this.context=context;
        this.lat=lat;
        this.lon=lon;
        this.whichone=whichone;
        this.num=num;
    }
    @Override
    protected void onPreExecute() {
       // my= new myprogreedialog(context);
       // my.show();
    }
    long start;
    protected String[] doInBackground(String... urls) {
        /*
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
        start=System.currentTimeMillis();
        String latString[]=convertToSexagesimal(lat).split("'");
        String lonString[]=convertToSexagesimal(lon).split("'");

        String jstring = getJson("http://ngis.moea.gov.tw/NgisFxData/WebService/XmlFunc.aspx?lon_deg=" + lonString[0] + "&lon_min=" + lonString[1] + "&lon_sec=" + lonString[2] + "&lat_deg=" + latString[0] + "&lat_min=" + latString[1] + "&lat_sec=" + latString[2] + "&CODE=10");
        Log.v("ngis", jstring+"\n");
        String TM_X97="";
        String TM_Y97="";
        try {
            DocumentBuilderFactory dbf =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(jstring));

            Document doc = db.parse(is);

            NodeList nodes = doc.getElementsByTagName("DATALIST");

            // iterate the employees
     //       for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(0);
                NodeList name = element.getElementsByTagName("DATA");
                Element line = (Element) name.item(0);
                TM_X97=line.getAttributes().getNamedItem("TM_X97").getNodeValue();
                TM_Y97=line.getAttributes().getNamedItem("TM_Y97").getNodeValue();

                //Log.v("ngis", line.getAttributes().getNamedItem("TM_X97").getNodeValue());
     //       }
        }
        catch (Exception e) {
            e.printStackTrace();
        }



        String jstring2 = getJson("http://ngis.moea.gov.tw/NgisFxData/WebService/XmlFunc.aspx?TM_X="+TM_X97+"&TM_Y="+TM_Y97+"&CODE=5&Radius=30");
        String SLOPE="";
        String DIRECTION="";
        Log.v("ngis","\n"+jstring2);
        try {
            DocumentBuilderFactory dbf =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(jstring2));

            Document doc = db.parse(is);

            NodeList nodes = doc.getElementsByTagName("DATALIST");

            // iterate the employees
            //       for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(0);
            NodeList name = element.getElementsByTagName("DATA");
            Element line = (Element) name.item(0);
            SLOPE=line.getAttributes().getNamedItem("SLOPE").getNodeValue();
            DIRECTION=line.getAttributes().getNamedItem("DIRECTION").getNodeValue();

            Log.v("ngis", SLOPE+"~~"+DIRECTION);
            //       }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String data[]={SLOPE,DIRECTION};

        return data;
    }

    protected void onPostExecute(String[] result) {
        if(whichone) {
            long end = System.currentTimeMillis();
            try {
                //dbdata.add(Double.parseDouble(result[0]));
                dbdata.put(num, Double.parseDouble(result[0]));
            }catch (NumberFormatException e){
                dbdata.put(num, 100.0);
            }
            /*
            Log.v("json", "" + (end - start));
            String haha = "";
            for (int i = 0; i < dbdata.size(); i++) {
                haha += ngistask.dbdata.get(i);
            }

            Log.v("ngis", dbdata.size() + haha);
            Log.v("ngis_2","db:"+dbdata.size());
            */
            Log.v("ngis_2","db:"+num);
            goodsave=true;


        }else{
            try {
                fore=Double.parseDouble(result[0]);
            }catch (NumberFormatException e){
                fore=100.0;
            }

            Log.v("dre",""+fore);
            Log.v("ngis_2","nondb:"+"execute");
            if(fore>0){
                TextView showtx= (TextView) ((Activity)context).findViewById(R.id.show);
                new showalert(context,showtx)
                        .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }
        //Toast.makeText(context,haha,Toast.LENGTH_LONG).show();
       // my.dismiss();
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
    //将小数转换为度分秒
    public String convertToSexagesimal(double num){

        int du=(int)Math.floor(Math.abs(num));    //获取整数部分
        double temp=getdPoint(Math.abs(num))*60;
        int fen=(int)Math.floor(temp); //获取整数部分
        double miao=getdPoint(temp)*60;
        if(num<0)
            return "-"+du+"'"+fen+"'"+miao;

        return du+"'"+fen+"'"+miao;

    }
    //获取小数部分
    public double getdPoint(double num){
        double d = num;
        int fInt = (int) d;
        BigDecimal b1 = new BigDecimal(Double.toString(d));
        BigDecimal b2 = new BigDecimal(Integer.toString(fInt));
        double dPoint = b1.subtract(b2).floatValue();
        return dPoint;
    }

    /*
    class LatLonToTWD97{
        double a;
        double b;
        double long0;
        double k0;
        double dx;
        public LatLonToTWD97() {
            a = 6378137.0;
            b = 6356752.314245;
            long0 = Math.toRadians(121);
            k0 = 0.9999;
            dx = 250000;
        }

        public double[] convert(double lat,double lon){
            double e = Math.pow((1-Math.pow(b,2)/Math.pow(a,2)),0.5);
            double e2 = Math.pow(e,2)/(1-Math.pow(e,2));
            double n = (a-b)/(a+b);
            double nu = a/Math.pow((1-(Math.pow(e,2))*Math.pow(Math.sin(lat),2)),0.5);
            double p = lon-long0;

            double A = a*(1 - n + (5/4.0)*(Math.pow(n,2) - Math.pow(n,3)) + (81/64.0)*(Math.pow(n,4)  - Math.pow(n,5)));
            double B = (3*a*n/2.0)*(1 - n + (7/8.0)*(Math.pow(n,2) - Math.pow(n,3)) + (55/64.0)*(Math.pow(n,4) - Math.pow(n,5)));
            double C = (15*a*(Math.pow(n,2))/16.0)*(1 - n + (3/4.0)*(Math.pow(n,2) - Math.pow(n,3)));
            double D = (35*a*(Math.pow(n,3))/48.0)*(1 - n + (11/16.0)*(Math.pow(n,2) - Math.pow(n,3)));
            double E = (315*a*(Math.pow(n,4))/51.0)*(1 - n);

            double S = A*lat - B*Math.sin(2*lat) + C*Math.sin(4*lat) - D*Math.sin(6*lat) + E*Math.sin(8*lat);

            double K1 = S*k0;
            double K2 = k0*nu*Math.sin(2*lat)/4.0;
            double K3 = (k0*nu*Math.sin(lat)*(Math.pow(Math.cos(lat),3))/24.0) *
            (5 - Math.pow(Math.tan(lat),2) + 9*e2*(Math.pow(Math.cos(lat),2)) + 4*(Math.pow(e2,2))*(Math.pow(Math.cos(lat),4)));

            double y = K1 + K2*(Math.pow(p,2)) + K3*(Math.pow(p,4));

            double K4 = k0*nu*Math.cos(lat);
            double K5 = (k0*nu*(Math.pow(Math.cos(lat),3))/6.0) *
            (1 - Math.pow(Math.tan(lat),2) + e2*(Math.pow(Math.cos(lat),2)));

            double  x = K4*p + K5*(Math.pow(p,3)) + dx;
            /////////////
            double xy[]={x,y};
            return xy;
        }

    }
    */
}