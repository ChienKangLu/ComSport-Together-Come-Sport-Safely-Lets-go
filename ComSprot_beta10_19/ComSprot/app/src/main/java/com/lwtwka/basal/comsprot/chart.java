package com.lwtwka.basal.comsprot;


import java.util.ArrayList;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


public class chart extends ActionBarActivity{
    private LineChart mChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
//////////////
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
/////////////////
        mChart = (LineChart) findViewById(R.id.chart);
        mChart.setGridBackgroundColor(Color.TRANSPARENT);

//        mChart.setOnChartGestureListener(this);
//       mChart.setOnChartValueSelectedListener(this);

        // no description text
        mChart.setDescription("");
        mChart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable value highlighting
        mChart.setHighlightEnabled(true);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        // mChart.setScaleXEnabled(true);
        // mChart.setScaleYEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        // mChart.setBackgroundColor(Color.WHITE);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
//        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);

        // set the marker to the chart
//        mChart.setMarkerView(mv);

        // enable/disable highlight indicators (the lines that indicate the
        // highlighted Entry)
        mChart.setHighlightIndicatorEnabled(false);

        // x-axis limit line
//        LimitLine llXAxis = new LimitLine(10f, "Index 10");
//        llXAxis.setLineWidth(4f);
//        llXAxis.enableDashedLine(10f, 10f, 0f);
//        llXAxis.setLabelPosition(LimitLabelPosition.POS_RIGHT);
//        llXAxis.setTextSize(10f);
//
        XAxis xAxis = mChart.getXAxis();
//        xAxis.addLimitLine(llXAxis);
/*
        LimitLine ll1 = new LimitLine(130f, "Upper Limit");
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.POS_RIGHT);
        ll1.setTextSize(10f);

        LimitLine ll2 = new LimitLine(-30f, "Lower Limit");
        ll2.setLineWidth(4f);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.POS_RIGHT);
        ll2.setTextSize(10f);
*/
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
//        leftAxis.addLimitLine(ll1);
//        leftAxis.addLimitLine(ll2);
        leftAxis.setAxisMaxValue(220f);
        leftAxis.setAxisMinValue(-50f);
        leftAxis.setStartAtZero(false);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);


        ////去掉GRID 線
        leftAxis.setDrawGridLines(false);
        xAxis.setDrawGridLines(false);
        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);
        leftAxis.setDrawAxisLine(false);
        xAxis.setDrawAxisLine(false);

        // add data
        setData(20, 200);
//        mChart.setVisibleXRange(20);
//        mChart.setVisibleYRange(20f, AxisDependency.LEFT);
//        mChart.centerViewTo(20, 50, AxisDependency.LEFT);

        mChart.animateX(5000, Easing.EasingOption.EaseInOutQuart);
//        mChart.invalidate();

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(LegendForm.SQUARE);

        // // dont forget to refresh the drawing
        // mChart.invalidate();
    }
    private void setData(int count, float range) {

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add((i) + "");
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();//0 10 25 30 35 35 45 50 55 70 80 90 90 100 150 150  175  180  190 200
        int val[]={20, 25, 25, 30, 35, 35, 45, 50, 55, 70, 80, 90, 90, 100 ,150, 150,  175,  180,  190, 200 };
        for (int i = 0; i < count; i++) {

            yVals.add(new Entry(val[i]+30   , i));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
       // set1.enableDashedLine(10f, 5f, 0f);
        set1.enableDashedLine(10f, 0f, 0f);
        set1.setColor(Color.argb(255, 198, 247, 222));
        set1.setCircleColor(Color.argb(255, 198, 247, 222));
        set1.setLineWidth(5f);
        set1.setCircleSize(10f);//1f
        set1.setDrawCircleHole(true);
        set1.setValueTextSize(0f);
        set1.setFillAlpha(65);
        set1.setDrawFilled(true);
        set1.setFillColor(Color.argb(255,198,247,222));
        //set1.setShader(new LinearGradient(0, 0, 0, mChart.getHeight(),
       // Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));


        /////////////////////////////////

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();

        for (int i = 0; i < 20; i++) {

            yVals2.add(new Entry(val[i]-20, i));
        }
        LineDataSet set2 = new LineDataSet(yVals2, "DataSet 2");
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        // set1.enableDashedLine(10f, 5f, 0f);
        set2.enableDashedLine(10f, 0f, 0f);
        set2.setColor(Color.argb(255, 232, 255, 214));
        set2.setCircleColor(Color.argb(255, 232, 255, 214));
        set2.setLineWidth(5f);
        set2.setCircleSize(10f);//1f
        set2.setDrawCircleHole(true);
        set2.setValueTextSize(0f);
        set2.setFillAlpha(65);
        set2.setDrawFilled(true);
        set2.setFillColor(Color.argb(255, 198, 215, 222));
        /////////////////////////////////

















        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets

        dataSets.add(set2); // add the datasets

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
            default:
                flag=super.onOptionsItemSelected(item);
                break;
        }
        return  flag;
    }


