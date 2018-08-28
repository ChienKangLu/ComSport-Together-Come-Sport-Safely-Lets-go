package com.lwtwka.basal.comsprot.activity;


import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lwtwka.basal.comsprot.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class f2 extends Fragment implements LocationListener {


    public f2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);//�ǽT��
        criteria.setAltitudeRequired(true);//�O�_�n�ή���
        criteria.setBearingRequired(false);//�O�_�n�Τ�V��
        criteria.setSpeedRequired(true);//�t��
        criteria.setCostAllowed(false);//��O

        String provider = locationManager.getBestProvider(criteria, false);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
        Toast.makeText(getActivity().getBaseContext(), "f2 onCreate location start",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.f2, container, false);
    }
    LocationManager locationManager;
    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude(); //�g��
        double longitude = location.getLongitude(); //�n��
        double altitude = location.getAltitude(); //����
        double Speed = location.getSpeed(); //�t��
        double time = location.getTime(); //�ɶ�
        TextView a=(TextView)getActivity().findViewById(R.id.textView);
        TextView b=(TextView)getActivity().findViewById(R.id.textView2);
        TextView c=(TextView)getActivity().findViewById(R.id.textView3);
        TextView d=(TextView)getActivity().findViewById(R.id.textView4);
        TextView e=(TextView)getActivity().findViewById(R.id.textView5);
        a.setText("latitude: "+latitude);
        b.setText("longitude: "+longitude);
        c.setText("altitude: "+altitude);
        d.setText("Speed: "+Speed);
        e.setText("time: " + time);
/*
        String msg = "New Latitude: " + location.getLatitude()
                + "New Longitude: " + location.getLongitude()
                + "altitude: " + location.getAltitude();


        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();*/
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getActivity().getBaseContext(), "Gps is turned on!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getActivity().getBaseContext(), "Gps is turned off!! ",
                Toast.LENGTH_SHORT).show();
    }


}
