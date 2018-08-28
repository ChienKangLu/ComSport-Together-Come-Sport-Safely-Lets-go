package com.lwtwka.basal.comsprot;

import android.app.DialogFragment;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lwtwka.basal.comsprot.adapter.MyGridView;
import com.lwtwka.basal.comsprot.adapter.TagsAdapter;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.dialog.analystdialog;

import java.util.ArrayList;


public class try_grid_checkbox extends ActionBarActivity {

    MyGridView gv;
    ArrayList<String> checkdata=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_grid_checkbox);
        //////////////
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
/////////////////

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_try_grid_checkbox, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.box) {
            DialogFragment dialog = new analystdialog();
            Bundle args = new Bundle();
            args.putStringArrayList("data",checkdata);
            dialog.setArguments(args);
            dialog.show(getFragmentManager(), "tag");
            /*
            myDB mydb=new myDB(this);
            userDetail user=new userDetail(this);
            mydb.Connect();
            mydb.insertanalyt(user.id(),"神一般的分析");
            int analystid=mydb.getlastanalyt();
            Log.v("LATEST",""+analystid);
            //ArrayList<String> checkdata=new ArrayList<>();
            String insertsql="";
            for(int i=0;i<checkdata.size();i++) {
                if(i!=0){
                    insertsql+=",";
                }
                insertsql+="("+analystid+","+checkdata.get(i)+")";
            }
            Log.v("insertsql",""+insertsql);
            mydb.insertanalytdetail(insertsql);
            onBackPressed();
            */
        }
        if(id == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gv=(MyGridView)findViewById(R.id.gv);
        TagsAdapter mAdapter = new TagsAdapter(this, onItemClickClass);
        WindowManager wm = (WindowManager) try_grid_checkbox.this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

			/*根據螢幕大小改變數量*/
        int gridViewEntrySize = dip2px(this,165);//64
        int gridViewSpacing = dip2px(this, 10);//10
        int numColumns = (display.getWidth() - gridViewSpacing) / (gridViewEntrySize + gridViewSpacing);
        gv.setNumColumns(numColumns);

        gv.setAdapter(mAdapter);
    }
    /** 实现接口，点击选中或者取消选中，并获取其被选中的集合 */
    TagsAdapter.OnItemClickClass onItemClickClass = new TagsAdapter.OnItemClickClass() {
        @Override
        public void OnItemClick(View v, int position, CheckBox checkBox, TextView textView) {
                TextView idt=(TextView)((RelativeLayout)v).getChildAt(0);
            ImageView imcheck=(ImageView)v.findViewById(R.id.imcheck);
                //   ImageView im1=(ImageView)((RelativeLayout)v).findViewById(R.id.im);
                //  im1.setVisibility(View.VISIBLE);
                if (checkBox.isChecked()) {
                    checkBox.setChecked(false);
                    imcheck.setVisibility(View.INVISIBLE);
                    // textView.setBackgroundColor(try_grid_checkbox.this.getResources().getColor(R.color.white));
                    //textView.setBackgroundResource(R.drawable.tags_not_select_shape);
                    // TextView key=(TextView)((RelativeLayout)view).getChildAt(0);
                    // ((RelativeLayout)v).setBackgroundResource(R.drawable.tags_not_select_shape);
                    //textView.setTextColor(try_grid_checkbox.this.getResources().getColor(R.color.B));

                    for (int i = 0; i < checkdata.size(); i++) {
                        if (idt.getText().toString().equals(checkdata.get(i))) {
                            checkdata.remove(i);
                        }
                    }

                } else {

                    if(checkdata.size()<3) {
                        checkBox.setChecked(true);
                        imcheck.setVisibility(View.VISIBLE);
                        //textView.setBackgroundColor(try_grid_checkbox.this.getResources().getColor(R.color.gplus_color_2));
                        // textView.setBackgroundResource(R.drawable.tags_select_shape);
                        //   ((RelativeLayout)v).setBackgroundResource(R.drawable.tags_select_shape);
                        //   textView.setTextColor(try_grid_checkbox.this.getResources().getColor(R.color.white));

                        String id = idt.getText().toString();
                        checkdata.add(id);
                    }
                }
                String aa = "";
                for (int i = 0; i < checkdata.size(); i++) {
                    if (i != 0) {
                        aa += ",";
                    }
                    aa += checkdata.get(i);
                }
                Toast.makeText(v.getContext(), aa, Toast.LENGTH_LONG).show();
                Log.v("datatest", aa);

        }
    };
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
