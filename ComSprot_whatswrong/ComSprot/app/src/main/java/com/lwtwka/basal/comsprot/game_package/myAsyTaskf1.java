package com.lwtwka.basal.comsprot.game_package;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.adapter.userride_adapter;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.progressbar.myprogreedialog;
import com.lwtwka.basal.comsprot.userDetail;

import java.util.ArrayList;

/**
 * Created by leo on 2015/8/9.
 */
public class myAsyTaskf1 extends AsyncTask<Void, Void, Void> {
    userride_adapter adapter;
    Context context;
    public  myprogreedialog my;
    ArrayList<f1.point> pointlist ;
    String weathers;
    String dates;
    int counts;
    long now;
    double totaldistance;
    double sumspped;
    double sumalt;
    double maxspeed;
    double  maxalt;
    double temps;
    myDB  mydb;
    String bikerecordid;
    public myAsyTaskf1(Context context, ArrayList<f1.point> pointlist, String weathers, String dates, int counts, long now, double totaldistance, double sumspped, double sumalt, double maxspeed, double maxalt, double temps,String id) {
        this.context=context;
        this.temps = temps;
        this.maxalt = maxalt;
        this.maxspeed = maxspeed;
        this.sumalt = sumalt;
        this.sumspped = sumspped;
        this.totaldistance = totaldistance;
        this.now = now;
        this.counts = counts;
        this.dates = dates;
        this.weathers = weathers;
        this.pointlist = pointlist;
        mydb=new myDB(context);
        this.bikerecordid=id;

    }

    @Override
    protected void onPreExecute() {
        my= new myprogreedialog(context);
        my.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
// ArrayList<point> pointlist ,String weathers , String dates ,int counts, long now,double totaldistance ,double sumspped ,double sumalt ,double maxspeed,dobule  maxalt,double temps

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            mydb.Connect();
            //1)先建立一個bikerecord
            //SharedPreferences settings = getActivity().getSharedPreferences("LoginInfo", 0);

            userDetail user=new userDetail(context);
            String userid=user.id();
            int dbuserid=Integer.parseInt(userid);
            String dbweather=weathers;
            String dbdate=dates;
            String dbtotalcount=""+(counts+1);
            String dbtotaltime= f1.formatLongToTimeStr(now);
            String dbtotalcal=""+f1.calWeight(now,user.weight());
            String dbtotaldis=""+totaldistance;//km
            String dbavgspeed=""+(sumspped/counts+1);
            String dbavgalt=""+(sumalt/counts+1);
            String dbmaxspeed=""+(maxspeed*3600)/1000;
            String dbmaxalt=""+maxalt;
            String dbgrade="";
            String dbpic="1";
            String dbfav="0";
            String dbtemp=""+temps;
            String dbtitle="BASAL"+mydb.getlatesttitle(""+dbuserid);

            Log.v("id5", bikerecordid+"~"+dbuserid+"~"+dbtotaltime+"~"+dbdate);
            mydb.insertrank(bikerecordid,dbuserid,dbtotaltime,dbdate);


        return null;
    }

    @Override
    protected void onPostExecute(Void res) {
        mydb.closedb();

        TextView gg = (TextView)((Activity)context).findViewById(R.id.gg);
        gg.setText("over");
        ((Activity)context).finish();
        my.dismiss();
    }
}
