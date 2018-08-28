package com.lwtwka.basal.comsprot.frd;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.activity.MainActivity;
import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.adapter.analyst_adapter;
import com.lwtwka.basal.comsprot.analyst_chart2;
import com.lwtwka.basal.comsprot.basalmainpagehaha.joinbikeActivity;
import com.lwtwka.basal.comsprot.try_grid_checkbox;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class friend extends Fragment {


    public friend() {
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
        view=inflater.inflate(R.layout.friend, container, false);
        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();
        CircleImageView add =(CircleImageView)view.findViewById(R.id.add);
        add.setOnClickListener(addL);
        CircleImageView check =(CircleImageView)view.findViewById(R.id.check);
        check.setOnClickListener(checkL);

        ListView L=(ListView)getActivity().findViewById(R.id.list1);

        frd_adapter adapter=new frd_adapter(getActivity(),10);
        L.setAdapter(adapter);
/*
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
    /*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.analyst, menu);
    }
*/
    private View.OnClickListener addL = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), addActivity.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener checkL = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), checkActivity.class);
            startActivity(intent);
        }
    };
}
