package com.lwtwka.basal.comsprot;


import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.lwtwka.basal.comsprot.activity.f1;
import com.lwtwka.basal.comsprot.adapter.RoadAdapter;
import com.lwtwka.basal.comsprot.adapter.SampleFragmentPagerAdapter;
import com.lwtwka.basal.comsprot.adapter.sosAdapter;
import com.lwtwka.basal.comsprot.database.myDB;


/**
 * A simple {@link Fragment} subclass.
 */
public class sos extends Fragment implements LocationListener {

    ViewPager viewPager;
    public LocationManager locationManager;


    public sos() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_sos, container, false);
        return view;
    }
    Menu menu ;
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        this.menu=menu;
        inflater.inflate(R.menu.sos, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.box:
                item.setIcon(R.drawable.loacationtarget_b);
                reload();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userDetail user =new userDetail(getActivity());
        if(user.mode().equals("1")) {
            TextView test = (TextView) getActivity().findViewById(R.id.Toplevelvariable);//經緯度做標
            test.setText("120,23");

            ImageView bkloc = (ImageView) view.findViewById(R.id.bkloc);
            bkloc.setVisibility(View.GONE);
        }else{

        }

        viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
        viewPager.setAdapter(new sosAdapter(getChildFragmentManager()));
        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
        viewPager.setTag("aaa");

        if(user.mode().equals("0")) {
            reload();
        }else if(user.mode().equals("1")){

        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onLocationChanged(Location location) {//只執行一次
        double latitude = location.getLatitude(); //經度
        double longitude = location.getLongitude(); //緯度

     //   Toast.makeText(getActivity(), longitude + "," + latitude, Toast.LENGTH_LONG);
        TextView test=(TextView)view.findViewById(R.id.Toplevelvariable);

        test.setText(longitude + "," + latitude);
       // test.setText(121.508716 + "," + 25.041605);///25.041605, 121.508716
        viewPager.setAdapter(new sosAdapter(getChildFragmentManager()));
     //   Fragment fragment1 = ((sosAdapter) viewPager.getAdapter()).getFragment(0);
     //   Fragment fragment2 = ((sosAdapter) viewPager.getAdapter()).getFragment(1);
     //   Fragment fragment3 = ((sosAdapter) viewPager.getAdapter()).getFragment(2);
     //   ((sos_module1) fragment1).setvalue(longitude + "," + latitude);
     //   ((sos_module2) fragment2).setvalue(longitude + "," + latitude);
     ///   ((sos_module3) fragment3).setvalue(longitude + "," + latitude);

        stop();
        menu.getItem(0).setIcon(R.drawable.loacationtarget_w);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

   // private View.OnClickListener music1 = new View.OnClickListener() {
   //     public void onClick(View v) {
    public void reload(){
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);//準確性
            criteria.setAltitudeRequired(true);//是否要用海拔
            criteria.setBearingRequired(false);//是否要用方向性
            criteria.setSpeedRequired(true);//速度
            criteria.setCostAllowed(false);//資費

            String provider = locationManager.getBestProvider(criteria, false);

            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            } else {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));//開啟設定頁面
            }
            start();

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


            Toast.makeText(getActivity().getBaseContext(), "gps location record start",
                   Toast.LENGTH_LONG).show();
        //   } else {
        //       Toast.makeText(getActivity().getBaseContext(), "請開啟定位服務", Toast.LENGTH_LONG).show();
        //       startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));	//開啟設定頁面
        //   }
    }
    public void stop(){
        try {
            locationManager.removeUpdates(this);
            ImageView bkloc = (ImageView) view.findViewById(R.id.bkloc);
            bkloc.setVisibility(View.GONE);
        }catch (NullPointerException e){

        }
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
    @Override
    public void onDestroyView() {
        super.onDestroyView();//滑動到第三頁觸發
        stop();
        Log.v("close", "sos onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("close", "sos onDestroy");    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.v("close", "sos onDetach");
    }
}
