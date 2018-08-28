package com.lwtwka.basal.comsprot.progressbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.lwtwka.basal.comsprot.R;

/**
 * Created by leo on 2015/8/7.
 */
public class myprogreedialog extends ProgressDialog {

    public myprogreedialog(Context context) {
        super(context);
    }
    public myprogreedialog(Context context, int theme) {
        super(context, theme);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

//      progressDialog.setIndeterminate(true);
        setCancelable(false);
        //progressDialog.show()
        setContentView(R.layout.mydialog);
    }
    public void showDialog()
    {
        show();
    }
}
