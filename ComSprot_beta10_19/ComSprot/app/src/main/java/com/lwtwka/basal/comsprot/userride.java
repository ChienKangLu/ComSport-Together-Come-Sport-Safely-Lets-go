package com.lwtwka.basal.comsprot;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.adapter.listitem;
import com.lwtwka.basal.comsprot.adapter.userride_adapter;
import com.lwtwka.basal.comsprot.mayAsyncTask.myAsyTask;
import com.lwtwka.basal.comsprot.progressbar.myprogreedialog;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;

import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;


/**
 * A simple {@link Fragment} subclass.
 */
public class userride extends Fragment  {

    ListView L=null;
   public  myprogreedialog my=null;
    View view=null;
    public userride() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view=inflater.inflate(R.layout.fragment_userride, container, false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        L=(ListView)getActivity().findViewById(R.id.list1);

    }
    myAsyTask HAHA;
    @Override
    public void onResume() {
        super.onResume();

        HAHA = new myAsyTask(getActivity(),userride.this,L);
        HAHA.execute();

        //my= new myprogreedialog(view.getContext());
          //  userride_adapter adapter = new userride_adapter(getActivity(), 10);
        //    L.setAdapter(adapter);
            L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {

                    TextView key=(TextView)((RelativeLayout)view).getChildAt(0);
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("id", key.getText().toString());
                    //將Bundle物件assign給intent
                    intent.putExtras(bundle);

                    intent.setClass(getActivity(), userride2_detail.class);
                    startActivity(intent);
                    // TODO Auto-generated method stub
                    // TextView title = (TextView) ((RelativeLayout) view).getChildAt(0);

                }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.v("close", "userride onDestroyView");
        HAHA.cancel(true);
        HAHA=null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("close","userride onDestroy");    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.v("close", "userride onDetach");
    }
}
