package com.lwtwka.basal.comsprot;


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

import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.adapter.analyst_adapter;
import com.lwtwka.basal.comsprot.adapter.userride_adapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class analyst extends Fragment {


    public analyst() {
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
        return inflater.inflate(R.layout.fragment_analyst, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
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
                //將Bundle物件assign給intent
                intent.putExtras(bundle);

                intent.setClass(getActivity(), analyst_chart2.class);
                startActivity(intent);
                // TODO Auto-generated method stub
                // TextView title = (TextView) ((RelativeLayout) view).getChildAt(0);


            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.analyst, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.box:
                Log.v("哈哈哈", "good click toolbaritem");
                Intent intent=new Intent();
                intent.setClass(getActivity(),try_grid_checkbox.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
