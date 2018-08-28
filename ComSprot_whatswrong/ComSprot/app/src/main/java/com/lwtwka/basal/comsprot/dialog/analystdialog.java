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

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.userDetail;

import java.util.ArrayList;

/**
 * Created by leo on 2015/8/14.
 */
public class analystdialog extends DialogFragment {
    View view;

    ArrayList<String> checkdata=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.analystdialog, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);//去除title
        Bundle bundle = getArguments();
        if (bundle != null) {
            checkdata = bundle.getStringArrayList("data");

            // TextView textView = (TextView)view.findViewById(R.id.hahaha);
            // textView.setText(aa);
        }
        if(checkdata!=null){
            Log.v("checkdata","good");
        }else{
            Log.v("checkdata","bad");
        }
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
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
    }
}
