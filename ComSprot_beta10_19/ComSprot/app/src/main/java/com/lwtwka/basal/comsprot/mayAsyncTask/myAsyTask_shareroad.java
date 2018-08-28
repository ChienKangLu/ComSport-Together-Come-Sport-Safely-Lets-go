package com.lwtwka.basal.comsprot.mayAsyncTask;

import android.content.Context;
import android.os.AsyncTask;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.adapter.road_shareroad_adapter;
import com.lwtwka.basal.comsprot.adapter.userride_adapter;
import com.lwtwka.basal.comsprot.progressbar.myprogreedialog;
import com.lwtwka.basal.comsprot.userride;

/**
 * Created by leo on 2015/8/9.
 */
public class myAsyTask_shareroad extends AsyncTask<Void, Void, Void> {
    road_shareroad_adapter adapter;

    Context context;
    MyListView L;
    public  myprogreedialog my;
    public myAsyTask_shareroad(Context context,MyListView L) {
        this.context=context;
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
            Thread.sleep(3000);
            //listGames = GameResultsCache.getInstance().getGameResults();
            //adapter = new GameResultsAdapter(getBaseContext(), listGames);
             adapter = new road_shareroad_adapter(context, 10);
            //listViewGameResults = (ListView)findViewById(R.id.listViewGameResults);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void res) {
        L.setAdapter(adapter);
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
