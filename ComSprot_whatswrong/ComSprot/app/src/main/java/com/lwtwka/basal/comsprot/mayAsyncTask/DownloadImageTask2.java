package com.lwtwka.basal.comsprot.mayAsyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.lwtwka.basal.comsprot.userDetail;

import java.io.InputStream;
import java.net.URL;

public  class DownloadImageTask2 extends AsyncTask<String, Void, Bitmap> {
 //ImageView bmImage;


        ImageView bmImage;

        public DownloadImageTask2(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            URL imageURL=null;
            try {
               // InputStream in = new java.net.URL(urldisplay).openStream();
               // mIcon11 = BitmapFactory.decodeStream(in);
                imageURL = new URL(urldisplay);
                //BitmapFactory.Options options = new BitmapFactory.Options();
                //options.inSampleSize=3;
                //BitmapFactory.Options o = new BitmapFactory.Options();
               // o.inJustDecodeBounds = true;
                mIcon11 = BitmapFactory.decodeStream(imageURL.openStream());

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            if(result!=null) {
                bmImage.setImageBitmap(getCircleBitmap(result));//getCircleBitmap(
                result=null;
                //result.recycle();
                Log.v("imdd", "good");
            }

        }


    public Bitmap getCircleBitmap(Bitmap bitmap)
    {
        Bitmap output;
        Canvas canvas = null;
        final int color = 0xffff0000;
        final Paint paint = new Paint();
        Rect rect = null;
/*
        if (bitmap.getHeight() > 501)
        {
            output = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
            canvas = new Canvas(output);
            rect = new Rect(0, 0, 500, 500);
        }
        else
        {
        */
            //System.out.println("output            else =======");
            bitmap = Bitmap.createScaledBitmap(bitmap, 500, 500,true);

            System.gc();
            output = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);

            canvas = new Canvas(output);
            rect = new Rect(0, 0, 500, 500);
      //  }
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