package com.lwtwka.basal.comsprot.basalmainpagehaha;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.lwtwka.basal.comsprot.R;
import com.lwtwka.basal.comsprot.activity.BlankFragment;
import com.lwtwka.basal.comsprot.activity.MainActivity;
import com.lwtwka.basal.comsprot.activity.youtubelist_fragment;
import com.lwtwka.basal.comsprot.adapter.ElasticScrollView;
import com.lwtwka.basal.comsprot.adapter.MyListView;
import com.lwtwka.basal.comsprot.adapter.gameAdapter;
import com.lwtwka.basal.comsprot.analyst;
import com.lwtwka.basal.comsprot.animation.ActivityAnimator;
import com.lwtwka.basal.comsprot.challenge;
import com.lwtwka.basal.comsprot.game;
import com.lwtwka.basal.comsprot.myscrollview;
import com.lwtwka.basal.comsprot.road;
import com.lwtwka.basal.comsprot.share;
import com.lwtwka.basal.comsprot.sos;
import com.lwtwka.basal.comsprot.userride;
import com.lwtwka.basal.comsprot.userride2_detail_share;
import com.lwtwka.basal.comsprot.weightchange;

import java.util.ArrayList;

import zrc.widget.SimpleFooter;
import zrc.widget.SimpleHeader;
import zrc.widget.ZrcListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class basalmain extends Fragment implements myscrollview.OnHeaderRefreshListener {


    ViewPager viewPager;
    //int pic[]={R.drawable.bs1,R.drawable.bs2,R.drawable.bs3,R.drawable.bs4,R.drawable.bs5};
    int pic[]={R.drawable.mainrace1,R.drawable.mainrace2,R.drawable.mainrace5,R.drawable.mainrace6,R.drawable.mainrace2};

    PagerContainer mContainer;
    /*
    ////////
    private ZrcListView listView;
    private Handler handler;
    private ArrayList<String> msgs;
    private int pageId = -1;
    private MyAdapter adapter;
    private static final String[][] names = new String[][]{
            {"加拿大","瑞典","澳大利亚","瑞士","新西兰","挪威","丹麦","芬兰","奥地利","荷兰","德国","日本","比利时","意大利","英国"},
            {"德国","西班牙","爱尔兰","法国","葡萄牙","新加坡","希腊","巴西","美国","阿根廷","波兰","印度","秘鲁","阿联酋","泰国"},
            {"智利","波多黎各","南非","韩国","墨西哥","土耳其","埃及","委内瑞拉","玻利维亚","乌克兰"},
            {"以色列","海地","中国","沙特阿拉伯","俄罗斯","哥伦比亚","尼日利亚","巴基斯坦","伊朗","伊拉克"}
    };
    ///////
    */
    public basalmain() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view=inflater.inflate(R.layout.basalmain, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ElasticScrollView mScrollView = (ElasticScrollView)view.findViewById(R.id.mysv);
       // ImageView mBackgroundImageView = (ImageView) view.findViewById(R.id.personal_background_image);
        //mScrollView.setImageView(mBackgroundImageView);
       // mScrollView.setOnHeaderRefreshListener(this);
        ///////////////
        ScrollView aa=null;
        MyListView L=(MyListView)view.findViewById(R.id.list1);
        road_shareroad_adapter adapter=new road_shareroad_adapter(getActivity(),10);
        L.setAdapter(adapter);

        //myAsyTask_shareroad HAHA = new myAsyTask_shareroad(getActivity(),L);
        //HAHA.execute();


        L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView key = (TextView) ((RelativeLayout) view).getChildAt(0);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("id", key.getText().toString());
                //將Bundle物件assign給intent
                intent.putExtras(bundle);
                intent.setClass(getActivity(), userride2_detail_share.class);
                startActivity(intent);

            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("首頁");
        ImageView jointb=(ImageView)view.findViewById(R.id.join);
        jointb.setOnClickListener(join);

        ImageView challeb=(ImageView)view.findViewById(R.id.ch);
        challeb.setOnClickListener(chall);

        ImageView mainic1=(ImageView)view.findViewById(R.id.mainic1);
        mainic1.setOnClickListener(goclick1);

        ImageView mainic2=(ImageView)view.findViewById(R.id.mainic2);
        mainic2.setOnClickListener(goclick2);

        ImageView mainic3=(ImageView)view.findViewById(R.id.mainic3);
        mainic3.setOnClickListener(goclick3);

        ImageView mainic4=(ImageView)view.findViewById(R.id.mainic4);
        mainic4.setOnClickListener(goclick4);

        ImageView mainic5=(ImageView)view.findViewById(R.id.mainic5);
        mainic5.setOnClickListener(goclick5);

        ImageView mainic6=(ImageView)view.findViewById(R.id.mainic6);
        mainic6.setOnClickListener(goclick6);

        ImageView mainic7=(ImageView)view.findViewById(R.id.mainic7);
        mainic7.setOnClickListener(goclick7);

        ImageView mainic8=(ImageView)view.findViewById(R.id.mainic8);
        mainic8.setOnClickListener(goclick8);



        mContainer = (PagerContainer)getActivity().findViewById(R.id.pager_container);
        final ViewPager pager = mContainer.getViewPager();
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            boolean isAutoPlay = false;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int i) {
                int pageIndex = i;
                if (i == 0) {
                    // 当视图在第一个时，将页面号设置为图片的最后一张。
                    pageIndex = pic.length;
                } else if (i == pic.length + 1) {
                    // 当视图在最后一个是,将页面号设置为图片的第一张。
                    pageIndex = 1;
                }
                if (i != pageIndex) {
                    pager.setCurrentItem(pageIndex, false);
                    return;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                /*
                switch (state) {

                    case 1:// 手势滑动，空闲中

                        isAutoPlay = false;

                        break;

                    case 2:// 界面切换中

                        isAutoPlay = true;
                        break;
                    case 0:// 滑动结束，即切换完毕或者加载完毕
// 当前为最后一张，此时从右向左滑，则切换到第一张
                        if (pager.getCurrentItem() == pager.getAdapter().getCount() - 1 && !isAutoPlay) {

                            pager.setCurrentItem(0);

                        }

// 当前为第一张，此时从左向右滑，则切换到最后一张

                        else if (pager.getCurrentItem() == 0 && !isAutoPlay) {

                            pager.setCurrentItem(pager.getAdapter().getCount() - 1);

                        }

                        break;

                }
                */
            }
        });
        PagerAdapter adapterpic = new MyPagerAdapter(pic,getActivity());
        //viewPager.setAdapter(new mainpicAdapter(getChildFragmentManager(),getActivity()));
        pager.setAdapter(adapterpic);
        pager.setCurrentItem(1);
        //Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        pager.setOffscreenPageLimit(adapterpic.getCount());
        //A little space between pages
        pager.setPageMargin(15);

        //If hardware acceleration is enabled, you should also remove
        // clipping on the pager for its children.
        pager.setClipChildren(false);
        /*
        更新好物!!!別刪
        /////////////////////////////////////////////////////////
        ///////////////////////////////////////////
       // ZrcListView hh=
        listView = (ZrcListView) getActivity().findViewById(R.id.zListView);
        handler = new Handler();

        // 设置默认偏移量，主要用于实现透明标题栏功能。（可选）
        float density = getResources().getDisplayMetrics().density;
        listView.setFirstTopOffset((int) (50 * density));

        // 设置下拉刷新的样式（可选，但如果没有Header则无法下拉刷新）
        SimpleHeader header = new SimpleHeader(getActivity());
        header.setTextColor(0xff0066aa);
        header.setCircleColor(0xff33bbee);
        listView.setHeadable(header);

        // 设置加载更多的样式（可选）
        SimpleFooter footer = new SimpleFooter(getActivity());
        footer.setCircleColor(0xff33bbee);
        listView.setFootable(footer);

        // 设置列表项出现动画（可选）
        listView.setItemAnimForTopIn(R.anim.topitem_in);
        listView.setItemAnimForBottomIn(R.anim.bottomitem_in);

        // 下拉刷新事件回调（可选）
        listView.setOnRefreshStartListener(new ZrcListView.OnStartListener() {
            @Override
            public void onStart() {
                refresh();
            }
        });

        // 加载更多事件回调（可选）
        listView.setOnLoadMoreStartListener(new ZrcListView.OnStartListener() {
            @Override
            public void onStart() {
                loadMore();
            }
        });

        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        listView.refresh(); // 主动下拉刷新
        */














        //
        /*
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

			/*根據螢幕大小改變數量*/
        /*
        int gridViewEntrySize = dip2px(this,165);//64
        int gridViewSpacing = dip2px(this, 10);//10
        int numColumns = (display.getWidth() - gridViewSpacing) / (gridViewEntrySize + gridViewSpacing);
        */

        //


        //viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
         //  viewPager.setPadding(20, 0, 20, 0);
         //  viewPager.setClipToPadding(false);

