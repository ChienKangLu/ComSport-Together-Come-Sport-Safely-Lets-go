package com.lwtwka.basal.comsprot.mayAsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lwtwka.basal.comsprot.adapter.road_race_adapter;
import com.lwtwka.basal.comsprot.progressbar.myprogreedialog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by leo on 2015/9/1.
 */
public  class race_catch_detail extends AsyncTask<String, Void,  ArrayList<String>> {

    Context context;
    String importurl;
    public  myprogreedialog my;
    TextView name;
    TextView date;
    TextView descript;
    TextView dis;
    TextView asign;
    TextView urltx;
    ImageView im;
    TextView title;
    public race_catch_detail(String importurl, Context context,TextView name,TextView date,TextView descript,TextView dis,TextView asign,TextView urltx,ImageView im,TextView title) {
        this.context=context;
        this.importurl=importurl;
        this.name=name;
        this.date=date;
        this.descript=descript;
        this.dis=dis;
        this.asign=asign;
        this.urltx=urltx;
        this.im=im;
        this.title=title;
    }
    @Override
    protected void onPreExecute() {
        my= new myprogreedialog(context);
        my.show();
    }

    protected  ArrayList<String> doInBackground(String... urls) {

        ArrayList<String> innerdatadate= new ArrayList<String>();
        try {
            Document doc = null;
                String title="";
                String date="";
                String sign="";
                String dis="";
                String note="";
                String urln2="";
                String url="";
                String im="";
                Document doc2 = null;
                urln2 = importurl;
                doc2 = Jsoup.connect(urln2).get();
                Elements titleele=doc2.select(".article h1");
                title=(titleele.get(0).text()).toString();
                Elements dateele=doc2.select(".tblstyle1 tbody tr td span");
                date=(dateele.get(0).text()).toString();
                sign=(dateele.get(3).text()).toString();
                Elements disele=doc2.select(".tabB .tblstyle1 tbody tr td span");
                dis=disele.get(2).text().toString();
                note=disele.get(8).text().toString();
                Elements urlele=doc2.select(".tblstyle1 tbody tr td span a");
                try {
                    url = (urlele.get(0).attr("href"));
                    ////////////////////////////
                }catch (Exception e){
                }

            Elements imele=doc2.select(".figure img");
            try {
                im = "http://solomo.xinmedia.com"+(imele.get(0).attr("src"));
            }catch (Exception e){
                im="nothing";
            }
            innerdatadate.add(title);//0
            innerdatadate.add(date);//1
            innerdatadate.add(sign);//2
            innerdatadate.add(dis);//3
            innerdatadate.add(note);//4
            innerdatadate.add(url);//5
            innerdatadate.add(im);//6
            System.out.println(title+" "+date+" "+sign+" "+dis+" "+note+" "+url+" "+im);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return innerdatadate;
    }

    protected void onPostExecute( ArrayList<String> res) {
      //  Log.v("json", result);
        name.setText(res.get(0));
        date.setText(res.get(1));
        descript.setText(res.get(4));
        dis.setText(res.get(3));
        asign.setText(res.get(2));
      //  urltx.setText(res.get(5));
        title.setText(res.get(0));

        urltx.setMovementMethod(LinkMovementMethod.getInstance());
        urltx.setText(Html.fromHtml("<a href=\"" + res.get(5) + "\" target=\"_blank\">相關網站連結</a>"));
        my.dismiss();
        new DownloadImageTask(im)
                .execute(res.get(6));//image38
    }

}