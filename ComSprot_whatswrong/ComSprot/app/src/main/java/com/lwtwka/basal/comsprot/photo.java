package com.lwtwka.basal.comsprot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.lwtwka.basal.comsprot.photoD.AsyncTaskForPostFile;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class photo extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo, menu);
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
/*
        //註冊AsyncTaskForPostFile完成的通知
        registerReceiver(AsyncTaskForPostFileReceiver, new IntentFilter("PostFileComplete"));
        //取得Download的路徑
        String DownloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        //取得sample.apk的路徑
        String FilePath = DownloadPath + "/record1.png";
        //開始上傳
        AsyncTaskForPostFile PostFile = new AsyncTaskForPostFile(photo.this);
        PostFile.execute(FilePath, null, null);
        */
        /////////////////////////////////////////////
        try {
            URL url=new URL("http://163.14.68.47/pic1/basal.JPG");
            new DownloadImageTask((ImageView) findViewById(R.id.imageView3))
                    .execute("http://163.14.68.47/pic1/basal.JPG");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    private final BroadcastReceiver AsyncTaskForPostFileReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //顯示上傳結束
            Toast.makeText(photo.this, "PostFileComplete", Toast.LENGTH_SHORT).show();
        }
    };
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