////////////////////////////////////////////////////////////////


/*
    private LineChart mLineChart;
//  private Typeface mTf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        mLineChart = (LineChart) findViewById(R.id.chart);

//      mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Bold.ttf");

        LineData mLineData = getLineData(36, 100);
        showChart(mLineChart, mLineData, Color.rgb(114, 188, 223));
    }

    // ?置?示的?式
    private void showChart(LineChart lineChart, LineData lineData, int color) {
        lineChart.setDrawBorders(false);  //是否在折??上添加?框

        // no description text
        lineChart.setDescription("");// ?据描述
        // 如果?有?据的?候，??示??，?似listview的emtpyview
        lineChart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable / disable grid background
        lineChart.setDrawGridBackground(false); // 是否?示表格?色
        lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的?色，在?里是是??色?置一?透明度

        // enable touch gestures
        lineChart.setTouchEnabled(true); // ?置是否可以触摸

        // enable scaling and dragging
        lineChart.setDragEnabled(true);// 是否可以拖拽
        lineChart.setScaleEnabled(true);// 是否可以?放

        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(false);//

        lineChart.setBackgroundColor(color);// ?置背景

        // add data
        lineChart.setData(lineData); // ?置?据

        // get the legend (only possible after setting data)
        Legend mLegend = lineChart.getLegend(); // ?置比例??示，就是那?一?y的value的

        // modify the legend ...
        // mLegend.setPosition(LegendPosition.LEFT_OF_CHART);
        mLegend.setForm(LegendForm.CIRCLE);// ?式
        mLegend.setFormSize(6f);// 字体
        mLegend.setTextColor(Color.WHITE);// ?色
//      mLegend.setTypeface(mTf);// 字体

        lineChart.animateX(2500); // 立即?行的??,x?
    }

    /**
     * 生成一??据
     * @param count 表示?表中有多少?坐??
     * @param range 用?生成range以?的?机?
     * @return
     */
    /*
    private LineData getLineData(int count, float range) {
        ArrayList<String> xValues = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            // x??示的?据，?里默?使用?字下??示
            xValues.add("" + i);
        }

        // y?的?据
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float value = (float) (Math.random() * range) + 3;
            yValues.add(new Entry(value, i));
        }

        // create a dataset and give it a type
        // y?的?据集合
        LineDataSet lineDataSet = new LineDataSet(yValues, "測試" /*?示在比例?上);
        // mLineDataSet.setFillAlpha(110);
        // mLineDataSet.setFillColor(Color.RED);

        //用y?的集合??置??
        lineDataSet.setLineWidth(1.75f); // ??
        lineDataSet.setCircleSize(3f);// ?示的?形大小
        lineDataSet.setColor(Color.WHITE);// ?示?色
        lineDataSet.setCircleColor(Color.WHITE);// ?形的?色
        lineDataSet.setHighLightColor(Color.WHITE); // 高亮的?的?色

        ArrayList<LineDataSet> lineDataSets = new ArrayList<LineDataSet>();
        lineDataSets.add(lineDataSet); // add the datasets

        // create a data object with the datasets
        LineData lineData = new LineData(xValues, lineDataSets);

        return lineData;
    }


*/

//////////////////////////////////////////////////////////////
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        BarChart chart = (BarChart) findViewById(R.id.chart);

        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.setDescription("My Chart");
        chart.animateXY(2000, 2000);
        chart.invalidate();
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(110.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(40.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(30.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(90.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(100.000f, 5); // Jun
        valueSet1.add(v1e6);

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(150.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(90.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(120.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(60.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(20.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
        valueSet2.add(v2e6);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Brand 2");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("JAN");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");
        return xAxis;
    }
*/
}
