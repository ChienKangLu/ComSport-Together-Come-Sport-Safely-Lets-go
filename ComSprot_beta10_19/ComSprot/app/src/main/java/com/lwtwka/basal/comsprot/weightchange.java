package com.lwtwka.basal.comsprot;

import android.content.res.Resources;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;


public class weightchange extends ActionBarActivity {
    NumberPicker np = null;
    userDetail user=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightchange);
        //////////////
        user=new userDetail(this);
        int height=Integer.parseInt(user.height());
        int meter=height/100;
        int cm=height%100;
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("更改體重");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /////////
        np = (NumberPicker) findViewById(R.id.numberPicker);
        np.setMinValue(1);
        np.setMaxValue(99);
        np.setValue(meter);
        set_numberpicker_text_colour(np);
        setDividerColor(np);
        /////////////////////

        TextView btn =(TextView)findViewById(R.id.save);
        btn.setOnClickListener(music1);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_heightchange, menu);
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
    private void set_numberpicker_text_colour(NumberPicker number_picker) {
        final int count = number_picker.getChildCount();
        final int color_blue = getResources().getColor(R.color.pickercolor);
        for (int i = 0; i < count; i++) {
            View child = number_picker.getChildAt(i);

            try {
                Field wheelpaint_field = number_picker.getClass().getDeclaredField("mSelectorWheelPaint");
                wheelpaint_field.setAccessible(true);

                ((Paint) wheelpaint_field.get(number_picker)).setColor(color_blue);
                ((EditText) child).setTextColor(color_blue);
                number_picker.invalidate();
            } catch (NoSuchFieldException e) {
                Log.w("setNumberPicker", e);
            } catch (IllegalAccessException e) {
                Log.w("setNumber", e);
            } catch (IllegalArgumentException e) {
                Log.w("setNumbe", e);
            }
        }
    }
    private void setDividerColor (NumberPicker picker) {

        java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    //pf.set(picker, getResources().getColor(R.color.my_orange));
                    //Log.v(TAG,"here");
                    pf.set(picker, getResources().getDrawable(R.drawable.divider_picker));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private View.OnClickListener music1 = new View.OnClickListener() {
        public void onClick(View v) {
            String newweight=""+np.getValue();
            userDetail user =new userDetail(weightchange.this);
            user.edit("weight",newweight);
            /*
            * userDetail
            *
            */
           // Toast.makeText(v.getContext(), "" + np.getValue(), Toast.LENGTH_LONG).show();
            onBackPressed();
        }
    };
}
