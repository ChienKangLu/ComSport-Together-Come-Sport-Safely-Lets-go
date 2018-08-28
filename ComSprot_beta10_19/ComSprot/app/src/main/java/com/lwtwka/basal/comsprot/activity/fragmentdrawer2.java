package com.lwtwka.basal.comsprot.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.adapter.IdeasExpandableListAdapter;


public class  fragmentdrawer2 extends Fragment {
    View view;
    public  fragmentdrawer2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragmentdrawer2, null);

        ExpandableListView L=(ExpandableListView)view.findViewById(R.id.listc2);
        IdeasExpandableListAdapter adapter=new IdeasExpandableListAdapter(getActivity());
        L.setGroupIndicator(null);
        L.setAdapter(adapter);
        L.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //Nothing here ever fires

                //System.err.println("child clicked");
                //Toast.makeText(getActivity(), "child clicked"+childPosition, Toast.LENGTH_SHORT).show();




                return true;
            }
        });



        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_fragmentdrawer2, container, false);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}



/*
        view = inflater.inflate(R.layout.fragment_fragmentdrawer2, null);
        ExpandableListView L=(ExpandableListView)view.findViewById(R.id.listc2);
        IdeasExpandableListAdapter adapter=new IdeasExpandableListAdapter(getActivity());
        L.setGroupIndicator(null);
        L.setAdapter(adapter);
        L.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //Nothing here ever fires

                //System.err.println("child clicked");
                //Toast.makeText(getActivity(), "child clicked"+childPosition, Toast.LENGTH_SHORT).show();




                return true;
            }
        });



        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_fragmentdrawer2, container, false);
        return view;
 */