/*
        RelativeLayout mViewPagerContainer = (RelativeLayout)getActivity().findViewById(R.id.con);

        int pagerWidth =  (int) (getResources().getDisplayMetrics().widthPixels*3.0f/5.0f);
        viewPager.measure(0, 0);

        ViewGroup.LayoutParams lp = viewPager.getLayoutParams();
        if(lp==null)
        {
            lp = new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        }else{
            lp.width = pagerWidth;
        }
        viewPager.setLayoutParams(lp);//設置頁面寬度为屏幕的3/5
        */
        //viewPager.setOffscreenPageLimit(4);  //設置ViewPager至多緩存4個Pager頁面，防止多次加載
        //viewPager.setPageMargin(10);  //設置Pager之間的間距

        /*
        *
        * */
        //pager settings
        //int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20 * 2, getResources().getDisplayMetrics());
       // viewPager.setPageMargin(-margin);

       // viewPager.setPadding(70, 0, 70, 0);
      //  viewPager.setClipToPadding(false);

        //viewPager.setPageMargin(10);

        /*
        *
        */
        /*
        viewPager.setAdapter(new mainpicAdapter(getChildFragmentManager(),getActivity()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        */
        // Give the PagerSlidingTabStrip the ViewPager

        //PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) getActivity().findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
       // tabsStrip.setViewPager(viewPager);
      //  viewPager.setTag("aaa");
    }

    @Override
    public void onHeaderRefresh(myscrollview view) {

    }
