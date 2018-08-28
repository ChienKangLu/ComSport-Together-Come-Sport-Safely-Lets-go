package com.lwtwka.basal.comsprot;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.adapter.sos_module1_adapter;
import com.lwtwka.basal.comsprot.adapter.sos_module2_adapter;
import com.lwtwka.basal.comsprot.myjson.jsontask;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ScrollDirectionListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class sos_module2 extends Fragment {


    public sos_module2() {
        // Required empty public constructor
    }


    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_sos_module1, container, false);
        return view;
    }
    jsontask HAHA;
    ListView L;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView test=(TextView)getActivity().findViewById(R.id.Toplevelvariable);//經緯度做標
        setvalue(test.getText().toString());
        String location[]=test.getText().toString().split(",");

        L=(ListView)view.findViewById(R.id.list1);
        //sos_module2_adapter adapter=new sos_module2_adapter(getActivity(),10);
        /*
        new jsontask(L,2,getActivity(),view)
                .execute("http://maps.google.com/maps/api/geocode/json?latlng=" + location[1] + "," + location[0] + "&language=zh-CN&sensor=true");//23,120  image38
*/
        if(!location[0].equals("notyet")) {
            HAHA = new jsontask(L, 2, getActivity(), view);
            HAHA.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "http://maps.google.com/maps/api/geocode/json?latlng=" + location[1] + "," + location[0] + "&language=zh-CN&sensor=true");//23,120  image38
            //    .execute("http://maps.google.com/maps/api/geocode/json?latlng=" + location[1] + "," + location[0] + "&language=zh-CN&sensor=true");//23,120  image38
        }
        ///////////////
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabicon);
        fab.attachToListView(L, new ScrollDirectionListener() {
            @Override
            public void onScrollDown() {
                Log.d("ListViewFragment", "onScrollDown()");
            }

            @Override
            public void onScrollUp() {
                Log.d("ListViewFragment", "onScrollUp()");
            }
        }, new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d("ListViewFragment", "onScrollStateChanged()");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d("ListViewFragment", "onScroll()");
            }
        });
        fab.setOnClickListener(goallmap);
    }

    @Override
    public void onResume() {
        super.onResume();

        /*
        L.setAdapter(adapter);

        L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //     Intent intent = new Intent();
                //     intent.setClass(getActivity(), userride2_detail.class);
                //     startActivity(intent);
                // TODO Auto-generated method stub


            }
        });
        */

    }

    public void setvalue(String a){

        TextView test=(TextView)view.findViewById(R.id.test);
        test.setText(a);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();//滑動到第三頁觸發
        Log.v("close", "sos_module2 onDestroyView");
        if(HAHA!=null) {
            HAHA.cancel(true);
            HAHA = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("close", "sos_module2 onDestroy");    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.v("close", "sos_module2 onDetach");
    }
    private View.OnClickListener goallmap = new View.OnClickListener() {
        public void onClick(View v) {
            ArrayList<ArrayList<String>> data=((sos_module2_adapter)(L.getAdapter())).data;
            String [] newdata=new String[data.size()];
            for(int i=0;i<data.size();i++){
                newdata[i]=data.get(i).get(1);
            }
            if(data.size()>0) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), sosmap.class);
                Bundle bundle = new Bundle();
                bundle.putString("type", "1");
                bundle.putStringArray("address", newdata);
                intent.putExtras(bundle);
                startActivity(intent);
            }


        }
    };

}
