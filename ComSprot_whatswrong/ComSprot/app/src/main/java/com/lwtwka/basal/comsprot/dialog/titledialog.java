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
public class titledialog extends DialogFragment {
    View view;

    String id="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.analystdialog, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);//去除title
        Bundle bundle = getArguments();
        if (bundle != null) {
            id=bundle.getString("bikerecordid");

            // TextView textView = (TextView)view.findViewById(R.id.hahaha);
            // textView.setText(aa);
        }
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        myDB mydb=new myDB(getActivity());
        mydb.Connect();
        ArrayList<String> data=mydb.gettitle(id);
        mydb.closedb();
        EditText title=(EditText)view.findViewById(R.id.name);
        title.setText(data.get(0));
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        myDB mydb=new myDB(getActivity());
        mydb.Connect();
        EditText title=(EditText)view.findViewById(R.id.name);
        mydb.updatetitle(title.getText().toString(), id);
        TextView titlename =(TextView)getActivity().findViewById(R.id.roadname);
        titlename.setText(title.getText().toString());
        mydb.closedb();
    }
}
