package com.lwtwka.basal.comsprot;


import java.util.ArrayList;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.lwtwka.basal.comsprot.database.myDB;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/*接收到analyistid*/
public class analyst_chart2 extends ActionBarActivity{
   // private LineChart mChart;
    String id=null;
    String title = null;
    String datet = null;
    String dist = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyst_chart2);
//////////////
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
/////////////////
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
            title = bundle.getString("title");
            datet = bundle.getString("date");
            dist = bundle.getString("dis");
            Toast.makeText(this,""+id,Toast.LENGTH_LONG).show();
        }
        TextView roadname=(TextView)findViewById(R.id.roadname);
        TextView date=(TextView)findViewById(R.id.date);
        TextView dis=(TextView)findViewById(R.id.dis);
        roadname.setText(title);
        date.setText(datet);
        dis.setText(dist);


        LineChart mChart = (LineChart) findViewById(R.id.chart);
        myDB mydb=new myDB(this);
        mydb.Connect();
        show(mydb,mChart,"alt");
        /////////////////////////////////////////////
        LineChart mChart2 = (LineChart) findViewById(R.id.chart2);
        show(mydb, mChart2, "speed");
        mydb.closedb();
    }


    ArrayList<String> xnumber(int count){
        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add((i) + "");
        }
        return xVals;
    }
    LineDataSet putdata(int data[],int count,String title,int r,int g,int b){
        ArrayList<Entry> datalist = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            datalist.add(new Entry(data[i], i));
        }
        LineDataSet set = new LineDataSet(datalist, title);
        set.enableDashedLine(10f, 0f, 0f);
        set.setColor(Color.argb(255, r, g, b));
        set.setCircleColor(Color.argb(255, r, g, b));
        set.setLineWidth(2f);//5f
        set.setCircleSize(0f);//1f
        set.setDrawCircleHole(false);
        set.setValueTextSize(0f);
        return set;
    }
    private void setData(LineChart mChart,int xn,int count,int val[][],String[] title,int[]everylength) {
        ArrayList<String> xVals=xnumber(xn);
        LineDataSet []set=new LineDataSet[count];
        int [][]color=new int[4][3];
        /*加顏色在這裡*///getResources().getColor( R.color.B //預備10種顏色!!記得!!!重要!!!不然會error
        color[0][0]=198;
        color[0][1]=247;
        color[0][2]=222;
        //////////////////////
        color[1][0]=128;
        color[1][1]=162;
        color[1][2]=205;
        //////////////////////
        color[2][0]=255;
        color[2][1]=0;
        color[2][2]=0;
        //////////////////////
        color[3][0]=0;
        color[3][1]=255;
        color[3][2]=0;
        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        for(int i=0;i<count;i++){
            set[i]=putdata(val[i],everylength[i],title[i],color[i][0], color[i][1], color[i][2]);
            dataSets.add(set[i]);
        }
        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);
        // set data
        mChart.setData(data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean flag =false;
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                flag=true;
                break;

            case R.id.trash:
                myDB mydb =new myDB(this);
                mydb.Connect();
                mydb.deleteanalyst(id);
                mydb.closedb();
                flag=true;
                onBackPressed();
                break;

            default:
                flag=super.onOptionsItemSelected(item);
                break;
        }
        return  flag;
    }
    public void initalchart(LineChart mChart){
        mChart.setGridBackgroundColor(Color.TRANSPARENT);
        mChart.setDescription("");
        mChart.setNoDataTextDescription("You need to provide data for the chart.");
        mChart.setHighlightEnabled(true);
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setPinchZoom(true);
        mChart.setHighlightIndicatorEnabled(false);
        XAxis xAxis = mChart.getXAxis();
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
      //  leftAxis.setAxisMaxValue(220f);//上限
      //  leftAxis.setAxisMinValue(-50f);//下限
        leftAxis.setStartAtZero(false);
        leftAxis.setDrawGridLines(true);
        xAxis.setDrawGridLines(true);
        leftAxis.setDrawLimitLinesBehindData(true);
        mChart.getAxisRight().setEnabled(true);
        mChart.getAxisRight().setDrawGridLines(false);
        mChart.getAxisRight().setDrawLabels(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }
    public void show(myDB mydb,LineChart mChart,String style){
        initalchart(mChart);
        int roadnumber=Integer.parseInt(mydb.getroadnumber(id));//有幾條路來筆較
        ArrayList<String> bikerecordid=mydb.analystrecordid(id);//傳進來的id
        ArrayList< ArrayList<String>> totalpoint=new ArrayList<>();
        for(int i=0;i<roadnumber;i++){
            totalpoint.add(mydb.selectpoint(Integer.parseInt(bikerecordid.get(i)),style));
        }
        int everylength[]=new int [totalpoint.size()];
        for(int i=0;i<everylength.length;i++){
            everylength[i]=totalpoint.get(i).size();
        }
        int valf[][]=new int[totalpoint.size()][];
        for(int i=0;i<totalpoint.size();i++){
            int change[]=new int[everylength[i]];
            for(int j=0;j<everylength[i];j++){
                change[j]=(int)(Double.parseDouble(totalpoint.get(i).get(j)));
            }
            valf[i]=change;
        }
        String date[]=new String[totalpoint.size()];
        for(int i=0;i<date.length;i++){
            date[i]="date"+(i+1);
        }
        int maxcount=0;
        for(int i=0;i<totalpoint.size();i++){
            if(totalpoint.get(i).size()>=maxcount){
                maxcount=totalpoint.get(i).size();
            }
        }
        setData(mChart,maxcount,totalpoint.size(),valf,date,everylength);//2條線~20筆資料

        mChart.animateX(5000, Easing.EasingOption.EaseInOutQuart);
        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        l.setForm(LegendForm.SQUARE);
    }


            /*
        取90點
        a路線100個點
        a:100/90=1.11111
        b路線100個點
        b:200/90=2.2
        0,2,4,6,8.....200
        除法算取樣的點
        用count計算要幾個點，count完成就break
        */
}
