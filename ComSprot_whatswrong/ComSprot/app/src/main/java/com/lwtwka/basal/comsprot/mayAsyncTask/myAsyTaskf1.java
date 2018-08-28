package com.lwtwka.basal.comsprot.mayAsyncTask;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.activity.f1;
import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.adapter.userride_adapter;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.myngis.ngistask;
import com.lwtwka.basal.comsprot.progressbar.myprogreedialog;
import com.lwtwka.basal.comsprot.userDetail;
import com.lwtwka.basal.comsprot.userride;

import java.util.ArrayList;
import java.util.HashMap;

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

    public myAsyTaskf1(Context context,ArrayList<f1.point> pointlist ,String weathers , String dates ,int counts, long now,double totaldistance ,double sumspped ,double sumalt ,double maxspeed,double  maxalt,double temps) {
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
    }

    @Override
    protected void onPreExecute() {
        my= new myprogreedialog(context);
        my.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
// ArrayList<point> pointlist ,String weathers , String dates ,int counts, long now,double totaldistance ,double sumspped ,double sumalt ,double maxspeed,dobule  maxalt,double temps
/*
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
        for(Integer key:f1.APIpoint.keySet()){
            while (!f1.APIpoint.get(key).goodsave){//false
                Log.v("ngis_2","notyet,please wait me"+f1.APIpoint.get(key).num);
            }
        }
////////////
        double totalslope=0;
        double maxslope=0;
        for(int i=0;i<ngistask.dbdata.size();i++){
            totalslope+=ngistask.dbdata.get(i);
            if(ngistask.dbdata.get(i)>maxslope){
                maxslope=ngistask.dbdata.get(i);
            }

        }
        ////////////////////////////////
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
            String dbavgspeed=""+(sumspped/(counts+1));

            //String dbavgalt=""+(sumalt/counts+1);

            Log.v("savedata",""+totalslope+"~"+counts);
            String dbavgalt=""+(totalslope/(counts+1));

            String dbmaxspeed=""+(maxspeed*3600)/1000;

            //String dbmaxalt=""+maxalt;
            String dbmaxalt=""+maxslope;

            String dbgrade="";
            String dbpic="1";
            String dbfav="0";
            String dbtemp=""+temps;
            String dbtitle="BASAL"+mydb.getlatesttitle(""+dbuserid);

            mydb.insertbikerecord(dbuserid,dbweather,dbdate,dbtotalcount,dbtotaltime,dbtotalcal,dbtotaldis,dbavgspeed,dbavgalt,dbmaxspeed,dbmaxalt,dbgrade,dbpic,dbfav,dbtemp,dbtitle);

            ArrayList<String> batch=new ArrayList<String>();
            int batchsize=20;
            String insertsql="";
            //"("+bikerecordid+","+count+",'"+lat+"','"+lon+"','"+speed+"','"+alt+"','"+category+"')"
            //INSERT 陳述式中資料列值數目運算式超過允許的 1000 資料列值數目的上限。

            int bikerecordid=mydb.getlatestinsertbikerecordid();
            //900傳一次
            for(int i=0;i<counts+1;i++) {//lat, double lon, double speed, double alt, int category
                String lat=""+pointlist.get(i).lat;
                String lon=""+pointlist.get(i).lon;
                String speed=""+pointlist.get(i).speed;

                //String alt=""+pointlist.get(i).alt;
                String alt=""+ngistask.dbdata.get(i);

                String category=""+0;
                //mydb.insertbikepoint(bikerecordid,i,lat,lon,speed,alt,category);
                if(i%batchsize!=0){
                    insertsql+=",";
                }
                insertsql+="("+bikerecordid+","+i+",'"+lat+"','"+lon+"','"+speed + "','" + alt+"','"+category+"')";
                if((i+1)%batchsize==0) {
                    batch.add(insertsql);
                    insertsql = "";
                }
            }
            batch.add(insertsql);//最後一筆
            for(int i=0;i<batch.size();i++) {
                mydb.insertbikepoint(batch.get(i));
            }

            ////////////////圖片
             String name=mydb.getimagetite();//id-->n+1
                //insert image
        mydb.insertimage(name);


        //latest bikerecordid

        //update image bikerecord
        mydb.updatebikerecord(name,""+bikerecordid);


        return null;
    }

    @Override
    protected void onPostExecute(Void res) {
        mydb.closedb();
        ngistask.dbdata=new HashMap<Integer,Double>();
        f1.HAHAlist=new ArrayList<ngistask>();
        f1.APIpoint=new HashMap<Integer,ngistask>();
        TextView gg = (TextView) ((Activity)context).findViewById(R.id.gg);
        gg.setText("ready");
        my.dismiss();
    }
}
