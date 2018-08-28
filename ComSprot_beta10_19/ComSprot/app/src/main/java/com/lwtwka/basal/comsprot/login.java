package com.lwtwka.basal.comsprot;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lwtwka.basal.comsprot.activity.MainActivity;
import com.lwtwka.basal.comsprot.animation.ActivityAnimator;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.mayAsyncTask.DownloadImageTask;
import com.lwtwka.basal.comsprot.mayAsyncTask.DownloadImageTask2;
import com.lwtwka.basal.comsprot.screenshot.MagicFileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class login extends ActionBarActivity {
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    @Override
    protected void onResume() {
        super.onResume();
        Button go=(Button)findViewById(R.id.loginB);
        go.setOnClickListener(music1);

        TextView add=(TextView)findViewById(R.id.add);
        add.setOnClickListener(music2);
        File file = new File("/data/data/com.lwtwka.basal.comsprot/shared_prefs","LoginInfo.xml");
        if(file.exists()){
         //   new DownloadImageTask2()
          //          .execute("http://163.14.68.47/map/" + "image14" + ".png");
         //   userDetail.i=1;
           // new DownloadImageTask(im)
           //         .execute("http://163.14.68.47/map/" + "image14" + ".png");
            settings = getSharedPreferences("LoginInfo",0);
            String account=settings.getString("account", "");
            String code=settings.getString("code", "");
            if(!account.equals("")&&!code.equals("")){
                Intent reit = new Intent();
                reit.setClass(login.this,MainActivity.class);
                startActivity(reit);
                login.this.finish();
            }
        }

    }
    private View.OnClickListener music1 = new View.OnClickListener() {
        public void onClick(View v) {
            EditText account=(EditText)findViewById(R.id.account);
            EditText code=(EditText)findViewById(R.id.code);
            //if(資料正確才存入SharedPreferences)
            myDB mydb=new myDB(login.this);
            mydb.Connect();
            if(mydb.login(account.getText().toString(),code.getText().toString())) {
                //
                String userid =mydb.getuserid(account.getText().toString(), code.getText().toString());
                ArrayList<String> usertotal= mydb.getusertotal(userid);
                mydb.closedb();
                settings = getSharedPreferences("LoginInfo", 0);
                settings.edit()
                        .putString("userid", usertotal.get(0))
                        .putString("height",usertotal.get(1))
                        .putString("weight",usertotal.get(2))
                        .putString("age",usertotal.get(3))
                        .putString("account", usertotal.get(4))
                        .putString("code", usertotal.get(5))
                        .putString("name", usertotal.get(6))
                        .putString("applydate", usertotal.get(7))
                        .putString("email", usertotal.get(8))
                        .putString("pic", usertotal.get(9))
                        .commit();

                Intent reit = new Intent();
                reit.setClass(login.this, MainActivity.class);
                startActivity(reit);
                login.this.finish();
            }else{
                Toast.makeText(login.this,"帳號密碼錯誤",

                        Toast.LENGTH_LONG).show();
            }

        }
    };
    private View.OnClickListener music2 = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(login.this, regist.class);
            startActivity(intent);
        }
    };


}
