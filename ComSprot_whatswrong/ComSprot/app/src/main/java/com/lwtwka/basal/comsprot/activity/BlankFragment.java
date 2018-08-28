package com.lwtwka.basal.comsprot.activity;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.adapter.SampleFragmentPagerAdapter;
import com.lwtwka.basal.comsprot.map;
import com.lwtwka.basal.comsprot.myngis.ngistask;
import com.lwtwka.basal.comsprot.tools.WeatherIconMapper;
import com.lwtwka.basal.comsprot.userDetail;
import com.survivingwithandroid.weather.lib.WeatherClient;
import com.survivingwithandroid.weather.lib.WeatherConfig;
import com.survivingwithandroid.weather.lib.client.okhttp.WeatherDefaultClient;
import com.survivingwithandroid.weather.lib.exception.WeatherLibException;
import com.survivingwithandroid.weather.lib.model.CurrentWeather;
import com.survivingwithandroid.weather.lib.provider.IWeatherCodeProvider;
import com.survivingwithandroid.weather.lib.provider.openweathermap.OpenweathermapProviderType;
import com.survivingwithandroid.weather.lib.provider.wunderground.WeatherUndergroundProviderType;
import com.survivingwithandroid.weather.lib.request.WeatherRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements LocationListener {

    int count=0;
    ViewPager viewPager;
    boolean key=true;
    ArrayList<point> pointlist=new ArrayList();//f1裡還有一個!!用來存檔用的
    public class point{
        double lat;
        double lon;
        public point(double lat,double lon){
            this.lat=lat;
            this.lon=lon;
        }
    }

    public BlankFragment() {
        // Required empty public constructor
    }

    public LocationManager locationManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);//準確性
        criteria.setAltitudeRequired(true);//是否要用海拔
        criteria.setBearingRequired(false);//是否要用方向性
        criteria.setSpeedRequired(true);//速度
        criteria.setCostAllowed(false);//資費

        String provider = locationManager.getBestProvider(criteria, false);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
        Toast.makeText(getActivity().getBaseContext(), "f1 onCreate location start",
                Toast.LENGTH_LONG).show();
