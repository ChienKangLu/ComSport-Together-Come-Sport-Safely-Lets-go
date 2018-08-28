package com.lwtwka.basal.comsprot.basalmainpagehaha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.game_package.game_frame;
import com.lwtwka.basal.comsprot.game_package.rank;
import com.lwtwka.basal.comsprot.mayAsyncTask.DownloadImageTask;


public class mainpic extends Fragment {
    int pic;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.picitem, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pic=bundle.getInt("pic");
        }
        ImageView im=(ImageView)view.findViewById(R.id.im);
        im.setImageResource(pic);


    }


}
