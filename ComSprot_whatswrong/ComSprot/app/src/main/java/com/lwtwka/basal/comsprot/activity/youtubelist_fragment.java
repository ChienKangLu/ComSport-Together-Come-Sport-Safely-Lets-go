package com.lwtwka.basal.comsprot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeIntents;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.adapter.listitem;
import com.lwtwka.basal.comsprot.chart;

/**
 * Created by leo on 2015/7/26.
 */
public class youtubelist_fragment extends Fragment {
    public static final String API_KEY = "AIzaSyA5Prb6dLjI0YcfMQhZuGT5-_Kt68ZgDiQ";
    private Toolbar mToolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_youtubelist, container, false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
     //   mToolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
     //   mToolbar.inflateMenu(R.id.chart);
    }

    listitem adapter;
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MyListView L=(MyListView)getActivity().findViewById(R.id.list1);

        listitem adapter=new listitem(getActivity(),1);
        L.setAdapter(adapter);

        L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                TextView title = (TextView) ((RelativeLayout) view).getChildAt(0);
                // startActivity(YouTubeIntents.createPlayVideoIntentWithOptions(getActivity(), "HdjVg4qJPXk", false, false));
                startActivity(YouTubeStandalonePlayer.createVideoIntent(getActivity(),
                        API_KEY, title.getText().toString(), 0, true, true));
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onStop() {
        super.onStop();
        /*
        for (YouTubeThumbnailLoader loader : adapter.youTubeThumbnailLoader) {
            loader.release();
        }*/
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.trash:
                Log.v("������", "good click toolbaritem");
                Intent intent=new Intent();
                intent.setClass(getActivity(),chart.class);
                startActivity(intent);
                // do s.th.
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}
