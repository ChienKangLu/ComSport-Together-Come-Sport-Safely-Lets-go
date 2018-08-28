package com.lwtwka.basal.comsprot;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.adapter.RoadAdapter;
import com.lwtwka.basal.comsprot.adapter.listitem;
import com.lwtwka.basal.comsprot.adapter.road_race_adapter;
import com.lwtwka.basal.comsprot.adapter.userride_adapter;
import com.lwtwka.basal.comsprot.mayAsyncTask.race_catch;
import com.lwtwka.basal.comsprot.myjson.jsontask;


/**
 * A simple {@link Fragment} subclass.
 */
public class road_race extends Fragment {


    public road_race() {
        // Required empty public constructor
    }

    View view=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_road_race, container, false);
        return view;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    race_catch HAHA;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView L=(ListView)view.findViewById(R.id.list1);
        HAHA=new race_catch(L,getActivity(),view);
                HAHA.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


                        // road_race_adapter adapter=new road_race_adapter(getActivity(),10);
                        // L.setAdapter(adapter);

                        L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {
                                Intent intent = new Intent();

                                TextView url = (TextView) ((RelativeLayout) view).findViewById(R.id.url);
                                Bundle bundle = new Bundle();
                                bundle.putString("url", url.getText().toString());
                                //將Bundle物件assign給intent
                                intent.putExtras(bundle);
                                intent.setClass(getActivity(), race_detail.class);
                                startActivity(intent);
                                // TODO Auto-generated method stub
                                // TextView title = (TextView) ((RelativeLayout) view).getChildAt(0);

                            }
                        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();//滑動到第三頁觸發
        Log.v("close", "road_race onDestroyView");
        HAHA.cancel(true);
        HAHA=null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("close", "road_race onDestroy");    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.v("close", "road_race onDetach");
    }
}
