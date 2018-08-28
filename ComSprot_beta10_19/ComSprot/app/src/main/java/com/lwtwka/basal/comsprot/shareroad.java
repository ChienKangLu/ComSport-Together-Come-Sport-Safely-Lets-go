package com.lwtwka.basal.comsprot;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.adapter.road_race_adapter;
import com.lwtwka.basal.comsprot.basalmainpagehaha.road_shareroad_adapter;
import com.lwtwka.basal.comsprot.mayAsyncTask.DownloadImageTask;
import com.lwtwka.basal.comsprot.mayAsyncTask.DownloadImageTask2;
import com.lwtwka.basal.comsprot.mayAsyncTask.myAsyTask;
import com.lwtwka.basal.comsprot.mayAsyncTask.myAsyTask_shareroad;


/**
 * A simple {@link Fragment} subclass.
 */
public class shareroad extends Fragment {


    public shareroad() {
        // Required empty public constructor
    }

    View view=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_shareroad, container, false);
        return view;
    }
    road_shareroad_adapter adapter;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MyListView L=(MyListView)view.findViewById(R.id.list1);
        adapter=new road_shareroad_adapter(getActivity(),10);
        L.setAdapter(adapter);

        //myAsyTask_shareroad HAHA = new myAsyTask_shareroad(getActivity(),L);
        //HAHA.execute();


        L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView key = (TextView) ((RelativeLayout) view).getChildAt(0);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("id", key.getText().toString());
                //將Bundle物件assign給intent
                intent.putExtras(bundle);
                intent.setClass(getActivity(), userride2_detail_share.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }
    public void onDestroyView() {
        super.onDestroyView();//滑動到第三頁觸發
        Log.v("close", "shareroad onDestroyView");
        /*
        for(DownloadImageTask nowtask:adapter.task){
            nowtask.cancel(true);
            nowtask=null;
        }
        for(DownloadImageTask2 nowtask:adapter.task2){
            nowtask.cancel(true);
            nowtask=null;
        }
        */
      //  HAHA.cancel(true);
      //  HAHA=null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("close", "shareroad onDestroy");    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.v("close", "shareroad onDetach");
    }
}