/*
    private void refresh(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int rand = (int) (Math.random() * 2); // 随机数模拟成功失败。这里从有数据开始。
                if (rand == 0 || pageId == -1) {
                    pageId = 0;
                    msgs = new ArrayList<String>();
                    for (String name : names[0]) {
                        msgs.add(name);
                    }
                    adapter.notifyDataSetChanged();
                    listView.setRefreshSuccess("加载成功"); // 通知加载成功
                    listView.startLoadMore(); // 开启LoadingMore功能
                } else {
                    listView.setRefreshFail("加载失败");
                }
            }
        }, 2 * 1000);
    }

    private void loadMore(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pageId++;
                if(pageId<names.length){
                    for(String name:names[pageId]){
                        msgs.add(name);
                    }
                    adapter.notifyDataSetChanged();
                    listView.setLoadMoreSuccess();
                }else{
                    listView.stopLoadMore();
                }
            }
        }, 2 * 1000);
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return msgs==null ? 0 : msgs.size();
        }
        @Override
        public Object getItem(int position) {
            return msgs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            if(convertView==null) {
                textView = (TextView) getLayoutInflater(getArguments()).inflate(android.R.layout.simple_list_item_1, null);
            }else{
                textView = (TextView) convertView;
            }
            textView.setText(msgs.get(position));
            return textView;
        }
    }
    */
