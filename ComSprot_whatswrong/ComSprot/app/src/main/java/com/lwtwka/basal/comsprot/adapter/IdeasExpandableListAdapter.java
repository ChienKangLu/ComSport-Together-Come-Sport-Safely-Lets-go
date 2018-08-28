package com.lwtwka.basal.comsprot.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.lwtwka.basal.comsprot.R;

public class IdeasExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext = null;
    // 测试数据，开发时可能来自数据库，网络....
    int titlecount=6;
    private String[] groups={"個人檔案","路線"};//,"B","C"};
    private String[] A= {"個人資料", "統計資料", "分析資料","個人路線","社群分享" };
    private String[] Aid= {(titlecount+0)+"",(titlecount+1)+"",(titlecount+2)+"",(titlecount+3)+"",(titlecount+4)+""};
    private String[] B= {"參考路線", "路線影片"};
    private String[] Bid= {(titlecount+5)+"", (titlecount+6)+""};
  //  private String[] B = {"1.", "2.", "3." };
  //  private String[] Bid= {"3", "4", "5" };
  //  private String[] C = { "1.", "2.", "3." };
  //  private String[] Cid= {"6", "7", "8" };
    private List<String> groupList = null;
    private List<List<String>> itemList = null;
    private List<List<String>> id = null;

    public IdeasExpandableListAdapter(Context context) {
            this.mContext = context;
            groupList = new ArrayList<String>();
            itemList = new ArrayList<List<String>>();
            id = new ArrayList<List<String>>();
            initData();
    }

    /**
     * 初始化数据，将相关数据放到List中，方便处理
     */
    private void initData() {


            for (int i = 0; i < groups.length; i++) {
                    groupList.add(groups[i]);
            }

            List<String> item1 = new ArrayList<String>();
            List<String> item1id = new ArrayList<String>();
            for (int i = 0; i < A.length; i++) {
                    item1.add(A[i]);
                    item1id.add(Aid[i]);
            }

            List<String> item2 = new ArrayList<String>();
            List<String> item2id = new ArrayList<String>();

            for (int i = 0; i < B.length; i++) {
                    item2.add(B[i]);
                    item2id.add(Bid[i]);
            }
/*
            List<String> item3 = new ArrayList<String>();
            List<String> item3id = new ArrayList<String>();
            for (int i = 0; i < C.length; i++) {
                    item3.add(C[i]);
                    item3id.add(Cid[i]);
            }
*/


            itemList.add(item1);
            itemList.add(item2);
   //         itemList.add(item3);
            id.add(item1id);
            id.add(item2id);
   //         id.add(item3id);
            
    }

    public boolean areAllItemsEnabled() {
            return false;
    }

    /*
     * 设置子节点对象，在事件处理时返回的对象，可存放一些数据
     */
    public Object getChild(int groupPosition, int childPosition) {
            return itemList.get(groupPosition).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
    }

    /*
     * 字节点视图，这里我们显示一个文本对象
     */
    public View getChildView(int groupPosition, int childPosition,
                    boolean isLastChild, View convertView, ViewGroup parent) {
    	/*
            TextView text = null;
            if (convertView == null) {
                    text = new TextView(mContext);
            } else {
                    text = (TextView) convertView;
            }
            // 获取子节点要显示的名称
            String name = (String) itemList.get(groupPosition).get(childPosition);
            // 设置文本视图的相关属性
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, 100);//框框大小
            text.setLayoutParams(lp);
            text.setBackgroundResource(R.color.I);
            text.setTextSize(18);
            text.setTextColor(( mContext).getResources().getColor(R.color.B));
            text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            text.setPadding(40, 0, 0, 0);//距離
            text.setText(name);
            */
            View row = convertView;
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.expandlist, parent, false);
            row.setBackgroundResource(R.color.I);
            TextView T1 = (TextView) row.findViewById(R.id.textView1);
            TextView T2 = (TextView) row.findViewById(R.id.textView2);
            String name = (String) itemList.get(groupPosition).get(childPosition);
            String ids = (String) id.get(groupPosition).get(childPosition);
            T1.setText(name);
            T2.setText(ids);
            
            return row;
          //  return text;
    }

    /*
     * 返回当前分组的字节点个数
     */
    public int getChildrenCount(int groupPosition) {
            return itemList.get(groupPosition).size();
    }

    /*
     * 返回分组对象，用于一些数据传递，在事件处理时可直接取得和分组相关的数据
     */
    public Object getGroup(int groupPosition) {
            return groupList.get(groupPosition);
    }

    /*
     * 分组的个数
     */
    public int getGroupCount() {
            return groupList.size();
    }

    public long getGroupId(int groupPosition) {
            return groupPosition;
    }

    /*
     * 分组视图，这里也是一个文本视图
     */
    public View getGroupView(int groupPosition, boolean isExpanded,
                    View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        row = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        TextView T1 = (TextView) row.findViewById(R.id.title);
        String name = (String) groupList.get(groupPosition);
        T1.setText(name);
        /*
            TextView text = null;
            if (convertView == null) {
                    text = new TextView(mContext);
            } else {
                    text = (TextView) convertView;
            }
            String name = (String) groupList.get(groupPosition);
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                            ViewGroup.LayoutParams.FILL_PARENT, 100);
            text.setLayoutParams(lp);
            text.setTextSize(18);
            text.setTextColor(( mContext).getResources().getColor(R.color.B));
            text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            
            int padding_in_dp = 30;  // 6 dps
            final float scale = mContext.getResources().getDisplayMetrics().density;
            int padding_in_px = (int) (padding_in_dp * scale + 0.5f);

            text.setPadding(padding_in_px,0, padding_in_px,0);//(int)(8* scale + 0.5f)
            text.setText(name);
            */
          //  return text;
        return row;
    }

    /*
     * 判断分组是否为空，本示例中数据是固定的，所以不会为空，我们返回false 
     * 如果数据来自数据库，网络时，可以把判断逻辑写到这个方法中，如果为空
     * 时返回true
     */
    public boolean isEmpty() {
            return false;
    }

    /*
     * 收缩列表时要处理的东西都放这儿
     */
    public void onGroupCollapsed(int groupPosition) {

    }

    /*
     * 展开列表时要处理的东西都放这儿
     */
    public void onGroupExpanded(int groupPosition) {

    }

    /*
     * Indicates whether the child and group IDs are stable across changes to
     * the underlying data.
     */
    public boolean hasStableIds() {
            return false;
    }

    /*
     * Whether the child at the specified position is selectable.
     */
    public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
    }

}
