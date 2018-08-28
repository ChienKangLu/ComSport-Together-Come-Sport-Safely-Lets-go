package com.lwtwka.basal.comsprot;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.progressbar.myprogreedialog;

import java.util.ArrayList;
import java.util.logging.LogRecord;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;


public class runtest extends ActionBarActivity {


    CircularProgressBar cpb=null;

    private Handler handler = new Handler() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runtest);
        final Button waitButton = (Button)findViewById(R.id.waitButton);

        cpb=(CircularProgressBar)findViewById(R.id.circular);
        //final String name[]=new String[900];
        final myDB mydb=new myDB(this);
        mydb.Connect();
        waitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                waitButton.setVisibility(View.GONE);
                TextView tx=(TextView)findViewById(R.id.txt);
                tx.setText("good!!!!!!!");
                final myprogreedialog my= new myprogreedialog(runtest.this);
                my.show();
                cpb.setVisibility(View.VISIBLE);
                ((CircularProgressDrawable) cpb.getIndeterminateDrawable()).start();
                // ((CircularProgressDrawable)cpb.getIndeterminateDrawable()).progressiveStop();
                //  ((CircularProgressDrawable)cpb.getIndeterminateDrawable()).start();
               // final Dialog dialog = ProgressDialog.show(runtest.this,
               //         "讀取中", "請等待3秒...", true);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                           // SharedPreferences settings = getActivity().getSharedPreferences("LoginInfo", 0);
                            String userid="1";
                            int dbuserid=Integer.parseInt(userid);
                            String dbweather="";
                            String dbdate="";
                            String dbtotalcount="";
                            String dbtotaltime="";
                            String dbtotalcal="";
                            String dbtotaldis="";
                            String dbavgspeed="";
                            String dbavgalt="";
                            String dbmaxspeed="";
                            String dbmaxalt="";
                            String dbgrade="";
                            String dbpic="";
                            String dbfav="";
                            String dbtemp="";
                            String dbtitle="BASAL"+mydb.getlatesttitle(""+dbuserid);
                            mydb.insertbikerecord(dbuserid, dbweather, dbdate, dbtotalcount, dbtotaltime, dbtotalcal, dbtotaldis, dbavgspeed, dbavgalt, dbmaxspeed, dbmaxalt, dbgrade, dbpic, dbfav, dbtemp, dbtitle);
                          //  mydb.closedb();
                          //  mydb.Connect();
                            int bikerecordid=mydb.getlatestinsertbikerecordid();
                            String insertsql="";
                            ArrayList<String> batch=new ArrayList<String>();
                            int batchsize=20;
                            for(int i=0;i<1893;i++) {//lat, double lon, double speed, double alt, int category
                                String lat="aa";
                                String lon="aa";
                                String speed="aa";
                                String alt="aa";
                                String category="1";
                                //mydb.insertbikepoint(30,i,lat,lon,speed,alt,category);
                                if(i%batchsize!=0){
                                    insertsql+=",";
                                }
                                insertsql+="("+bikerecordid+","+i+",'"+lat+"','"+lon+"','"+speed + "','" + alt+"','"+category+"')";
                                //0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20
                                //0,1,2,3,4,5,6,7,8,9
                                if((i+1)%batchsize==0) {
                                    batch.add(insertsql);
                                    insertsql = "";
                                }
                            }
                            batch.add(insertsql);//最後一筆
                            for(int i=0;i<batch.size();i++) {
                                mydb.insertbikepoint(batch.get(i));
                            }
                            /*
                            for(int i=0;i<900;i++) {
                                mydb.inserttestLu("leo");
                            }
                            */
                           // Thread.sleep(3000);

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                          //  ((CircularProgressDrawable)cpb.getIndeterminateDrawable()).stop();
                            //current value in the text view
                            handler.post(new Runnable() {
                                public void run() {
                                    ((CircularProgressDrawable) cpb.getIndeterminateDrawable()).progressiveStop();
                                }
                            });
                            mydb.closedb();
                            my.dismiss();
                            //dialog.dismiss();
                        }
                    }
                }).start();


            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //((CircularProgressDrawable)cpb.getIndeterminateDrawable()).stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_runtest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
