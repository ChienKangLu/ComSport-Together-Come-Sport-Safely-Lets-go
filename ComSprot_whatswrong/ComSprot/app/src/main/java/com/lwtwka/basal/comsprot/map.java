package com.lwtwka.basal.comsprot;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.mayAsyncTask.myAsyTaskf1;
import com.lwtwka.basal.comsprot.mayAsyncTask.myAsyTaskf_ftp;
import com.lwtwka.basal.comsprot.screenshot.CaptureScreeUtil;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.FileInputStream;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class map extends Fragment {

    public map() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }
    WebView webview;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        webview=(WebView)getActivity().findViewById(R.id.webView);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("file:///android_asset/mht2.html");
    }

    @Override
    public void onResume() {
        super.onResume();
       // final String markURL = "javascript:mark(" +(-33.890542) + "," + 151.274856 + ")";
      //  webview.loadUrl(markURL);
       // Button b=(Button)getActivity().findViewById(R.id.button2);
       //b.setOnClickListener(music1);

    }
    public void addtomap(double lat,double lon,double lat2,double lon2){
        //final String markURL = "javascript:mark(" +(-33.890542) + "," + 151.274856 + ")";
       // webview.loadUrl(markURL);
        final String markURL2 = "javascript:line("+lat+","+lon+","+lat2+","+lon2+")";
        webview.loadUrl(markURL2);
        //final String markURL3 = "javascript:line2()";
        //webview.loadUrl(markURL3);
    }
    public void centerAt(double lat,double lon){
        final String markURL2 = "javascript:centerAt("+lat+","+lon+")";
        webview.loadUrl(markURL2);
    }
    public void cleanmygooglemapwebview(){
        webview.loadUrl("file:///android_asset/mht2.html");
    }
    /*
    private View.OnClickListener music1 = new View.OnClickListener() {
        public void onClick(View v) {
             final String markURL = "javascript:mark(" +(-33.890542) + "," + 151.274856 + ")";
              webview.loadUrl(markURL);
        }
    };
    */

    public void screenshot(){
        Bitmap mCapture = CaptureScreeUtil.capture2(webview, 500, 500, true, Bitmap.Config.ARGB_8888);//500,500 CaptureScreeUtil.capture2(mCaptureWidth, mCaptureHeight, myWebView);
        String data[]=CaptureScreeUtil.screenshot(getActivity(), mCapture, "map");
        //ftpUpload("163.14.68.47", "21", "QQ", "basal",
        //        "pic1",urib, "basal2.png");
        myDB mydb=new myDB(getActivity());
        mydb.Connect();
        String name=mydb.getimagetite();//id-->n+1
        /////////////////////////////////////////////////////
        //insert image
       // mydb.insertimage(name);


        //latest bikerecordid
       // String bikerecordid=""+mydb.getlatestinsertbikerecordid();
        //update image bikerecord
      //  mydb.updatebikerecord(name,bikerecordid);
/////////////////////////////////////////////////////////////////////////
        mydb.closedb();
        myAsyTaskf_ftp HAHA = new myAsyTaskf_ftp(getActivity(),data[0],"image"+name);
        HAHA.execute();
    }

    public static String ftpUpload(String url, String port, String username,
                                   String password, String remotePath, String fileNamePath,
                                   String fileName) {//伺服器位置、port、使用者名稱、密碼、要放在server的路徑、手機端的路徑、上傳到server的檔名
        FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null;
        String returnMessage = "0";
        try {
            ftpClient.connect(url, Integer.parseInt(port));
            boolean loginResult = ftpClient.login(username, password);
            int returnCode = ftpClient.getReplyCode();

            if (loginResult && FTPReply.isPositiveCompletion(returnCode)) {// 如果登入成功
                Log.v("loginResult", "登入成功");
                // 设置上傳目錄
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                //設置圖片格式
                ftpClient.setControlEncoding("GBK");// UTF-8
                // 设置文件類型(二進位制)
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                fis = new FileInputStream(fileNamePath);

                boolean result = ftpClient.storeFile(fileName, fis);
                fis.close();
                if (result == true) {
                    returnMessage = "1"; // 上傳成功
                    Log.v("upload result", "上傳成功");
                }
            } else {// 如果登入失敗
                returnMessage = "0";
                Log.v("loginResult", "登入失敗");
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FTP客戶端出錯！", e);
        } finally {
            // IOUtils.closeQuietly(fis);
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("關閉FTP連接發生異常！", e);
            }
        }
        return returnMessage;
    }

}
