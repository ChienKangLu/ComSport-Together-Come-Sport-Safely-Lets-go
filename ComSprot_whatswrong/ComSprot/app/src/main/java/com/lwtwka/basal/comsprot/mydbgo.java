package com.lwtwka.basal.comsprot;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.lwtwka.basal.comsprot.database.dbconection;
import com.lwtwka.basal.comsprot.database.myDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class mydbgo extends ActionBarActivity {
   // dbconection DB=null;
    //Connection connect;
    myDB mydb=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydbgo);
        mydb=new myDB(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mydbgo, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        TextView text=(TextView)findViewById(R.id.T);
        mydb.Connect();
        mydb.query();
        mydb.closedb();

        mydb.Connect();
        data=mydb.query();
        mydb.closedb();
        int size=data.size();//有幾筆資料
        int countrow=data.get(0).size();//每筆資料有幾個欄位
        text.setText("" + data.get(0).get(0) + "  " + data.get(0).get(1)+"  "+
                data.get(1).get(0) + "  " +
                data.get(1).get(1)+"()"+size+"[]"+countrow
        );

        /*
        mydb.Connect();
        int dbuserid=userDetail.Uid;
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
        mydb.insertbikerecord(dbuserid,dbweather,dbdate,dbtotalcount,dbtotaltime,dbtotalcal,dbtotaldis,dbavgspeed,dbavgalt,dbmaxspeed,dbmaxalt,dbgrade,dbpic,dbfav,dbtemp);
*/
        //ArrayList<ArrayList<String>> data=
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
