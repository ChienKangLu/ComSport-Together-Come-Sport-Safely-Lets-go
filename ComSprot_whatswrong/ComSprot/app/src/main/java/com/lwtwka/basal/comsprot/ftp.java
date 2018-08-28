package com.lwtwka.basal.comsprot;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;


public class ftp extends ActionBarActivity implements View.OnClickListener {

    /*********  work only for Dedicated IP ***********/
    static final String FTP_HOST= "163.14.68.47";

    /*********  FTP USERNAME ***********/
    static final String FTP_USER = "QQ";

    /*********  FTP PASSWORD ***********/
    static final String FTP_PASS  ="basal";

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ftp);

        btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(this);
    }

    public void onClick(View v) {

        /********** Pick file from sdcard *******/
        File f = new File("");
        if(f.exists()){
            Toast.makeText(getBaseContext(), " has file", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getBaseContext(), " gg", Toast.LENGTH_SHORT).show();

        }

        // Upload sdcard file
        uploadFile(f);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ftp, menu);
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
    }

    public void uploadFile(File fileName){


        FTPClient client = new FTPClient();

        try {

            client.connect(FTP_HOST,21);
            client.login(FTP_USER, FTP_PASS);
            client.setType(FTPClient.TYPE_BINARY);
            client.changeDirectory("/pic1/");
            client.upload(fileName, new MyTransferListener());

        } catch (Exception e) {
            e.printStackTrace();
            try {
                client.disconnect(true);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    /*******  Used to file upload and show progress  **********/

    public class MyTransferListener implements FTPDataTransferListener {

        public void started() {

            btn.setVisibility(View.GONE);
            // Transfer started
            Toast.makeText(getBaseContext(), " Upload Started ...", Toast.LENGTH_SHORT).show();
            //System.out.println(" Upload Started ...");
        }

        public void transferred(int length) {

            // Yet other length bytes has been transferred since the last time this
            // method was called
            Toast.makeText(getBaseContext(), " transferred ..." + length, Toast.LENGTH_SHORT).show();
            //System.out.println(" transferred ..." + length);
        }

        public void completed() {

            btn.setVisibility(View.VISIBLE);
            // Transfer completed

            Toast.makeText(getBaseContext(), " completed ...", Toast.LENGTH_SHORT).show();
            //System.out.println(" completed ..." );
        }

        public void aborted() {

            btn.setVisibility(View.VISIBLE);
            // Transfer aborted
            Toast.makeText(getBaseContext(),"transfer aborted ,please try again...", Toast.LENGTH_SHORT).show();
            //System.out.println(" aborted ..." );
        }

        public void failed() {

            btn.setVisibility(View.VISIBLE);
            // Transfer failed
            System.out.println(" failed ..." );
        }

    }
}
