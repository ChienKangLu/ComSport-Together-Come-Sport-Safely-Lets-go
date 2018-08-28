package com.lwtwka.basal.comsprot.dialog;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.userDetail;

import java.util.ArrayList;

/**
 * Created by leo on 2015/8/14.
 */
public class userridedialog extends DialogFragment {
    View view;

    //ArrayList<String> checkdata=null;
    String id="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.userridedialog, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);//去除title
        Bundle bundle = getArguments();
        if (bundle != null) {
            id=bundle.getString("bikerecordid");
            //checkdata = bundle.getStringArrayList("data");

            // TextView textView = (TextView)view.findViewById(R.id.hahaha);
            // textView.setText(aa);
        }
        if(id!=null){
            Log.v("checkid","good"+id);
        }else{
            Log.v("checkid","bad");
        }
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        //顯示舊的資料
        myDB mydb=new myDB(getActivity());
        mydb.Connect();
        ArrayList<String> data=mydb.getstarend(id);
        mydb.closedb();
        EditText start=(EditText)view.findViewById(R.id.start);
        EditText end=(EditText)view.findViewById(R.id.end);
        start.setText(data.get(0));
        end.setText(data.get(1));

    }
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        myDB mydb=new myDB(getActivity());
        mydb.Connect();
        EditText start=(EditText)view.findViewById(R.id.start);
        EditText end=(EditText)view.findViewById(R.id.end);
        mydb.updatestartend(start.getText().toString(),end.getText().toString(),id);
        mydb.closedb();
        /*
        myDB mydb=new myDB(getActivity());
        userDetail user=new userDetail(getActivity());
        mydb.Connect();
        EditText name=(EditText)view.findViewById(R.id.name);
        String namet=name.getText().toString();
        mydb.insertanalyt(user.id(),namet);
        int analystid=mydb.getlastanalyt();
        Log.v("LATEST", "" + analystid);
        //ArrayList<String> checkdata=new ArrayList<>();
        String insertsql="";
        for(int i=0;i<checkdata.size();i++) {
            if(i!=0){
                insertsql+=",";
            }
            insertsql+="("+analystid+","+checkdata.get(i)+")";
        }
        Log.v("insertsql", "" + insertsql);
        mydb.insertanalytdetail(insertsql);
        getActivity().onBackPressed();
        */
    }
}
