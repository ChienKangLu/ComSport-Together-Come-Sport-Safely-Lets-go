package com.lwtwka.basal.comsprot.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.lwtwka.basal.comsprot.R;

import java.util.Map;

public class listitem extends ArrayAdapter {
	Context context;
	int resource;
	public YouTubeThumbnailLoader youTubeThumbnailLoader[];
	String words;
    String videosid[]={"HdjVg4qJPXk","f4wrO1P-lx4","Ltz_5Q99eis","GkmfGV8IiLg","Ih2u3ChhQfM","od9H5xmjZk8"};
    String videosname[]={"推薦新北市汐止最美單車路線~","環海岸山脈390KM《台灣 ‧ 用騎的最美》","1010908 鐵馬家族與展翼合唱團 環台北71k紀實","特富野古道單車路線"
                        ,"2015 6 22特富野古道","20110614拉拉山福巴越嶺古道福山自行車之旅"};
	String name[]={"陸建綱","王慧縈","柯浩元","蔡穎珊","吳宛穎"};


	public static final String API_KEY = "AIzaSyA5Prb6dLjI0YcfMQhZuGT5-_Kt68ZgDiQ";
	public listitem(Context context, int resource) {
		super(context, resource);
		this.context = context;
		this.resource = resource;
        youTubeThumbnailLoader=new YouTubeThumbnailLoader[videosid.length];
				

		// TODO Auto-generated constructor stub
	}

	public int getCount() {

		if(resource==10)
		return resource;
		else 
			return videosid.length;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View row = inflater.inflate(R.layout.listfont, parent, false);//youtubethumbnailview
        TextView title=(TextView)row.findViewById(R.id.videostitle);
        title.setText(videosname[position]);
        TextView id=(TextView)row.findViewById(R.id.vid);
        id.setText(videosid[position]);
		TextView videospeople=(TextView)row.findViewById(R.id.videospeople);
		videospeople.setText(name[position%5]);
		final String videoId = "nrNTKy0L8jk";
		YouTubeThumbnailView youTubeThumbnailView = (YouTubeThumbnailView)row.findViewById(R.id.youtubethumbnailview);

        youTubeThumbnailView.initialize(API_KEY, new YouTubeThumbnailView.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader thumbnailLoader) {
                youTubeThumbnailLoader[position] = thumbnailLoader;
              //  thumbnailLoader.setOnThumbnailLoadedListener(new ThumbnailLoadedListener());
                youTubeThumbnailLoader[position].setVideo(videosid[position]);
                Log.v("哈哈哈哈","youTubeThumbnailLoader[position].setVideo(\"nrNTKy0L8jk\");");

        }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //youTubeThumbnailLoader.release();

                Log.v("哈哈哈哈2","nInitializationFailure");
                youTubeThumbnailView.setImageResource(R.drawable.ic_launcher);
            }

//override methods here..

        });


		//TextView textViewTitle = (TextView) row.findViewById(R.id.textView);
		//TextView textViewTitle1 = (TextView) row.findViewById(R.id.textView1);



		//LinearLayout ll = (LinearLayout)((Activity) context).findViewById(R.id.frame);
		//TextView tt=(TextView) row.findViewById(R.id.textView);
		//ll.removeAllViews();
		//tt.setText("gg");
		//ll.addView(tt);
		//tt.setText("hahahaha");
		//ll.addView(tt);
		
		
		//String title=(position+1)+". e";
		
		
		/*CharSequence aa=Html.fromHtml("<font color=\"#ff0000\">e</font>");*/
		//String example="‧  egg[ɛg] (n.) 蛋";
		//textViewTitle.setText(title);
		//textViewTitle1.setText(example+"\n"+example+"\n"+example);


		//textViewTitle1.setText(""+position);
		return row;
	}
	/*
	@Override
	public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader thumbnailLoader) {
		String videoId = (String) youTubeThumbnailView.getTag();
		Toast.makeText(context,""+videoId, Toast.LENGTH_LONG).show();
		youTubeThumbnailLoader=thumbnailLoader;
		thumbnailLoader.setOnThumbnailLoadedListener(new ThumbnailLoadedListener());
		youTubeThumbnailLoader.setVideo("nrNTKy0L8jk");
	}

	@Override
	public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

	}
	*/

	private final class ThumbnailLoadedListener implements
			YouTubeThumbnailLoader.OnThumbnailLoadedListener {

		@Override
		public void onThumbnailError(YouTubeThumbnailView arg0, YouTubeThumbnailLoader.ErrorReason arg1) {

		}

		@Override
		public void onThumbnailLoaded(YouTubeThumbnailView arg0, String arg1) {

           // youTubeThumbnailLoader.release();
		}


	}

}
