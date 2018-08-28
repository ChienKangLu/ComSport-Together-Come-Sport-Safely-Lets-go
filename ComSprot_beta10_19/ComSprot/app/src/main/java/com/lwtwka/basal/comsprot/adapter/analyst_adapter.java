package com.lwtwka.basal.comsprot.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.userDetail;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class analyst_adapter extends ArrayAdapter {
	Context context;
	int resource;
	myDB mydb;
	ArrayList<ArrayList<String>> analyst=null;
	ArrayList<ArrayList<ArrayList<String>>>  analystdetail=null;
	public analyst_adapter(Context context, int resource) {
		super(context, resource);
		this.context = context;
		this.resource = resource;
		mydb=new myDB(context);
		mydb.Connect();
		userDetail user=new userDetail(context);
		analyst=mydb.selectanalyst(""+user.id());

		analystdetail=new ArrayList<ArrayList<ArrayList<String>>>();
		for(int i=0;i<analyst.size();i++){
			analystdetail.add(mydb.selectanalystdetail(analyst.get(i).get(0)));
		}

		mydb.closedb();
		Toast.makeText(context,""+analyst.size(),Toast.LENGTH_LONG).show();
		// TODO Auto-generated constructor stub
	}

	public int getCount() {
		return analyst.size();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View row = inflater.inflate(R.layout.analyst_item, parent, false);//youtubethumbnailview

		TextView roadname=(TextView)row.findViewById(R.id.roadname);
		TextView date=(TextView)row.findViewById(R.id.date);
		TextView dis=(TextView)row.findViewById(R.id.dis);
		TextView id=(TextView)row.findViewById(R.id.id);
		roadname.setText(analyst.get(position).get(1));
		id.setText(analyst.get(position).get(0));

		String datestring="";
		String disstring="";
		for(int i=0;i<analystdetail.get(position).size();i++){
			if(i!=0){
				datestring+=",";
				disstring+=",";
			}
			datestring+=analystdetail.get(position).get(i).get(0);
			disstring+=analystdetail.get(position).get(i).get(1);
		}
		date.setText(datestring);
		dis.setText(disstring);



		//先拿出介面id

		//ImageView fav=(ImageView)row.findViewById(R.id.favorite);
		//fav.setOnClickListener(music1);


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
	private View.OnClickListener music1 = new View.OnClickListener() {
		public void onClick(View v) {
			Toast.makeText(context, "click star~good",
					Toast.LENGTH_LONG).show();


		}
	};

}