*/      //start();

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);//準確性
        criteria.setAltitudeRequired(true);//是否要用海拔
        criteria.setBearingRequired(false);//是否要用方向性
        criteria.setSpeedRequired(true);//速度
        criteria.setCostAllowed(false);//資費

        String provider = locationManager.getBestProvider(criteria, false);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
     //   Toast.makeText(getActivity().getBaseContext(), "gps can use ",
     //           Toast.LENGTH_LONG).show();
           } else {
      //         Toast.makeText(getActivity().getBaseContext(), "請開啟定位服務", Toast.LENGTH_LONG).show();
               startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));	//開啟設定頁面
           }
    }
    int gpssecond=1000;
    int gpsmeter=1;
    public void GPSorNetwork(){

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, gpssecond, gpsmeter, this);
     //       Toast.makeText(getActivity().getBaseContext(), "使用GPS定位", Toast.LENGTH_LONG).show();

        } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, gpssecond, gpsmeter, this);
     //       Toast.makeText(getActivity().getBaseContext(), "使用網路定位", Toast.LENGTH_LONG).show();

        }
    }
    public  void start(){

      //  if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            GPSorNetwork();//開始定位!!


        //    Toast.makeText(getActivity().getBaseContext(), "gps location record start",
         //           Toast.LENGTH_LONG).show();
     //   } else {
     //       Toast.makeText(getActivity().getBaseContext(), "請開啟定位服務", Toast.LENGTH_LONG).show();
     //       startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));	//開啟設定頁面
     //   }
    }
    public void stop(){
        locationManager.removeUpdates(this);
    //    Toast.makeText(getActivity().getBaseContext(), "gps location record end",
    //            Toast.LENGTH_LONG).show();
        /*
        pointlist.clear();//清空資料
        count=0;//清空記數器
        /*重製google map v3 webview*/
        /*
        Fragment fragment = ((SampleFragmentPagerAdapter) viewPager.getAdapter()).getFragment(1);
        if (fragment instanceof map) {
            ((map) fragment).cleanmygooglemapwebview();
        }
        */

    }
    public void reset(){
        pointlist.clear();//清空資料
        count=0;//清空記數器
                    /*重製google map v3 webview*/
        Fragment fragment = ((SampleFragmentPagerAdapter) viewPager.getAdapter()).getFragment(1);
        if (fragment instanceof map) {
            ((map) fragment).screenshot();
            ((map) fragment).cleanmygooglemapwebview();

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {//設定
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.slidefragment, container, false);
        userDetail user=new userDetail(getActivity());
        //int gpssecond=1000;
        //gpsmeter=Integer.parseInt(user.gps());
        if(user.gps().equals("0")) {
            gpsmeter=20;
        }else if(user.gps().equals("1")){
            gpsmeter=10;
        }else if(user.gps().equals("2")){//正常模式下
            gpsmeter=1;
        }
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getChildFragmentManager()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) getActivity().findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
        viewPager.setTag("aaa");




        Button bu=(Button)getActivity().findViewById(R.id.button);
        bu.setOnClickListener(music1);
        Button bu2=(Button)getActivity().findViewById(R.id.button1);
        bu2.setOnClickListener(music2);

        TextView keytx = (TextView) getActivity().findViewById(R.id.textView7);
        keytx.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("save")){
                    reset();
                    //  Toast.makeText(getActivity().getBaseContext(), "clean data",
                    //          Toast.LENGTH_LONG).show();
                }else {
                    if (key) {
                        start();

                        key = false;
                    } else {//I have reset all data here
                        stop();
                        key = true;
                    }
                    //    Toast.makeText(getActivity().getBaseContext(), "go" + key,
                    //           Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        // SampleFragmentPagerAdapter adapter = (SampleFragmentPagerAdapter) viewPager.getAdapter();
        // Fragment a= adapter.getItem(0);
        // TextView tx=(TextView) a.getActivity().findViewById(R.id.textView);
        // tx.setText("WIN!WIN!WIN!WIN!WIN!WIN!WIN!");


        TextView a=(TextView)getActivity().findViewById(R.id.textView);
        TextView b=(TextView)getActivity().findViewById(R.id.textView2);
        TextView c=(TextView)getActivity().findViewById(R.id.textView3);
        TextView d=(TextView)getActivity().findViewById(R.id.textView4);
        TextView e=(TextView)getActivity().findViewById(R.id.textView5);
        ImageView im=(ImageView)getActivity().findViewById(R.id.imageView);

        if(true) {
            im.setVisibility(View.GONE);
            a.setVisibility(View.GONE);
            b.setVisibility(View.GONE);
            c.setVisibility(View.GONE);
            d.setVisibility(View.GONE);
            e.setVisibility(View.GONE);
            bu.setVisibility(View.GONE);
            bu2.setVisibility(View.GONE);
            keytx.setVisibility(View.GONE);
        }


    }

    private View.OnClickListener music1 = new View.OnClickListener() {
        public void onClick(View v) {
            c();
        }
    };
    private View.OnClickListener music2 = new View.OnClickListener() {
        public void onClick(View v) {
            Fragment fragment = ((SampleFragmentPagerAdapter) viewPager.getAdapter()).getFragment(1);
            if (fragment instanceof map) {
                ((map) fragment).centerAt(pointlist.get(0).lat,pointlist.get(0).lon);
            }
        }
    };
    void c(){

        //viewPager.setTag("" + count);
        /*
        pointlist.add(new point(count,121));
        if(count>=1){//畫線
            Toast.makeText(getActivity().getBaseContext(),(count-1)+":"+pointlist.get(count-1).lat+","+pointlist.get(count-1).lon+"\n"+(count)+":"+pointlist.get(count).lat+","+pointlist.get(count).lon, Toast.LENGTH_LONG).show();

        }
        */
        //Toast.makeText(getActivity().getBaseContext(),""+pointlist.get(count).lat+","+pointlist.get(count).lon, Toast.LENGTH_LONG).show();

        count++;

        Log.v("01156109", "now:" + count);
      //  if(viewPager.getCurrentItem()==0) {
            Fragment fragment = ((SampleFragmentPagerAdapter) viewPager.getAdapter()).getFragment(0);
            if (fragment instanceof f1) {
              //  ((f1) fragment).setvalue(""+count);
            }
      //  }

/*
        if(viewPager.getCurrentItem()==1) {
            Fragment fragment = ((SampleFragmentPagerAdapter) viewPager.getAdapter()).getFragment(1);
            if (fragment instanceof map) {
                ((map) fragment).addtomap(23, 121, 0, 121);
                ((map) fragment).centerAt(25.038716, 121.508684);
            }
        }
        */

    }
