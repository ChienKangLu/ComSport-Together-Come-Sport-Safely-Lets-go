package com.lwtwka.basal.comsprot.mysetting;


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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.adapter.analyst_adapter;
import com.lwtwka.basal.comsprot.analyst_chart2;
import com.lwtwka.basal.comsprot.try_grid_checkbox;
//import com.zcw.togglebutton.ToggleButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class setting extends Fragment {


    public setting() {
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
        return inflater.inflate(R.layout.setting, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        /*
        MyListView L=(MyListView)getActivity().findViewById(R.id.list1);

        analyst_adapter adapter=new analyst_adapter(getActivity(),10);
        L.setAdapter(adapter);

        L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView key=(TextView)((RelativeLayout)view).getChildAt(0);
                TextView title=(TextView)((RelativeLayout)view).getChildAt(1);
                TextView date=(TextView)((RelativeLayout)view).getChildAt(2);
                TextView dis=(TextView)((RelativeLayout)view).getChildAt(3);
                Intent intent = new Intent();

                Bundle bundle = new Bundle();
                bundle.putString("id", key.getText().toString());
                bundle.putString("title", title.getText().toString());
                bundle.putString("date", date.getText().toString());
                bundle.putString("dis", dis.getText().toString());
                //�NBundle����assign��intent
                intent.putExtras(bundle);

                intent.setClass(getActivity(), analyst_chart2.class);
                startActivity(intent);
                // TODO Auto-generated method stub
                // TextView title = (TextView) ((RelativeLayout) view).getChildAt(0);


            }
        });
        */
    }

}
