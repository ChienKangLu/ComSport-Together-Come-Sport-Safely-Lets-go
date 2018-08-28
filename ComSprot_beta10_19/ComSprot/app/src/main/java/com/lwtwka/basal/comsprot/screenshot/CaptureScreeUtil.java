package com.lwtwka.basal.comsprot.screenshot;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Picture;
        import android.net.Uri;
        import android.os.Environment;
        import android.util.Log;
        import android.view.View;
        import android.webkit.WebView;

        import java.io.File;
        import java.io.FileOutputStream;

public class CaptureScreeUtil{

    private static final String TAG = "TestCaptureScreen";

    public static Bitmap capture(int width,int height,View view) {
        // TODO Auto-generated method stub
        Log.i(TAG,"capture");

        Bitmap bitmap = Bitmap.createBitmap(width, height,Bitmap.Config.RGB_565);
        bitmap.eraseColor(Color.WHITE);

        Canvas canvas = new Canvas(bitmap);
        final int left = view.getScrollX();
        final int top = view.getScrollY();
        int state = canvas.save();
        canvas.translate(-left, -top);
        float scale = width / (float) view.getWidth();
        canvas.scale(scale, scale, left, top);
        view.draw(canvas);
        canvas.restoreToCount(state);

        // manually anti-alias the edges for the tilt
        Paint sAlphaPaint = new Paint();
        //sAlphaPaint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        sAlphaPaint.setColor(Color.TRANSPARENT);

        canvas.drawRect(0, 0, 1, height, sAlphaPaint);
        canvas.drawRect(width - 1, 0, width,
                height, sAlphaPaint);
        canvas.drawRect(0, 0, width, 1, sAlphaPaint);
        canvas.drawRect(0, height - 1, width,
                height, sAlphaPaint);
        canvas.setBitmap(null);

        return bitmap;
    }

    public static Bitmap capture2(View view, float width, float height, boolean scroll, Bitmap.Config config) {
        if (!view.isDrawingCacheEnabled()) {
            view.setDrawingCacheEnabled(true);
        }
       // view.buildDrawingCache();//
       // Bitmap bitmap=view.getDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap((int) width, (int) height, config);

        bitmap.eraseColor(Color.WHITE);
        Canvas canvas = new Canvas(bitmap);
        int left =view.getLeft();//view
        int top = view.getTop();//view
        if (scroll) {
            left = view.getScrollX();
            top = view.getScrollY();
        }
        int status = canvas.save();
        canvas.translate(-left, -top);
        float scale = width / view.getWidth();
        canvas.scale(scale, scale, left, top);
        view.draw(canvas);//view.draw(canvas);
        canvas.restoreToCount(status);
        Paint alphaPaint = new Paint();
        alphaPaint.setColor(Color.TRANSPARENT);
        canvas.drawRect(0f, 0f, 1f, height, alphaPaint);
        canvas.drawRect(width - 1f, 0f, width, height, alphaPaint);
        canvas.drawRect(0f, 0f, width, 1f, alphaPaint);
        canvas.drawRect(0f, height - 1f, width, height, alphaPaint);
        canvas.setBitmap(null);
        Bitmap newbitmap=Bitmap.createBitmap(bitmap,100,100,bitmap.getWidth()-100,bitmap.getHeight()-100);
        return newbitmap;
    }
    public static Bitmap capture3(View view, float width, float height, boolean scroll, Bitmap.Config config) {
        if (!view.isDrawingCacheEnabled()) {
            view.setDrawingCacheEnabled(true);
        }
        // view.buildDrawingCache();//
        // Bitmap bitmap=view.getDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap((int) width, (int) height, config);

        bitmap.eraseColor(Color.WHITE);
        Canvas canvas = new Canvas(bitmap);
        int left =view.getLeft();//view
        int top = view.getTop();//view
        if (scroll) {
            left = view.getScrollX();
            top = view.getScrollY();
        }
        int status = canvas.save();
        canvas.translate(-left, -top);
        float scale = width / view.getWidth();
        canvas.scale(scale, scale, left, top);
        view.draw(canvas);//view.draw(canvas);
        canvas.restoreToCount(status);
        Paint alphaPaint = new Paint();
        alphaPaint.setColor(Color.TRANSPARENT);
        canvas.drawRect(0f, 0f, 1f, height, alphaPaint);
        canvas.drawRect(width - 1f, 0f, width, height, alphaPaint);
        canvas.drawRect(0f, 0f, width, 1f, alphaPaint);
        canvas.drawRect(0f, height - 1f, width, height, alphaPaint);
        canvas.setBitmap(null);
        //Bitmap newbitmap=Bitmap.createBitmap(bitmap,30,30,bitmap.getWidth()-30*2,bitmap.getHeight()-30*2);

        return bitmap;
    }
    public static String[] screenshot(Context context, Bitmap bitmap, String name) {
        if (bitmap == null) {
            return null;
        }

        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (name == null || name.trim().isEmpty()) {
            name = String.valueOf(System.currentTimeMillis());
        }
        name = name.trim();

        int count = 0;
        File file = new File(dir, name + ".png");
        while (file.exists()) {
            count++;
            file = new File(dir, name + "_" + count + ".png");
        }
        /////
        Uri uri=Uri.fromFile(file);
        boolean mustCanRead = false;
        String uriab = MagicFileChooser.getAbsolutePathFromUri(context,
                uri, mustCanRead);

        ///////////
        String data[]=new String[2];
        data[0]=uriab;//本機位置
        data[1]=name + "_" + count;
        try {
            FileOutputStream stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG,100, stream);
            stream.flush();
            stream.close();
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));//file.getAbsolutePath();
            return data;
        } catch (Exception e) {
            return null;
        }
    }
    public static String[] screenshot2(Context context, Bitmap bitmap, String name) {
        if (bitmap == null) {
            return null;
        }

        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (name == null || name.trim().isEmpty()) {
            name = String.valueOf(System.currentTimeMillis());
        }
        name = name.trim();

        int count = 0;
        File file = new File(dir, name + ".png");
        while (file.exists()) {
            count++;
            file = new File(dir, name + "_" + count + ".png");
        }
        /////
        Uri uri=Uri.fromFile(file);
        boolean mustCanRead = false;
        String uriab = MagicFileChooser.getAbsolutePathFromUri(context,
                uri, mustCanRead);

        ///////////
        String data[]=new String[2];
        data[0]=uriab;//本機位置
        data[1]=name + "_" + count;
        try {
            FileOutputStream stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG,0, stream);
            stream.flush();
            stream.close();
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));//file.getAbsolutePath();
            return data;
        } catch (Exception e) {
            return null;
        }
    }
    public static Bitmap captureScreen(Activity context){

        View cv = context.getWindow().getDecorView();

        Bitmap bmp = Bitmap.createBitmap(cv.getWidth(), cv.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bmp);

        cv.draw(canvas);

        return bmp;

}

}