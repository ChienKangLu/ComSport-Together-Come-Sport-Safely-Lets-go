package com.lwtwka.basal.comsprot.activity;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.urlimageviewhelper.UrlImageViewCallback;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.adapter.IdeasExpandableListAdapter;
import com.lwtwka.basal.comsprot.adapter.NavigationDrawerAdapter;
import com.lwtwka.basal.comsprot.basalmainpagehaha.basalmain;
import com.lwtwka.basal.comsprot.frd.friend;
import com.lwtwka.basal.comsprot.mayAsyncTask.DownloadImageTask;
import com.lwtwka.basal.comsprot.mayAsyncTask.DownloadImageTask2;
import com.lwtwka.basal.comsprot.model.NavDrawerItem;
import com.lwtwka.basal.comsprot.userDetail;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class FragmentDrawer extends Fragment {

    private static String TAG = FragmentDrawer.class.getSimpleName();

    private RecyclerView recyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationDrawerAdapter adapter;
    private View containerView;
    private static String[] titles = {"首頁","SOS模組","競速挑戰","單車導航","設定","登出"};
    private FragmentDrawerListener drawerListener;

    public FragmentDrawer() {
    }

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }

    public static List<NavDrawerItem> getData() {
        List<NavDrawerItem> data = new ArrayList<>();
        // preparing navigation drawer items
        for (int i = 0; i < titles.length; i++) {
            NavDrawerItem navItem = new NavDrawerItem();
            navItem.setTitle(titles[i]);
            data.add(navItem);
        }
        return data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // drawer labels
        //titles = getActivity().getResources().getStringArray(R.array.nav_drawer_labels);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CircleImageView go=(CircleImageView)getActivity().findViewById(R.id.frd_1);

        go.setOnClickListener(music1);
    }

    @Override
    public void onResume() {
        super.onResume();

        userDetail user =new userDetail(getActivity());

        TextView name=(TextView)view.findViewById(R.id.name);
        name.setText(""+user.name());

        TextView email=(TextView)view.findViewById(R.id.email);
        email.setText("" + user.email());
        //ImageView im=(ImageView)view.findViewById(R.id.profile_image);
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

        //Log.v("測試2",""+userDetail.i);
       // if(userDetail.photo!=null) {
      //      im.setImageBitmap(userDetail.photo);
       //     Log.v("測試", "成功");
       // }else{
       //     Log.v("測試","失敗");
       // }
       // new DownloadImageTask(im)
       //         .execute("http://163.14.68.47/map/" + "image14" + ".png");

    }

    @Override
    public void onStart() {
        super.onStart();
    }
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating view layout
         view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
       // view = inflater.inflate(R.layout.fragment_fragmentdrawer2, null);

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
                TextView key=(TextView)((LinearLayout)v).getChildAt(1);
                int keyn=Integer.parseInt(key.getText().toString());
                drawerListener.onDrawerItemSelected(v, keyn);
                mDrawerLayout.closeDrawer(containerView);
              //  Toast.makeText(getActivity(),""+keyn, Toast.LENGTH_SHORT).show();

                return true;
            }
        });
//////////////////////////////////////////////////////

        recyclerView = (RecyclerView) view.findViewById(R.id.drawerList);

        this.adapter = new NavigationDrawerAdapter(getActivity(), getData());
        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //點擊後關閉
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                drawerListener.onDrawerItemSelected(view, position);
                mDrawerLayout.closeDrawer(containerView);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return view;
    }

//綁定
    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);//最大的activity <--實體
        mDrawerLayout = drawerLayout;//<--編號
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {//抽屜打開
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {//抽屜關上
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();

            }
        });

    }
    //綁定
    public void setUp2(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);//最大的activity <--實體
        mDrawerLayout = drawerLayout;//<--編號
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);


            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();

            }
        });

    }

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }
    }

    public interface FragmentDrawerListener {
        public void onDrawerItemSelected(View view, int position);
    }

    private View.OnClickListener music1 = new View.OnClickListener() {
        public void onClick(View v) {

            Fragment fragment = new friend();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment, "");
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();
            ((MainActivity)getActivity()).getSupportActionBar().setTitle("好友");
            mDrawerLayout.closeDrawer(containerView);
        }
    };
}
