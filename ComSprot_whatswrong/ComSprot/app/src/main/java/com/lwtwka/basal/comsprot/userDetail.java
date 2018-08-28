package com.lwtwka.basal.comsprot;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import com.lwtwka.basal.comsprot.database.myDB;

/**
 * Created by leo on 2015/7/24.
 */
public class userDetail {
    public static Bitmap photo;
    public static int i=0;
    public static int changeonline=0;

    /*
    * .putString("userid", usertotal.get(0))
                        .putString("height",usertotal.get(1))
                        .putString("weight",usertotal.get(2))
                        .putString("age",usertotal.get(3))
                        .putString("account", usertotal.get(4))
                        .putString("code", usertotal.get(5))
                        .putString("name", usertotal.get(6))
                        .putString("applydate", usertotal.get(7))
    */
    SharedPreferences settings=null;
    public static double[] KCAL={3,9.7};//�M�}��(8.8����/�p��)(20.9����/�p��)
    Context context=null;
    public  userDetail(Context context) {
        this.context=context;
        settings=context.getSharedPreferences("LoginInfo",0);
    }
    public String id(){
        return settings.getString("userid", "");
    }
    public String height(){
        return settings.getString("height", "");
    }
    public int weight(){
        return  (int)Double.parseDouble(settings.getString("weight", ""));
    }
    public String age(){
        return settings.getString("age", "");
    }
    public String account(){
        return settings.getString("account", "");
    }
    public String code(){
        return settings.getString("code", "");
    }
    public String name(){
        return settings.getString("name", "");
    }
    public String applydate(){
        return settings.getString("applydate", "");
    }
    public String email(){
        return settings.getString("email", "");
    }
    public String pic(){
        return settings.getString("pic", "");
    }
    public String gps(){
        return settings.getString("gps", "");
    }
    public String mode(){
        return settings.getString("mode", "");
    }
    public void edit(String title,String value){
        myDB mydb =new myDB(context);
        mydb.Connect();
        mydb.updateheight(title, value, id());
        mydb.closedb();
        settings.edit()
                .putString(title, value).commit();
    }

}
