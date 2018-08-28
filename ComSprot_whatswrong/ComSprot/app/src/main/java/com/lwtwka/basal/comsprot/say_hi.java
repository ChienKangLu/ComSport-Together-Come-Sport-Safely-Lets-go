package com.lwtwka.basal.comsprot;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.lwtwka.basal.comsprot.activity.MainActivity;
import com.lwtwka.basal.comsprot.animation.ActivityAnimator;

public class say_hi extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_say_hi);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_say_hi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new gogogo().execute();

    }
    class gogogo extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent reit = new Intent();
            reit.setClass(say_hi.this, login.class);
            startActivity(reit);
            try
            {
                ActivityAnimator anim = new ActivityAnimator();
                anim.fadeAnimation(say_hi.this);
            }
            catch (Exception e) { //Toast.makeText(this, "An error occured " + e.toString(), Toast.LENGTH_LONG).show()

            }

          //  Intent i = new Intent(this, SecondActivity.class);
          //  i.putExtra("backAnimation", _animationList[arg2]);
          //  startActivity(i);
            //say_hi.this.finish();
        }
    }
}