private View.OnClickListener join = new View.OnClickListener() {
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), joinbikeActivity.class);
        startActivity(intent);
        /*
        try
        {
            ActivityAnimator anim = new ActivityAnimator();
            anim.fadeAnimation(getActivity());
        }
        catch (Exception e) { //Toast.makeText(this, "An error occured " + e.toString(), Toast.LENGTH_LONG).show()

        }
        */

    }
};
    private View.OnClickListener chall = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), challenge.class);
            startActivity(intent);
        /*
        try
        {
            ActivityAnimator anim = new ActivityAnimator();
            anim.fadeAnimation(getActivity());
        }
        catch (Exception e) { //Toast.makeText(this, "An error occured " + e.toString(), Toast.LENGTH_LONG).show()

        }
        */

        }
    };
    public void go(String titleget,Fragment f){
        String tag="f6";
        // fragment = new MessagesFragment();
        // fragment = new fragmentdrawer2();
        Fragment fragment = f;
        String title = titleget;
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();
        //     Toast.makeText(this.getBaseContext(), tag,
        //             Toast.LENGTH_LONG).show();
        // set the toolbar title
        ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle(title);
    }
    private View.OnClickListener goclick1 = new View.OnClickListener() {
        public void onClick(View v) {
            Fragment fragment = new userride();
            String title = "個人路線";
            go(title,fragment);
        }
    };
    private View.OnClickListener goclick2 = new View.OnClickListener() {
        public void onClick(View v) {

            Fragment fragment = new BlankFragment();
            String title = "ComSprot";
            go(title,fragment);
        }
    };
    private View.OnClickListener goclick3 = new View.OnClickListener() {
        public void onClick(View v) {

            Fragment fragment = new analyst();
            String title = "分析資料";
            go(title,fragment);
        }
    };
    private View.OnClickListener goclick4 = new View.OnClickListener() {
        public void onClick(View v) {
            Fragment fragment = new sos();
            String title = "SOS模組";
            go(title,fragment);
        }
    };
    private View.OnClickListener goclick5 = new View.OnClickListener() {
        public void onClick(View v) {

            Fragment fragment = new game();
            String title = "game";
            go(title,fragment);
        }
    };
    private View.OnClickListener goclick6 = new View.OnClickListener() {
        public void onClick(View v) {
            Fragment fragment = new youtubelist_fragment();
            String title = "路線影片";
            go(title,fragment);
        }
    };
    private View.OnClickListener goclick7 = new View.OnClickListener() {
        public void onClick(View v) {

            Fragment fragment = new road();
            String title = "參考路線";
            go(title,fragment);
        }
    };
    private View.OnClickListener goclick8 = new View.OnClickListener() {
        public void onClick(View v) {
            Fragment fragment = new share();
            String title = "社群分享";

            go(title,fragment);
        }
    };
    private View.OnClickListener m1 = new View.OnClickListener() {
        public void onClick(View v) {
            /*
            ImageView left=(ImageView)view.findViewById(R.id.left);
            ImageView right=(ImageView)view.findViewById(R.id.right);
            ImageView center=(ImageView)view.findViewById(R.id.center);
            left.setVisibility(View.GONE);
            right.setVisibility(View.GONE);
            center.setVisibility(View.GONE);
            */
            Intent intent = new Intent();
            intent.setClass(getActivity(), joinbikeActivity.class);
            startActivity(intent);

        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //this.menu=menu;
        setHasOptionsMenu(true);
        inflater.inflate(R.menu.basalbmain, menu);
    }
    private int PLACE_PICKER_REQUEST = 1;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.box:
                PlacePicker.IntentBuilder intentBuilder =
                        new PlacePicker.IntentBuilder();
                Intent intent = null;
                try {
                    intent = intentBuilder.build(getActivity());
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                // Start the intent by requesting a result,
                // identified by a request code.
                startActivityForResult(intent, PLACE_PICKER_REQUEST);




                /*
                Uri gmmIntentUri = Uri.parse("google.navigation:q=Taronga+Zoo,+Sydney+Australia");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                */
                //item.setIcon(R.drawable.loacationtarget_b);
               // reload();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
