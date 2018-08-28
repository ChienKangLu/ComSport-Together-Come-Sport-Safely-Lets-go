package com.lwtwka.basal.comsprot;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.urlimageviewhelper.UrlImageViewCallback;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.adapter.user_profifle_adapter;
import com.lwtwka.basal.comsprot.adapter.userride_adapter;
import com.lwtwka.basal.comsprot.mayAsyncTask.DownloadImageTask2;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class user_profile extends Fragment {


    public user_profile() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_user_profile, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ImageView im=(ImageView)view.findViewById(R.id.profile_image);
        userDetail user=new userDetail(getActivity());
        CircleImageView profile_image=(CircleImageView)view.findViewById(R.id.profile_image);
        UrlImageViewHelper.setUrlDrawable(profile_image, "http://163.14.68.47/map/" + "image"+user.pic() + ".png", R.drawable.circlepichaha, new UrlImageViewCallback() {
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
        /*
        new DownloadImageTask2(im)
                .execute("http://163.14.68.47/map/" + "image"+user.pic() + ".png");//image38
        */

        MyListView L=(MyListView)getActivity().findViewById(R.id.list1);

        user_profifle_adapter adapter=new user_profifle_adapter(getActivity(),4);
        L.setAdapter(adapter);

        L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (position == 0) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), heightchange.class);
                    startActivity(intent);
                }

                if (position == 1) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), weightchange.class);
                    startActivity(intent);
                }

                if (position == 3) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), agechange.class);
                    startActivity(intent);
                }

                /*
                Intent intent = new Intent();
                intent.setClass(getActivity(), userride2_detail.class);
                startActivity(intent);
                */
                // TODO Auto-generated method stub
                // TextView title = (TextView) ((RelativeLayout) view).getChildAt(0);

            }
        });
        TextView name=(TextView)getActivity().findViewById(R.id.name);
        name.setText(""+user.name());
        TextView acc=(TextView)getActivity().findViewById(R.id.accounttitle);
        acc.setOnClickListener(music1);
    }



    private View.OnClickListener music1 = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), codechange.class);
            startActivity(intent);

        }
    };
}
