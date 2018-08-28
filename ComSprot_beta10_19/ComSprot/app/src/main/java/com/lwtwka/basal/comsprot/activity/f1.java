package com.lwtwka.basal.comsprot.activity;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.mayAsyncTask.myAsyTask;
import com.lwtwka.basal.comsprot.mayAsyncTask.myAsyTaskf1;
import com.lwtwka.basal.comsprot.mayAsyncTask.showalert;
import com.lwtwka.basal.comsprot.myngis.ngistask;
import com.lwtwka.basal.comsprot.myngis.testupdate;
import com.lwtwka.basal.comsprot.progressbar.myprogreedialog;
import com.lwtwka.basal.comsprot.tools.WeatherIconMapper;
import com.lwtwka.basal.comsprot.userDetail;
import com.survivingwithandroid.weather.lib.WeatherClient;
import com.survivingwithandroid.weather.lib.WeatherConfig;
import com.survivingwithandroid.weather.lib.client.okhttp.WeatherDefaultClient;
import com.survivingwithandroid.weather.lib.exception.WeatherLibException;
import com.survivingwithandroid.weather.lib.model.CurrentWeather;
import com.survivingwithandroid.weather.lib.provider.IWeatherCodeProvider;
import com.survivingwithandroid.weather.lib.provider.openweathermap.OpenweathermapProviderType;
import com.survivingwithandroid.weather.lib.request.WeatherRequest;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;

/**
 * A simple {@link Fragment} subclass.
 */

public class f1 extends Fragment {
    userDetail user;
    ArrayList<point> pointlist=new ArrayList();//BlankFragment裡還有一個!!用來溝通每個fragment用的(f1,map)
    public class point{
        public double lat;
        public double lon;
        public double speed;
        public double alt;
        public int category;
        public point(double lat, double lon, double speed, double alt, int category) {
            this.lat = lat;
            this.lon = lon;
            this.speed = speed;
            this.alt = alt;
            this.category = category;
        }
    }
    long Ftime=0;//目前時間
    double totaldistance=0;//總距離

    long lastTime=0;
    long now=0;
    long time=0;
    //
    String weathers="";
    String dates="";
    int counts=0;
    double sumspped=0;
    double sumalt=0;
    double maxspeed=0;
    double maxalt=0;
    double temps=0;
    public static ArrayList<ngistask> HAHAlist=new ArrayList<ngistask>();
    public static HashMap<Integer,ngistask> APIpoint=new HashMap<>();

    public f1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user=new userDetail(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //result = container.getTag().toString();
        return inflater.inflate(R.layout.f1, container, false);
    }

