package com.lwtwka.basal.comsprot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.urlimageviewhelper.UrlImageViewCallback;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.game_package.game_frame;
import com.lwtwka.basal.comsprot.game_package.rank;
import com.lwtwka.basal.comsprot.mayAsyncTask.DownloadImageTask;


public class trycard extends Fragment {
    String totaltime;
    String totaldis;
    String avgspeed;
    String totalcal;
    String maxspeed;
    String maxalt;
    String avgalt;
    String title;
    String pic;
    String name;
    String id;
    String realdis;

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.activity_trycard, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            totaltime = bundle.getString("totaltime");
            totaldis = bundle.getString("totaldis");
            avgspeed = bundle.getString("avgspeed");
            totalcal = bundle.getString("totalcal");
            maxspeed = bundle.getString("maxspeed");
            maxalt = bundle.getString("maxalt");
            avgalt = bundle.getString("avgalt");
            title = bundle.getString("title");
            pic = bundle.getString("pic");
            name = bundle.getString("name");
            id = bundle.getString("id");
            realdis = bundle.getString("realdis");
            //A~B~C~D~E~F
            //20~40~60~80~100
            int score1=0;
            int score2=0;
            double scoretime=counttime(totaltime);
            if(scoretime>=0&&scoretime<5){
                score1=10;
            }else if(scoretime>=5&&scoretime<10){
                score1=20;
            }else if(scoretime>=10&&scoretime<15){
                score1=30;
            }else if(scoretime>=15&&scoretime<20){
                score1=40;
            }else if(scoretime>=20){
                score1=50;
            }
            double scoredis=Double.parseDouble(realdis);
            if(scoredis>=0&&scoredis<0.1){
                score2=10;
            }else if(scoredis>=0.1&&scoredis<0.5){
                score2=20;
            }else if(scoredis>=0.5&&scoredis<1){
                score2=30;
            }else if(scoredis>=1&&scoredis<1.5){
                score2=40;
            }else if(scoredis>=1.5){
                score2=50;
            }
            int finalscore=score1+score2;
            Log.v("score",""+finalscore);
            //A~B~C~D~E~F
            //20~40~60~80~100
            //~~~~~~~~~~~~~~~~~~``
            ImageView level=(ImageView)view.findViewById(R.id.catgr);
            ImageView star1=(ImageView)view.findViewById(R.id.star1);
            ImageView star2=(ImageView)view.findViewById(R.id.star2);
            ImageView star3=(ImageView)view.findViewById(R.id.star3);
            ImageView star4=(ImageView)view.findViewById(R.id.star4);
            ImageView star5=(ImageView)view.findViewById(R.id.star5);

            if(finalscore>=0&&finalscore<20){//1
                level.setImageResource(R.drawable.veryeasy_icon);
                star5.setImageResource(R.drawable.favorite2);
                star4.setImageResource(R.drawable.favorite2);
                star3.setImageResource(R.drawable.favorite2);
                star2.setImageResource(R.drawable.favorite2);

            }else if(finalscore>=20&&finalscore<40){//2v
                level.setImageResource(R.drawable.easy_icon);
                star5.setImageResource(R.drawable.favorite2);
                star4.setImageResource(R.drawable.favorite2);
                star3.setImageResource(R.drawable.favorite2);
            }else if(finalscore>=40&&finalscore<60){//3v
                level.setImageResource(R.drawable.com_icon);
                star5.setImageResource(R.drawable.favorite2);
                star4.setImageResource(R.drawable.favorite2);
            }else if(finalscore>=60&&finalscore<80){//4
                level.setImageResource(R.drawable.hard_icon);
                star5.setImageResource(R.drawable.favorite2);
            }else if(finalscore>=80){//5v
                level.setImageResource(R.drawable.veryhard_icon);
            }


            //00'01'05  50
            //0.2 km    50

            //7.6 km/hr
            //3.8 kcal
            //13.4km/hr
            //56.0 公尺
            //31.9 公尺

            //BASAL10 1
            //王慧縈
            //58
            String dd=totaltime+" "+
                    totaldis+" "+
                    avgspeed+" "+
                    totalcal+" "+
                    maxspeed+" "+
                    maxalt+" "+
                    avgalt+" "+
                    title+" "+
                    pic+" "+
                    name+" "+
                    id;
            Log.v("trycardbundle",dd);
        }
        TextView idt=(TextView)view.findViewById(R.id.id);
        idt.setText(id);

        TextView dis=(TextView)view.findViewById(R.id.dis);
        dis.setText(totaldis);

        TextView time=(TextView)view.findViewById(R.id.time);
        time.setText(totaltime);

        TextView who=(TextView)view.findViewById(R.id.who);
        who.setText(name);

        TextView titlet=(TextView)view.findViewById(R.id.title);
        titlet.setText(title);

        ImageView im=(ImageView)view.findViewById(R.id.im);

       // mageView roadpic = (ImageView) row.findViewById(R.id.roadpic);
        //String name="image"+truedata.get(position).get(7);
        UrlImageViewHelper.setUrlDrawable(im, "http://163.14.68.47/map/" + "image"+pic + ".png", R.drawable.map_loading1, new UrlImageViewCallback() {
            @Override
            public void onLoaded(ImageView imageView, Bitmap loadedBitmap, String url, boolean loadedFromCache) {
                if (!loadedFromCache) {
                    ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
                    scale.setDuration(300);
                    scale.setInterpolator(new OvershootInterpolator());
                    imageView.startAnimation(scale);
                }
            }
        });
        /*
        new DownloadImageTask(im)
                .execute("http://163.14.68.47/map/" + "image"+pic + ".png");
                */
       // Button got=(Button)view.findViewById(R.id.go);startbtn
       // got.setOnClickListener(go);
         ImageView startbtn=(ImageView)view.findViewById(R.id.startbtn);
         startbtn.setOnClickListener(go);

        ImageView rank=(ImageView)view.findViewById(R.id.rank);
        rank.setOnClickListener(gorank);

    }
    private View.OnClickListener go = new View.OnClickListener() {
        public void onClick(View v) {
            //TextView key=(TextView)((RelativeLayout)view).getChildAt(1);
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
           // bundle.putString("id", key.getText().toString());
            //將Bundle物件assign給intent
            bundle.putString("id",id);
            Log.v("id1",id);
            intent.putExtras(bundle);
            intent.setClass(getActivity(), game_frame.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener gorank = new View.OnClickListener() {
        public void onClick(View v) {
            //TextView key=(TextView)((RelativeLayout)view).getChildAt(1);
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            // bundle.putString("id", key.getText().toString());
            //將Bundle物件assign給intent
            bundle.putString("id",id);
            intent.putExtras(bundle);
            intent.setClass(getActivity(), rank.class);
            startActivity(intent);
        }
    };
    public double counttime(String a){
        //String a="00'00'20";
        String time[]=a.split("'");
        int second=0;
        for(int i=0;i<3;i++){
            int b=Integer.parseInt(time[i]);
            if(i==0){
                b=b*60*60;
            }else if(i==1){
                b=b*60;
            }else{
                b=b;
            }
            second+=b;
        }
        double second2=(double)second/(60);
        System.out.println(second2);//單位分鐘
        return second2;
    }
    public double countdis(String ff){
       // String ff="0.2 km";
        ff=ff.replaceAll(" km", "");
        double ff2=Double.parseDouble(ff);
        System.out.println(ff2);
        return ff2;
    }

}
