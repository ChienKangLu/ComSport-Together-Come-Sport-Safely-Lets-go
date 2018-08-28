package com.lwtwka.basal.comsprot.basalmainpagehaha;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.adapter.analyst_adapter;
import com.lwtwka.basal.comsprot.analyst_chart2;
import com.lwtwka.basal.comsprot.try_grid_checkbox;


/**
 * A simple {@link Fragment} subclass.
 */
public class picitem extends Fragment {


    public picitem() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        ImageView im=(ImageView)getActivity().findViewById(R.id.im);
        im.setImageResource(R.drawable.king);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.analyst, menu);
    }

}
