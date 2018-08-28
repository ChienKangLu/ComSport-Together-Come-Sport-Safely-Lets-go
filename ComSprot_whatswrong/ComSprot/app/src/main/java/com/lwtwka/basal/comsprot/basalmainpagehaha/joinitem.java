package com.lwtwka.basal.comsprot.basalmainpagehaha;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.koushikdutta.urlimageviewhelper.UrlImageViewCallback;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.joint_sentence;
import com.lwtwka.basal.comsprot.userDetail;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class joinitem extends ArrayAdapter {
	Context context;
	int resource;
    ArrayList<Integer> color;
    ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
    int colordata[]={R.color.j1,R.color.j2,R.color.j3,R.color.j4,R.color.j5,R.color.j6,R.color.j7};
	public joinitem(Context context, int resource) {
		super(context, resource);
		this.context = context;
		this.resource = resource;
        color=new ArrayList<Integer>();
        for(int i=0;i<colordata.length;i++){
            color.add(colordata[i]);
        }
        myDB mydb=new myDB(context);
        mydb.Connect();
        userDetail user=new userDetail(context);

        data=mydb.selectjointbike("" + user.id());
        mydb.closedb();


		// TODO Auto-generated constructor stub
	}

	public int getCount() {
		return data.size();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View row =null;


            row = inflater.inflate(R.layout.activity_swipe, parent, false);//jointext
            TextView tx=(TextView)row.findViewById(R.id.text);//句子
        tx.setText(""+data.get(position).get(4));
        TextView sentid=(TextView)row.findViewById(R.id.sentid);//發送者
        sentid.setText(""+ data.get(position).get(0));
        TextView receiveid=(TextView)row.findViewById(R.id.receiveid);//接收者(自己)
        receiveid.setText(""+ data.get(position).get(1));

        TextView id=(TextView)row.findViewById(R.id.id);//接收者(自己)

        id.setText(""+ data.get(position).get(6));

        TextView dateshow=(TextView)row.findViewById(R.id.dateshow);//時間
        dateshow.setText(""+ data.get(position).get(5));

        CircleImageView profile_image=(CircleImageView)row.findViewById(R.id.profile_image);//大頭照
        UrlImageViewHelper.setUrlDrawable(profile_image, "http://163.14.68.47/map/" + "image" + data.get(position).get(2) + ".png", R.drawable.circlepichaha, new UrlImageViewCallback() {
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


            tx.setBackgroundColor(context.getResources().getColor(color.get(position%color.size())));
            ///////
            SwipeLayout sample2 = (SwipeLayout) row.findViewById(R.id.haha);
            sample2.setShowMode(SwipeLayout.ShowMode.LayDown);
            sample2.addDrag(SwipeLayout.DragEdge.Right, sample2.findViewWithTag("Bottom2"));
//        sample2.setShowMode(SwipeLayout.ShowMode.PullOut);
        final View finalRow1 = row;
        sample2.findViewById(R.id.star).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                            Intent intent = new Intent();
                            intent.setClass(context, joint_sentence.class);
                            Bundle bundle = new Bundle();
                            TextView sentid=(TextView) finalRow1.findViewById(R.id.sentid);//發送者
                            bundle.putString("id", sentid.getText().toString());
                             intent.putExtras(bundle);
                            context.startActivity(intent);

                }
            });
/*
        sample2.findViewById(R.id.trash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Trash Bin", Toast.LENGTH_SHORT).show();
            }
        });
*/
        final View finalRow = row;
        sample2.findViewById(R.id.magnifier).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(context, "Magnifier", Toast.LENGTH_SHORT).show();
                    //color.remove(position);
                    myDB mydb=new myDB(context);
                    mydb.Connect();
                    userDetail user=new userDetail(context);
                    TextView id=(TextView) finalRow.findViewById(R.id.id);//接收者(自己)
                    mydb.deletjoinbike(""+id.getText());
                    mydb.closedb();
                    data.remove(position);
                    notifyDataSetChanged();
                }
            });

            sample2.findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(context, "Yo", Toast.LENGTH_SHORT).show();
                }
            });
            sample2.getSurfaceView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(context, "Click on surface", Toast.LENGTH_SHORT).show();
                }
            });

		return row;
	}

}