/*
    double firstlat=0;
    double firstlon=0;
    double secondlat=0;
    double secondlon=0;
    */

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude(); //經度
        double longitude = location.getLongitude(); //緯度
        double altitude = location.getAltitude(); //海拔
        double Speed = location.getSpeed(); //速度
        long time = location.getTime(); //時間

        Date date = new Date(location.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String myDate= sdf.format(date);

        TextView a=(TextView)getActivity().findViewById(R.id.textView);
        TextView b=(TextView)getActivity().findViewById(R.id.textView2);
        TextView c=(TextView)getActivity().findViewById(R.id.textView3);
        TextView d=(TextView)getActivity().findViewById(R.id.textView4);
        TextView e=(TextView)getActivity().findViewById(R.id.textView5);
        a.setText("latitude: "+latitude);
        b.setText("longitude: "+longitude);
        c.setText("altitude: " + altitude);
        d.setText("Speed: "+Speed);
        e.setText("time: " + myDate);

        //correct   count=0 start
        pointlist.add(new point(latitude,longitude));
        if(count==1){
            Fragment fragment = ((SampleFragmentPagerAdapter) viewPager.getAdapter()).getFragment(1);
            if (fragment instanceof map) {
                ((map) fragment).centerAt(pointlist.get(0).lat,pointlist.get(0).lon);
            }
        }
        if(count>=1){//畫線
            Fragment fragment = ((SampleFragmentPagerAdapter) viewPager.getAdapter()).getFragment(1);
            if (fragment instanceof map) {
                double p1_lat=pointlist.get(count-1).lat;
                double p1_lon=pointlist.get(count-1).lon;
                double p2_lat = pointlist.get(count).lat;
                double p2_lon=pointlist.get(count).lon;
                ((map) fragment).addtomap(p1_lat,p1_lon,p2_lat,p2_lon);
                ((map) fragment).centerAt(p2_lat,p2_lon);//隨使用者一起移動螢幕
            }
         //   Toast.makeText(getActivity().getBaseContext(),(count-1)+":"+pointlist.get(count-1).lat+","+pointlist.get(count-1).lon+"\n"+(count)+":"+pointlist.get(count).lat+","+pointlist.get(count).lon, Toast.LENGTH_LONG).show();

        }
        /*this is c*/
        Log.v("01156109", "now:" + count);
        //  if(viewPager.getCurrentItem()==0) {
        Fragment fragment = ((SampleFragmentPagerAdapter) viewPager.getAdapter()).getFragment(0);
        if (fragment instanceof f1) {
            ((f1) fragment).setvalue(count,latitude,longitude,altitude,Speed,time);
        }
        /*this is c end*/
        count++;




      //  Fragment fragment = ((SampleFragmentPagerAdapter)viewPager.getAdapter()).getFragment(0);
      //  fragment.onResume();

        // Sample WeatherLib client init



/*
        String msg = "New Latitude: " + location.getLatitude()
                + "New Longitude: " + location.getLongitude()
                + "altitude: " + location.getAltitude();


        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();*/


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
                    currentWeather.weather.currentCondition.getIcon();
                    IWeatherCodeProvider codeProvider;
                    ImageView im=(ImageView)getActivity().findViewById(R.id.imageView);
                    im.setImageResource(WeatherIconMapper.getWeatherResource(currentWeather.weather.currentCondition.getIcon(), currentWeather.weather.currentCondition.getWeatherId()));

                    Log.d("WL", "City [" + currentWeather.weather.location.getCity() + "] Current temp [" + currentTemp + "]");
               //     Toast.makeText(getActivity().getBaseContext(), "City [" + currentWeather.weather.location.getCity() + "] Current temp [" + currentTemp + "]"+currentWeather.weather.currentCondition.getWeatherId(), Toast.LENGTH_LONG).show();

                }



                @Override

                public void onWeatherError(WeatherLibException e) {

                    Log.d("WL", "Weather Error - parsing data");
            //        Toast.makeText(getActivity().getBaseContext(), "Weather Error - parsing data", Toast.LENGTH_LONG).show();

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
    public void onStatusChanged(String provider, int status, Bundle extras) {

        GPSorNetwork();
    }

    @Override
    public void onProviderEnabled(String provider) {//
    //    Toast.makeText(getActivity().getBaseContext(), "Gps is turned on!! ",
    //            Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onProviderDisabled(String provider) {
     //   Toast.makeText(getActivity().getBaseContext(), "Gps is turned off!! ",
      //          Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();//滑動到第三頁觸發
        Log.v("close", "BlakFragment onDestroyView");
        ngistask.dbdata=new HashMap<Integer,Double>();
        stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("close", "BlakFragment onDestroy");    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.v("close", "BlakFragment onDetach");
    }



}
