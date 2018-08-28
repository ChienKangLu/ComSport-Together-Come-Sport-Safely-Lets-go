package com.lwtwka.basal.comsprot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.lwtwka.basal.comsprot.basalmainpagehaha.joinbikeActivity;
import com.lwtwka.basal.comsprot.database.myDB;

import java.util.ArrayList;

public class sosmap extends ActionBarActivity {
    WebView webview;
    String address;
    String type;
    String alotofaddress[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sosmap);
//////////////
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /////////
        webview=(WebView)findViewById(R.id.map);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);

        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if(type.equals("0")){
                address(address,type);
            }else{
                for(int i=0;i<alotofaddress.length;i++){
                    address(alotofaddress[i],type);
                }
            }
            //address("台北市萬華區桂林路64號");
        }
        });
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("file:///android_asset/sosmap.html");
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            type= bundle.getString("type");
            if(type.equals("0")) {
                address = bundle.getString("address");
            }
            else{
                alotofaddress= bundle.getStringArray("address");
                for(String data:alotofaddress)
                Log.v("addr_2",data);
            }
        }
        //address("台北市萬華區昆明街225號")

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sosmap, menu);
        return true;
    }

    public void address(String  address,String type){
        //final String markURL = "javascript:mark(" +(-33.890542) + "," + 151.274856 + ")";
        // webview.loadUrl(markURL);
        final String markURL2 = "javascript:codeAddress('"+address+"',"+type+")";
        webview.loadUrl(markURL2);
        //final String markURL3 = "javascript:line2()";
        //webview.loadUrl(markURL3);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean flag =false;
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                flag=true;
                break;

            default:
                flag=super.onOptionsItemSelected(item);
                break;
        }
        return  flag;
    }
}
