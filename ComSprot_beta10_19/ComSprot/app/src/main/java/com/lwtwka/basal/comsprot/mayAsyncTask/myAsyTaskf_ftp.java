package com.lwtwka.basal.comsprot.mayAsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.lwtwka.basal.comsprot.activity.f1;
import com.lwtwka.basal.comsprot.adapter.userride_adapter;
import com.lwtwka.basal.comsprot.database.myDB;
import com.lwtwka.basal.comsprot.progressbar.myprogreedialog;
import com.lwtwka.basal.comsprot.userDetail;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by leo on 2015/8/9.
 */
public class myAsyTaskf_ftp extends AsyncTask<Void, Void, Void> {
    Context context;
    String urib;
    String name;
    public myAsyTaskf_ftp(Context context,String urib,String name) {
        this.context=context;
        this.urib=urib;
        this.name=name;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Void doInBackground(Void... params) {
        ftpUpload("163.14.68.47", "21", "QQ", "basal",
                "map",urib, name+".png");


        return null;
    }

    @Override
    protected void onPostExecute(Void res) {
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
