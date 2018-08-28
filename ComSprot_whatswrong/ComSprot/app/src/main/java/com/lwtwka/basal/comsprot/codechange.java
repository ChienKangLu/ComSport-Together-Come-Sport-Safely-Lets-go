package com.lwtwka.basal.comsprot;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class codechange extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codechange);
        ////////////////////////
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("更改帳號");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //////////////////////////
        TextView lastcode=(TextView)findViewById(R.id.lastcode);
        userDetail user= new userDetail(this);
        lastcode.setText(""+user.code());
        TextView save=(TextView)findViewById(R.id.save);
        save.setOnClickListener(music1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_codechange, menu);
        return true;
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
    private View.OnClickListener music1 = new View.OnClickListener() {

        public void onClick(View v) {
            TextView s=(TextView)findViewById(R.id.error);
            s.setVisibility(View.INVISIBLE);
            EditText a=(EditText)findViewById(R.id.newcode);
            EditText b=(EditText)findViewById(R.id.doublenewcode);
            if(a.getText().toString().equals(b.getText().toString())){

               // Toast.makeText(v.getContext(), "", Toast.LENGTH_LONG).show();
                userDetail user=new userDetail(codechange.this);
                user.edit("code", a.getText().toString());

                onBackPressed();
            }else{
                s.setVisibility(View.VISIBLE);
            }
        }
    };
}
