package com.lwtwka.basal.comsprot.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.urlimageviewhelper.UrlImageViewCallback;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.mayAsyncTask.DownloadImageTask;
import com.lwtwka.basal.comsprot.userDetail;

/**
 * 选择标签的适配器
 */
@SuppressLint("InflateParams")
public class TagsAdapter extends BaseAdapter {

    private Context context;
    private TagHolder mHolder = null;
    private int clickTemp = -1;
    OnItemClickClass onItemClickClass;
    //String data[];
    ArrayList<ArrayList<String>> truedata=null;
    public TagsAdapter(Context context, OnItemClickClass onItemClickClass) {
        this.context = context;
        this.onItemClickClass = onItemClickClass;
        myDB mydb=new myDB(context);
        mydb.Connect();
        userDetail user=new userDetail(context);
        truedata=mydb.selectallbikerecordnewtitle(user.id());

        /*
        data=new String[200];
        for(int i=0;i<data.length;i++){
            data[i]="BASAL"+(i+1);
        }
        */
    }

    public void setSelection(int choiceCount) {
        this.clickTemp = choiceCount;
    }

    @Override
    public int getCount() {
        return truedata.size();
    }

    @Override
    public Object getItem(int position) {
        return truedata.get(position).get(6);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.tags_item, null);
            mHolder = new TagHolder();
            mHolder.textView = (TextView) convertView.findViewById(R.id.tv_tags_item);
            mHolder.checkBox = (CheckBox) convertView.findViewById(R.id.tags_checkbox);
            convertView.setTag(mHolder);
        } else {
            mHolder = (TagHolder) convertView.getTag();
        }
        TextView id=(TextView)convertView.findViewById(R.id.id);
        id.setText(truedata.get(position).get(4));
        /////////////////////

        String name="image"+truedata.get(position).get(7);
        ImageView im=(ImageView)convertView.findViewById(R.id.im);
        UrlImageViewHelper.setUrlDrawable(im,"http://163.14.68.47/map/"+name+".png", R.drawable.map_loading1, new UrlImageViewCallback() {
            @Override
            public void onLoaded(ImageView imageView, Bitmap loadedBitmap, String url, boolean loadedFromCache) {
                if (!loadedFromCache) {
                    ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
                    scale.setDuration(300);
                    scale.setInterpolator(new OvershootInterpolator());
                    imageView.startAnimation(scale);
                }
            }
        });
        //new DownloadImageTask(im)
          //      .execute("http://163.14.68.47/map/"+name+".png");
        ////////////////////


        mHolder.textView.setText(truedata.get(position).get(6));//0
        mHolder.checkBox.setVisibility(View.GONE);




        //设置被选中和取消选中条目的状态
        if (clickTemp == position) {
            mHolder.textView.setSelected(true);
        } else {
            mHolder.textView.setSelected(false);
        }
        convertView.setOnClickListener(new OnTextClick(position, mHolder.checkBox, mHolder.textView));
        return convertView;
    }

    static class TagHolder {
        TextView textView;
        CheckBox checkBox;
    }

    /** 点击多选的接口 */
    public interface OnItemClickClass {
        public void OnItemClick(View v, int Position, CheckBox checkBox, TextView textView);
    }

    /** 多选的接口实现类 */
    class OnTextClick implements OnClickListener {

        int position;
        CheckBox checkBox;
        TextView tvSelected = null;
        TextView textView = null;

        public OnTextClick(int position, CheckBox checkBox) {
            this.position = position;
            this.checkBox = checkBox;
        }

        public OnTextClick(int position, CheckBox checkBox, TextView textView) {
            this.position = position;
            this.checkBox = checkBox;
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            if (truedata != null && onItemClickClass != null) {
                onItemClickClass.OnItemClick(v, position, checkBox, textView);
            }
        }
    }
}