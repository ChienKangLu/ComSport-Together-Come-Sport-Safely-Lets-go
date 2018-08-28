package com.lwtwka.basal.comsprot.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.To   ast;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.analyst;
import com.lwtwka.basal.comsprot.basalmainpagehaha.basalmain;
import com.lwtwka.basal.comsprot.bikegooglemap.navigation;
import com.lwtwka.basal.comsprot.chart;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.game;
import com.lwtwka.basal.comsprot.login;
import com.lwtwka.basal.comsprot.map;
import com.lwtwka.basal.comsprot.mayAsyncTask.DownloadImageTask;
import com.lwtwka.basal.comsprot.mysetting.setting;
import com.lwtwka.basal.comsprot.road;
import com.lwtwka.basal.comsprot.share;
import com.lwtwka.basal.comsprot.sos;
import com.lwtwka.basal.comsprot.sosmap;
import com.lwtwka.basal.comsprot.userDetail;
import com.lwtwka.basal.comsprot.user_profile;
import com.lwtwka.basal.comsprot.userride;
import com.lwtwka.basal.comsprot.youtubelist;

import java.io.File;


public class MainActivity extends ActionBarActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(this,"我現在上線了onCreate",Toast.LENGTH_LONG).show();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //FragmentDrawer
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);//抽屜的xml

        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout/*最大的ACTIVITY*/), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);
    }

    @Override
    protected void onResume() {//0-->下線   1-->上線  /////////2-->待機
        super.onResume();
       // Toast.makeText(this,"我現在上線了onResume",Toast.LENGTH_LONG).show();
        if(userDetail.changeonline==0) {
            myDB mydb = new myDB(this);
            mydb.Connect();
            userDetail user = new userDetail(this);
            mydb.updateonline(1, user.id());
            mydb.closedb();
        }
    }
    /*
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"我現在下線了onPause",Toast.LENGTH_LONG).show();
        myDB mydb=new myDB(this);
        mydb.Connect();
        userDetail user=new userDetail(this);
        mydb.updateonline(0, user.id());
        mydb.closedb();
    }
    */

    @Override
    protected void onStop() {
        super.onStop();
        if(userDetail.changeonline==0) {
           // Toast.makeText(this, "我現在下線了onStop", Toast.LENGTH_LONG).show();
            myDB mydb = new myDB(this);
            mydb.Connect();
            userDetail user = new userDetail(this);
            mydb.updateonline(0, user.id());
            mydb.closedb();
        }
    }
/*
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"我現在下線了onDestroy",Toast.LENGTH_LONG).show();
        myDB mydb=new myDB(this);
        mydb.Connect();
        userDetail user=new userDetail(this);
        mydb.updateonline(0, user.id());
        mydb.closedb();

    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//上方的圖示點擊
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.action_search){
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDrawerItemSelected(View view, int position) {
            displayView(position);
    }//判斷點擊的項目

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        String tag="";
        switch (position) {
            case 0:

                tag="f0";
                fragment = new basalmain();
                title = "首頁";
                break;
            case 1:

                tag="f0";
                fragment = new sos();
                //fragment = new map();
                title = "SOS模組";

                //fragment = new map();
                /*
                Uri gmmIntentUri = Uri.parse("google.navigation:q=Taronga+Zoo,+Sydney+Australia");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                this.startActivity(mapIntent);
                */
/*
                Intent intent = new Intent();
                intent.setClass(this, sosmap.class);
                this.startActivity(intent);
*/
                break;
            case 2:
                tag="f2";
               // fragment = new MessagesFragment();
               // fragment = new fragmentdrawer2();
                fragment = new game();
                title = "game";
                break;

            /////////////////////////////////////////////////////////////////////////
            case 3:
                fragment = new navigation();
                title = "單車導航";
                /*
                double lat=25.03883723;
                double lon=121.5039921;
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+lat+","+lon);//google.navigation:q=latitude,longitude
                //google.navigation:q=Taronga+Zoo,+Sydney+Australia
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                */
                break;
            case 4://設定
                fragment = new setting();
                title = "設定";
                break;
            case 5://登出
                File file = new File("/data/data/com.lwtwka.basal.comsprot/shared_prefs","LoginInfo.xml");
                if(file.exists()){
                    file.delete();
                    Intent reit = new Intent();
                    reit.setPackage("com.google.android.apps.maps");
                    reit.setClass(MainActivity.this,login.class);
                    startActivity(reit);
                    MainActivity.this.finish();

                }
                break;

            case 6:
                tag="f3";
                fragment = new user_profile();
                title = "個人資料";

                break;
            case 7:
                tag="f4";
                // fragment = new MessagesFragment();
                // fragment = new fragmentdrawer2();
                fragment = new BlankFragment();
                title = "ComSprot";
          //      Intent intent2=new Intent();
          //      intent2.setClass(this,youtubelist.class);
          //      startActivity(intent2);
                break;
            case 8:
                tag="f4";
                fragment = new analyst();
                title = "分析資料";
                break;
            case 9:
                tag="f6";
                // fragment = new MessagesFragment();
                // fragment = new fragmentdrawer2();
                fragment = new userride();
                title = "個人路線";
                break;
            case 10:

                tag="f7";
                // fragment = new MessagesFragment();
                // fragment = new fragmentdrawer2();
                fragment = new share();
                title = "社群分享";
                break;
            case 11:
                tag="f8";
                // fragment = new MessagesFragment();
                // fragment = new fragmentdrawer2();
                fragment = new road();
                title = "參考路線";

                break;
            case 12:
                tag="f9";
                // fragment = new MessagesFragment();
                // fragment = new fragmentdrawer2();
                fragment = new youtubelist_fragment();
                title = "路線影片";
                break;
            default:
                break;
        }

        SharedPreferences settings = getSharedPreferences("LoginInfo",0);
        String account=settings.getString("account", "");
        String code=settings.getString("code","");
        String userid=settings.getString("userid","");
 //       Toast.makeText(this,"[account=("+account+"),code=("+code+"),userod=("+userid+")]",
        //               Toast.LENGTH_LONG).show();
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment, tag);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();
       //     Toast.makeText(this.getBaseContext(), tag,
       //             Toast.LENGTH_LONG).show();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }
}
