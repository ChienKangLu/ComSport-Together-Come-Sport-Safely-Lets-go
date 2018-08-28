package com.lwtwka.basal.comsprot.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.lwtwka.basal.comsprot.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 2015/7/26.
 */
public class expandlistadapter2 extends BaseExpandableListAdapter {

    private Context mContext = null;
    // ???�u�A???�i��?��?�u?�A�I?....
    private String[] groups={"�ӤH�ɮ�"};//,"B","C"};
    private String[] A= {"�ӤH���", "�έp���", "���R���","�ӤH���u","���s����" };
    private String[] Aid= {"0", "1", "2","3","4"};
    //  private String[] B = {"1.", "2.", "3." };
    //  private String[] Bid= {"3", "4", "5" };
    //  private String[] C = { "1.", "2.", "3." };
    //  private String[] Cid= {"6", "7", "8" };
    private List<String> groupList = null;
    private List<List<String>> itemList = null;
    private List<List<String>> id = null;

    public expandlistadapter2(Context context) {
        this.mContext = context;
        groupList = new ArrayList<String>();
        itemList = new ArrayList<List<String>>();
        id = new ArrayList<List<String>>();
        initData();
    }

    /**
     * ��l��?�u�A?��??�u���List���A��K?�z
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

        //           List<String> item2 = new ArrayList<String>();
        //           List<String> item2id = new ArrayList<String>();
/*
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
        //        itemList.add(item2);
        //         itemList.add(item3);
        id.add(item1id);
        //         id.add(item2id);
        //         id.add(item3id);

    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    /*
     * ?�m�l???�H�A�b�ƥ�?�z?��^��?�H�A�i�s��@��?�u
     */
    public Object getChild(int groupPosition, int childPosition) {
        return itemList.get(groupPosition).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /*
     * �r????�A?����??�ܤ@?�奻?�H
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
            // ?���l??�n?�ܪ��W?
            String name = (String) itemList.get(groupPosition).get(childPosition);
            // ?�m�奻??����??��
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, 100);//�خؤj�p
            text.setLayoutParams(lp);
            text.setBackgroundResource(R.color.I);
            text.setTextSize(18);
            text.setTextColor(( mContext).getResources().getColor(R.color.B));
            text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            text.setPadding(40, 0, 0, 0);//�Z��
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
     * ��^?�e��?���r????
     */
    public int getChildrenCount(int groupPosition) {
        return itemList.get(groupPosition).size();
    }

    /*
     * ��^��??�H�A�Τ_�@��?�u??�A�b�ƥ�?�z?�i�������o�M��?��?��?�u
     */
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    /*
     * ��?��??
     */
    public int getGroupCount() {
        return groupList.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /*
     * ��???�A?���]�O�@?�奻??
     */
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
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
        text.setPadding(padding_in_px, (int)(8* scale + 0.5f), padding_in_px, (int)(8 * scale + 0.5f));
        text.setText(name);
        return text;
    }

    /*
     * �P?��?�O�_?�šA���ܨҤ�?�u�O�T�w���A�ҥH��??�šA��?��^false
     * �p�G?�u?��?�u?�A�I??�A�i�H��P????��??��k���A�p�G?��
     * ?��^true
     */
    public boolean isEmpty() {
        return false;
    }

    /*
     * ��?�C��?�n?�z��?�賣��?�I
     */
    public void onGroupCollapsed(int groupPosition) {

    }

    /*
     * �i?�C��?�n?�z��?�賣��?�I
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
