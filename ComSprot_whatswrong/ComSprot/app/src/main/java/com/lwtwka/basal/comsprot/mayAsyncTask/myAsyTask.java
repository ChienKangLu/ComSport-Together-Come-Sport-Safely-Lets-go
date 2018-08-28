package com.lwtwka.basal.comsprot.mayAsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.adapter.userride_adapter;
import com.lwtwka.basal.comsprot.progressbar.myprogreedialog;
import com.lwtwka.basal.comsprot.userride;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingLeftInAnimationAdapter;

/**
 * Created by leo on 2015/8/9.
 */
public class myAsyTask  extends AsyncTask<Void, Void, Void> {
    userride_adapter adapter;

    Context context;
    userride userride;
    ListView L;
    public  myprogreedialog my;
    public myAsyTask(Context context,userride userride,ListView L) {
        this.context=context;
        this.userride=userride;
        this.L=L;
    }

    @Override
    protected void onPreExecute() {
        my= new myprogreedialog(context, R.style.Custom_Progress);
        my.show();
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            Thread.sleep(1000);
            //listGames = GameResultsCache.getInstance().getGameResults();
            //adapter = new GameResultsAdapter(getBaseContext(), listGames);
             adapter = new userride_adapter(context, 10);
            //listViewGameResults = (ListView)findViewById(R.id.listViewGameResults);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void res) {
       // L.setAdapter(adapter);

        SwingLeftInAnimationAdapter mAnimAdapter = new SwingLeftInAnimationAdapter(adapter);
        mAnimAdapter.setAbsListView(L);
        L.setAdapter(mAnimAdapter);
        /*
        listViewGameResults.setAdapter(adapter);
        listViewGameResults.setDivider(null);
        listViewGameResults.setDividerHeight(0);
        ProgressBar pb = (ProgressBar)findViewById(R.id.progressbar_loading);
        demo.progress.setVisibility(View.GONE);
        */
        my.dismiss();
    }
}