    public void setvalue(final int countn,double lat,double lon,double alt,double speed,long second){

        ngistask  taska=new ngistask(getActivity(),lat, lon,true,countn);
        //taska.execute();
        taska.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        APIpoint.put(countn, taska);
        //HAHAlist.add(taska);
        if(countn%10==0&&countn!=0){//countn必定大於5
            //取出第n個點，以及第n-1的點，計算這一秒單位向量
            //透過這個向量，去估算使用者未來幾秒後的位置
            //傳入估算的位置，連結經濟地理資料庫，得到未來的坡度，和當前坡度做比較
            //如果XXX判斷後是不安全的，即刻顯示
            //如果安全，不顯示，忽略這次計算
            //ngis dre
            int now=countn;
            int forecasting1=now-2;
            int forecasting2=now-3;
            double lat1=pointlist.get(forecasting1).lat;
            double lon1=pointlist.get(forecasting1).lon;
            double lat2=pointlist.get(forecasting2).lat;
            double lon2=pointlist.get(forecasting2).lon;
            double vector_lat=lat2-lat1;
            double vector_lon=lon2-lon1;
            double forelat=lat+ vector_lat*100;//預測點
            double forelon=lon+ vector_lon*100;//預測點
            ngistask taskb=new ngistask(getActivity(),forelat,forelon,false,countn);
                   // .execute();
            taskb.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            HAHAlist.add(taskb);
        }

        final int count =countn;//次數
        double latitude = lat; //經度
        double longitude = lon; //緯度
        double altitude = alt; //海拔
        double Speed = speed; //速度
        time = second; //時間

        pointlist.add(new point(lat,lon,(Speed*3600)/1000,altitude,0));//在point加上屬性
        counts=count;

        //平均
        sumspped+=(Speed*3600)/1000;
        sumalt+=altitude;
        //最大
        if(Speed>=maxspeed){
            maxspeed=Speed;
        }
        if(altitude>=maxalt){
            maxalt=altitude;
        }
        /*目前時間*/
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String myDate= sdf.format(date);
        if(count>=1) {
        /*加總距離*/
            double nowdistance = GetDistance(pointlist.get(count).lat, pointlist.get(count).lon, pointlist.get(count - 1).lat, pointlist.get(count - 1).lon);
            totaldistance += nowdistance;
        }
        /*加總時間*/
        if(countn==1){
            Ftime=second;
            dates=myDate;
        }
        //lastTime=time-Ftime;
        now=lastTime+time-Ftime;


        if(countn>=1) {
            TextView gg = (TextView) getActivity().findViewById(R.id.gg);
            gg.setText("go(" + count+")");
            TextView datetime = (TextView) getActivity().findViewById(R.id.datetime);
            datetime.setText(myDate);
            TextView runtime = (TextView) getActivity().findViewById(R.id.runtime);
            runtime.setText(formatLongToTimeStr(now));
            TextView distance = (TextView) getActivity().findViewById(R.id.distance);
            distance.setText("" + judgedistance(totaldistance)[0]);
            TextView unit = (TextView) getActivity().findViewById(R.id.unit);
            unit.setText("" + judgedistance(totaldistance)[1]);
            TextView kal = (TextView) getActivity().findViewById(R.id.kcal);
            kal.setText(""+calWeight(now,user.weight())+" "+"kcal");
            TextView sp = (TextView) getActivity().findViewById(R.id.speed);
            int m_s_to_km_hr=(int) ((Speed*3600)/1000);
            sp.setText(""+m_s_to_km_hr);
        }
      //  kal.setText("");



        //*weather

        try {

            WeatherClient client = (new WeatherClient.ClientBuilder()).attach(getActivity())

                    .httpClient(WeatherDefaultClient.class)

                    .provider(new OpenweathermapProviderType())//YahooProviderType()OpenweathermapProviderType() YahooWeatherProviderType

                    .config(new WeatherConfig())

                    .build();


            // 120.950983,24.814691"2643741"
            client.getCurrentCondition(new WeatherRequest(longitude,latitude), new WeatherClient.WeatherEventListener() {

                @Override

                public void onWeatherRetrieved(CurrentWeather currentWeather) {

                    float currentTemp = currentWeather.weather.temperature.getTemp();
                    if(count==1){
                        weathers=currentWeather.weather.currentCondition.getIcon();
                        temps=currentTemp;
                    }
                    currentWeather.weather.currentCondition.getIcon();
                    IWeatherCodeProvider codeProvider;
                    ImageView im=(ImageView)getActivity().findViewById(R.id.weather);
                    im.setImageResource(WeatherIconMapper.getWeatherResource(currentWeather.weather.currentCondition.getIcon(), currentWeather.weather.currentCondition.getWeatherId()));

                    TextView temptr= (TextView) getActivity().findViewById(R.id.temptr);
                    temptr.setText(currentTemp + "°c");

                    Log.d("WL", "City [" + currentWeather.weather.location.getCity() + "] Current temp [" + currentTemp + "]");
                   // Toast.makeText(getActivity().getBaseContext(), "["+count+ "]--"+"City [" +currentWeather.weather.location.getCity() + "] Current temp [" + currentTemp + "]"+currentWeather.weather.currentCondition.getWeatherId(), Toast.LENGTH_LONG).show();

                }



                @Override

                public void onWeatherError(WeatherLibException e) {

                    Log.d("WL", "Weather Error - parsing data");
                  //  Toast.makeText(getActivity().getBaseContext(), "Weather Error - parsing data", Toast.LENGTH_LONG).show();

                    e.printStackTrace();

                }


                @Override

                public void onConnectionError(Throwable throwable) {

                    Log.d("WL", "Connection error");

                    throwable.printStackTrace();

                }

            });

        }

        catch (Throwable t) {

            t.printStackTrace();

        }
    }
    @Override
    public void onResume() {
        super.onResume();
        ImageButton go=(ImageButton)getActivity().findViewById(R.id.go);
        go.setOnClickListener(music1);
        ImageButton saveb=(ImageButton)getActivity().findViewById(R.id.save);
        saveb.setOnClickListener(music2);
        //blankFragment = (BlankFragment) getFragmentManager().findFragmentByTag("f2");//getChildFragmentManager()
        //blankFragment.start();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    boolean key=true;
    boolean save=true;
    private View.OnClickListener music1 = new View.OnClickListener() {
        public void onClick(View v) {
            //    FragmentTransaction ft=getFragmentManager().beginTransaction();
            //  blankFragment =getFragmentManager().findFragmentById(R.id.container_body);//getChildFragmentManager()
            //getActivity().getSupportFragmentManager().executePendingTransactions();
            //  ((BlankFragment)blankFragment).start();
            TextView gg = (TextView) getActivity().findViewById(R.id.gg);
            gg.setText("wait");

            ImageButton go=(ImageButton)getActivity().findViewById(R.id.go);
            if(key) {
                go.setImageResource(R.drawable.pause2);
                TextView keyt = (TextView) getActivity().findViewById(R.id.textView7);
                keyt.setText("gofalse");
                key=false;
                ImageButton saveb=(ImageButton)getActivity().findViewById(R.id.save);
                saveb.setVisibility(View.GONE);

                lastTime+=time-Ftime;//加上舊的時間
                Ftime=time;//更改目前

            }else{
                go.setImageResource(R.drawable.play2);

                TextView keyt = (TextView) getActivity().findViewById(R.id.textView7);
                keyt.setText("gotrue");
                key=true;
                ImageButton saveb=(ImageButton)getActivity().findViewById(R.id.save);
                saveb.setVisibility(View.VISIBLE);

            }
        }
    };
    public void reset(){
        pointlist=new ArrayList();



        Ftime=0;//目前時間
        totaldistance=0;//總距離

        lastTime=0;
        now=0;
        time=0;
        //
        weathers="";
        dates="";
        counts=0;
        sumspped=0;
        sumalt=0;
        maxspeed=0;
        maxalt=0;
        temps=0;
        TextView gg = (TextView) getActivity().findViewById(R.id.gg);
        gg.setText("saving...");
        TextView datetime = (TextView) getActivity().findViewById(R.id.datetime);
        //datetime.setText(myDate);
        TextView runtime = (TextView) getActivity().findViewById(R.id.runtime);
        runtime.setText("00:00:00");
        TextView distance = (TextView) getActivity().findViewById(R.id.distance);
        distance.setText("0.0");
        TextView unit = (TextView) getActivity().findViewById(R.id.unit);
        unit.setText("KM");
        TextView kal = (TextView) getActivity().findViewById(R.id.kcal);
        kal.setText("0.0"+"kcal");
        TextView sp = (TextView) getActivity().findViewById(R.id.speed);
        //int m_s_to_km_hr=(int) ((Speed*3600)/1000);
        sp.setText("0");

        /////////


    }
    private View.OnClickListener music2 = new View.OnClickListener() {//存檔
        public void onClick(View v) {
            myAsyTaskf1 HAHA = new myAsyTaskf1(getActivity(),pointlist,weathers,dates,counts,now,totaldistance,sumspped,sumalt,maxspeed,maxalt,temps);
            HAHA.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            //new testupdate(getActivity()).execute();

        /*
            final myDB mydb=new myDB(getActivity());
            mydb.Connect();
            final myprogreedialog my= new myprogreedialog(getActivity());
            my.show();
            //
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        //1)先建立一個bikerecord
                        //SharedPreferences settings = getActivity().getSharedPreferences("LoginInfo", 0);
                        userDetail user=new userDetail(getActivity());
                        String userid=user.id();
                        int dbuserid=Integer.parseInt(userid);
                        String dbweather=weathers;
                        String dbdate=dates;
                        String dbtotalcount=""+(counts+1);
                        String dbtotaltime=formatLongToTimeStr(now);
                        String dbtotalcal=""+calWeight(now);
                        String dbtotaldis=""+totaldistance;//km
                        String dbavgspeed=""+(sumspped/counts+1);
                        String dbavgalt=""+(sumalt/counts+1);
                        String dbmaxspeed=""+(maxspeed*3600)/1000;
                        String dbmaxalt=""+maxalt;
                        String dbgrade="";
                        String dbpic="";
                        String dbfav="";
                        String dbtemp=""+temps;
                        String dbtitle="BASAL"+mydb.getlatesttitle(""+dbuserid);
                        mydb.insertbikerecord(dbuserid,dbweather,dbdate,dbtotalcount,dbtotaltime,dbtotalcal,dbtotaldis,dbavgspeed,dbavgalt,dbmaxspeed,dbmaxalt,dbgrade,dbpic,dbfav,dbtemp,dbtitle);
                        //2)bikepoint bikerecordid point lat lon speed alt category(0,1,2,3)
                        // Thread.sleep(3000);


                        ArrayList<String> batch=new ArrayList<String>();
                        int batchsize=20;
                        String insertsql="";
                        //"("+bikerecordid+","+count+",'"+lat+"','"+lon+"','"+speed+"','"+alt+"','"+category+"')"
                        //INSERT 陳述式中資料列值數目運算式超過允許的 1000 資料列值數目的上限。

                        int bikerecordid=mydb.getlatestinsertbikerecordid();
                        //900傳一次
                        for(int i=0;i<counts+1;i++) {//lat, double lon, double speed, double alt, int category
                            String lat=""+pointlist.get(i).lat;
                            String lon=""+pointlist.get(i).lon;
                            String speed=""+pointlist.get(i).speed;
                            String alt=""+pointlist.get(i).alt;
                            String category=""+0;
                            //mydb.insertbikepoint(bikerecordid,i,lat,lon,speed,alt,category);
                            if(i%batchsize!=0){
                                insertsql+=",";
                            }
                            insertsql+="("+bikerecordid+","+i+",'"+lat+"','"+lon+"','"+speed + "','" + alt+"','"+category+"')";
                            if((i+1)%batchsize==0) {
                                batch.add(insertsql);
                                insertsql = "";
                            }
                        }
                        batch.add(insertsql);//最後一筆
                        for(int i=0;i<batch.size();i++) {
                            mydb.insertbikepoint(batch.get(i));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        mydb.closedb();
                        my.dismiss();
                    }
                }
            }).start();
            */
            reset();
            ImageButton saveb=(ImageButton)getActivity().findViewById(R.id.save);
            saveb.setVisibility(View.GONE);
            TextView keyt = (TextView) getActivity().findViewById(R.id.textView7);
            keyt.setText("save");

        }
    };
    public static String formatLongToTimeStr(Long l) {
        String str = "";
        int hour = 0;
        int minute = 0;
        int second = 0;
        second = l.intValue() / 1000;
        if (second > 60) {
            minute = second / 60;
            second = second % 60;
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        String hours="";
        String minutes="";
        String seconds="";
        if(hour<10){
            hours="0"+hour;
        }else{
            hours=""+hour;
        }
        if(minute<10){
            minutes="0"+minute;
        }else{
            minutes=""+minute;
        }
        if(second<10){
            seconds="0"+second;
        }else{
            seconds=""+second;
        }

        String strtime = hours+":"+minutes+":"+seconds;
       // Log.v("時間:",strtime);
        return strtime;
    }

    private double EARTH_RADIUS = 6378.137;
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }
    /*
    * 1. Lat1 Lung1 表示A点经纬度，Lat2 Lung2 表示B点经纬度；

    2. a=Lat1 – Lat2 为两点纬度之差  b=Lung1 -Lung2 为两点经度之差；

    3. 6378.137为地球半径，单位为千米；

    计算出来的结果单位为千米。

    从google maps的脚本里扒了段代码，是用来计算两点间经纬度距离*/
    public  double GetDistance(double lat1, double lng1, double lat2, double lng2)
    {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        //    s = Math.round(s * 10000) / 10000;//no use
        /*
        String f[]=new String[2];
        if(s<=1000){
            s=s*1000;//公尺
            s=Math.round(s);
            f[0]=""+(int)s;//數字
            f[1]="公尺";
        }else{
            s=s;
            DecimalFormat df = new DecimalFormat("##.0");
            s = Double.parseDouble(df.format(s));
            f[0]=""+s;//數字
            f[1]="公里";
        }
        */

        return s;//公里
    }
    public String[] judgedistance(double s){
        String f[]=new String[2];
        s=s*1000;
        if(s<=1000){
           // s=s*1000;//公尺
            s=Math.round(s);
            if(s<10) {
                f[0] = "  " + (int) s;//數字
            }else{
                f[0] = ""+ (int) s;//數字
            }
            f[1]="M";
        }else{
            s=s/1000;//公里
            DecimalFormat df = new DecimalFormat("##.0");
            s = Double.parseDouble(df.format(s));
            f[0]=""+s;//數字
            f[1]="KM";
        }
        return f;
    }
    public static double calWeight(Long l ,int weight){
        //MET(kcal/kg/hr)*體重(kg)*運動時間(hr)
        String str = "";
        int hour = 0;
        int minute = 0;
        int second = 0;
        second = l.intValue() / 1000;
        if (second > 60) {
            minute = second / 60;
            second = second % 60;
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        /**/
        double kcalhour=userDetail.KCAL[0]*weight*hour;
        double kcalminute=userDetail.KCAL[0]*weight*(((double)minute)/60);
        double kcalsecond=userDetail.KCAL[0]*weight*(((double)second)/3600);
        double kinal =kcalhour+kcalminute+kcalsecond;

        DecimalFormat df = new DecimalFormat("##.0");
        kinal = Double.parseDouble(df.format(kinal));
        return kinal;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();//滑動到第三頁觸發
        Log.v("close", "main_f1 onDestroyView");
        for(ngistask task:HAHAlist){
            task.cancel(true);
            task=null;
        }
        HAHAlist=new ArrayList<>();
        for(Integer key:f1.APIpoint.keySet()){
            APIpoint.get(key).cancel(true);
        }
        APIpoint=new HashMap<Integer,ngistask>();
       // HAHA.cancel(true);
       // HAHA=null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("close", "main_f1 onDestroy");    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.v("close", "main_f1 onDetach");
    }
    public void aa(){
        if(true)
                return;
        else{
            int y=1;
        }
    }

}
