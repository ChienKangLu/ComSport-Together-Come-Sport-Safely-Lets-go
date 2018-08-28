package com.lwtwka.basal.comsprot;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.mayAsyncTask.myAsyTaskf_ftp;
import com.lwtwka.basal.comsprot.screenshot.CaptureScreeUtil;
import com.lwtwka.basal.comsprot.screenshot.MagicFileChooser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TreeMap;

import de.hdodenhof.circleimageview.CircleImageView;


public class regist extends ActionBarActivity {
    final int IMAGE_CODE = 1;
    Uri uri;
    String uriab;
    Bitmap bitmap;
    boolean mustCanRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ////////////////////////
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //////////////////////////

    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageView add = (ImageView) findViewById(R.id.pic);
        add.setOnClickListener(music2);
        TextView savet = (TextView) findViewById(R.id.save);
        savet.setOnClickListener(update);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_regist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        boolean flag = false;
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                flag = true;
                break;
            default:
                flag = super.onOptionsItemSelected(item);
                break;
        }
        return flag;
    }

    private View.OnClickListener music2 = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            // 開啟Pictures畫面Type設定為image
            intent.setType("image/*");
            // 使用Intent.ACTION_GET_CONTENT這個Action //會開啟選取圖檔視窗讓您選取手機內圖檔
            intent.setAction(Intent.ACTION_GET_CONTENT);
            // 取得相片後返回本畫面
            startActivityForResult(intent, IMAGE_CODE);
        }
    };
    double w=0;
    double h=0;

    // 取得相片後返回的監聽式
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // 當使用者按下確定後
        if (resultCode == RESULT_OK) {
            // 取得圖檔的路徑位置
            uri = data.getData();

            // 呼叫選擇檔案的物件，將圖檔路徑傳進去已轉換成可上傳之真實路徑
            // (content==MainAcitvity.this,uri,boolean)
            uriab = MagicFileChooser.getAbsolutePathFromUri(regist.this,
                    uri, mustCanRead);
            // 顯示抓到之圖片路徑
            //TextView add = (TextView) findViewById(R.id.textView1);
            // add.setText(uriab);

            // 抽象資料的接口
            ContentResolver cr = this.getContentResolver();

            try {
                // 由抽象資料接口轉換圖檔路徑為Bitmap
                bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                w=bitmap.getWidth();
                h=bitmap.getHeight();
                // 取得圖片控制項ImageView
                ImageView imageView = (ImageView) findViewById(R.id.pic);
                // 將Bitmap設定到ImageView
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();

                //縮小為0.8倍
                double scale=100.0/height;
                float scaleWidth = (float) scale;
                float scaleHeight = (float) scale;//h *scle=480  scle=480/h

                // 取得想要缩放的matrix參數
                Matrix matrix = new Matrix();
                matrix.postScale(scaleWidth, scaleHeight);
                // 得到新的圖片

                Log.v("imagewh","h:"+height+",w:"+width+",s:"+scale);
                Bitmap newbm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,true);
                imageView.setImageBitmap(getCircleBitmap(newbm));


            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            }

            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private View.OnClickListener update = new View.OnClickListener() {
        public void onClick(View v) {
            //判斷帳號是否重複

            ////////////////圖片
            myDB mydb = new myDB(regist.this);
            mydb.Connect();
            EditText accountt = (EditText) findViewById(R.id.account);
            EditText namet = (EditText) findViewById(R.id.name);
            EditText codet = (EditText) findViewById(R.id.code);
            EditText emailt = (EditText) findViewById(R.id.email);

            TextView error=(TextView)findViewById(R.id.error);
            error.setText("");
            if(!accountt.getText().toString().equals("")&&!namet.getText().toString().equals("")&&!codet.getText().toString().equals("")&&!emailt.getText().toString().equals("")&&bitmap!=null) {
                if (mydb.addacount(accountt.getText().toString())) {
                    String name = mydb.getimagetite();//id-->n+1
                    mydb.insertimage(name);

                    //ImageView imageView = (ImageView) findViewById(R.id.pic);

                    mydb.insertuser(accountt.getText().toString(), codet.getText().toString(), namet.getText().toString(), emailt.getText().toString(), name);
                    mydb.closedb();


                    // Bitmap mCapture = CaptureScreeUtil.capture3(imageView, (int)w, (int)h, true, Bitmap.Config.ARGB_8888);//CaptureScreeUtil.capture2(mCaptureWidth, mCaptureHeight, myWebView);

                    int width = bitmap.getWidth();
                    int height = bitmap.getHeight();

                    //縮小為0.8倍
                    double scale = 100.0 / height;
                    float scaleWidth = (float) scale;
                    float scaleHeight = (float) scale;//h *scle=480  scle=480/h

                    // 取得想要缩放的matrix參數
                    Matrix matrix = new Matrix();
                    matrix.postScale(scaleWidth, scaleHeight);
                    // 得到新的圖片

                    Log.v("imagewh", "h:" + height + ",w:" + width + ",s:" + scale);
                    Bitmap newbm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);


                    String data[] = CaptureScreeUtil.screenshot2(regist.this, newbm, "map");
                    myAsyTaskf_ftp HAHA = new myAsyTaskf_ftp(regist.this, data[0], "image" + name);
                    HAHA.execute();

                    onBackPressed();
                    //myAsyTaskf_ftp HAHA = new myAsyTaskf_ftp(regist.this, uriab, "image" + name);
                    //HAHA.execute();
                } else {
                    error.setText("*帳號以經被申請，請更換帳號*");
                    mydb.closedb();
                }
            }else{
                error.setText("*有資料尚未填寫，請完成以上資料*");
                mydb.Connect();
            }
        }
    };
    public Bitmap getCircleBitmap(Bitmap bitmap)
    {
        Bitmap output;
        Canvas canvas = null;
        final int color = 0xffff0000;
        final Paint paint = new Paint();
        Rect rect = null;
        if (bitmap.getHeight() > 501)
        {
            output = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
            canvas = new Canvas(output);
            rect = new Rect(0, 0, 500, 500);
        }
        else
        {
            //System.out.println("output            else =======");
            bitmap = Bitmap.createScaledBitmap(bitmap, 500, 500, false);
            output = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
            canvas = new Canvas(output);
            rect = new Rect(0, 0, 500, 500);
        }
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setFilterBitmap(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth((float) 1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
}
