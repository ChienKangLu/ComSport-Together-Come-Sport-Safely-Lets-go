package com.lwtwka.basal.comsprot.ftpconnect;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FTPUploader{

    /**

     * @param port 窗口（可默認）

     * @param url FTP hostname

     * @param username FTP登入帳號

     * @param password FTP登入密碼

     */

    int port = 21;

    String url = "163.14.68.47";

    String username = "QQ";

    String password = "basal";

    FTPClient ftp = new FTPClient();

    public FTPUploader(){

        try{

            ftp.connect(url, port);//連接FTP服務器

            //如果採用默認端口，可以使用ftp.connect(url)的方式直接連接FTP服務器

        }catch (Exception e){

            System.out.println(e);

        }

    }

    /**

     * @param path FTP上的保存目錄

     * @param filename 文件名稱

     * @param input 輸入流

     */

    public boolean uploadFile(String path, String filename, InputStream input) {



        boolean success = false;

        try {

            int reply;

            ftp.login(username, password);//登錄

            reply = ftp.getReplyCode();

            ftp.makeDirectory(path);

            if (!FTPReply.isPositiveCompletion(reply)) {

                ftp.disconnect();

                return success;

            }

            //ftp.setFileType(FTP.BINARY_FILE_TYPE);如果是圖片

            ftp.changeWorkingDirectory(path);

            ftp.storeFile(filename, input);



            input.close();

            ftp.logout();

            success = true;

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (ftp.isConnected()) {

                try {

                    ftp.disconnect();

                } catch (IOException ioe) {

                }

            }

        }

        return success;//成功返回true

    }



    /**

     * @param remoteFileName 文件在ftp上的路徑

     * @param localSavePath 下載下來的文件存放路徑

     */

    public void downloadFile(String remoteFileName,String localSavePath) {

        FileOutputStream fos = null;



        try {

            ftp.connect(url, port);

            ftp.login(username, password);

            File tmp = new File(localSavePath);

            if (!tmp.exists())

                tmp.createNewFile();

            fos = new FileOutputStream(localSavePath);



            ftp.setBufferSize(1024);

            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);

            ftp.retrieveFile(remoteFileName, fos);

        } catch (IOException e) {

            e.printStackTrace();

            throw new RuntimeException("FTP客戶端出錯！", e);

        } finally {

            IOUtils.closeQuietly(fos);

            try {

                ftp.disconnect();

            } catch (IOException e) {

                e.printStackTrace(); //關閉連結異常

            }

        }

    }

}