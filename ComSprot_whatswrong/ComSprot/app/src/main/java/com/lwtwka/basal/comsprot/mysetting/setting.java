package com.lwtwka.basal.comsprot.mysetting;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.adapter.analyst_adapter;
import com.lwtwka.basal.comsprot.analyst_chart2;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.myui.SegmentedRadioGroup;
import com.lwtwka.basal.comsprot.try_grid_checkbox;
import com.lwtwka.basal.comsprot.userDetail;
import com.zcw.togglebutton.ToggleButton;
//import com.zcw.togglebutton.ToggleButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class setting extends Fragment {


    public setting() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.setting, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        ToggleButton btn2=(ToggleButton)getActivity().findViewById(R.id.btn1);
        ToggleButton btn=(ToggleButton)getActivity().findViewById(R.id.btn2);
        myDB mydb=new myDB(getActivity());
        mydb.Connect();
        final userDetail user=new userDetail(getActivity());
        String online=mydb.getuseronline(user.id());
      //  mydb.updateonline(0, user.id());
        mydb.closedb();
        if(online.equals("0")){
            btn.setToggleOff();
        }else{
            btn.setToggleOn();
        }
        if(user.mode().equals("0")) {
            btn2.setToggleOff();
        }else {
            btn2.setToggleOn();
        }


        SegmentedRadioGroup radiobut=(SegmentedRadioGroup)getActivity().findViewById(R.id.segment_text);
        if(user.gps().equals("0")) {
            radiobut.check(R.id.button_one);
        }else if(user.gps().equals("1")){
            radiobut.check(R.id.button_two);
        }else if(user.gps().equals("2")){
            radiobut.check(R.id.button_three);
        }

        radiobut.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.button_one) {
                    user.edit("gps", "0");
                    Toast.makeText(getActivity(),"gps精準度調為低",Toast.LENGTH_LONG).show();
                } else if (checkedId == R.id.button_two) {
                    user.edit("gps","1");
                    Toast.makeText(getActivity(),"gps精準度調為中",Toast.LENGTH_LONG).show();
                } else if (checkedId == R.id.button_three) {
                    user.edit("gps","2");
                    Toast.makeText(getActivity(),"gps精準度調為高",Toast.LENGTH_LONG).show();
                }
            }
        });

        btn.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                if (on) {
                    userDetail.changeonline = 0;
                    Toast.makeText(getActivity(), "顯示上線", Toast.LENGTH_LONG).show();
                    myDB mydb = new myDB(getActivity());
                    mydb.Connect();
                    userDetail user = new userDetail(getActivity());
                    mydb.updateonline(1, user.id());
                    mydb.closedb();
                } else {
                    userDetail.changeonline = 1;//使用者希望關閉~所以就~讓她永遠關閉
                    Toast.makeText(getActivity(), "顯示下線", Toast.LENGTH_LONG).show();
                    myDB mydb = new myDB(getActivity());
                    mydb.Connect();
                    userDetail user = new userDetail(getActivity());
                    mydb.updateonline(0, user.id());
                    mydb.closedb();
                }
            }
        });
        btn2.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                if (on) {//打開
                    user.edit("mode","1");
                } else {//關閉
                    user.edit("mode","0");
                }
            }
        });

        /*
        MyListView L=(MyListView)getActivity().findViewById(R.id.list1);

        analyst_adapter adapter=new analyst_adapter(getActivity(),10);
        L.setAdapter(adapter);

        L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView key=(TextView)((RelativeLayout)view).getChildAt(0);
                TextView title=(TextView)((RelativeLayout)view).getChildAt(1);
                TextView date=(TextView)((RelativeLayout)view).getChildAt(2);
                TextView dis=(TextView)((RelativeLayout)view).getChildAt(3);
                Intent intent = new Intent();

                Bundle bundle = new Bundle();
                bundle.putString("id", key.getText().toString());
                bundle.putString("title", title.getText().toString());
                bundle.putString("date", date.getText().toString());
                bundle.putString("dis", dis.getText().toString());
                //�NBundle����assign��intent
                intent.putExtras(bundle);

                intent.setClass(getActivity(), analyst_chart2.class);
                startActivity(intent);
                // TODO Auto-generated method stub
                // TextView title = (TextView) ((RelativeLayout) view).getChildAt(0);


            }
        });
        */
    }

}
