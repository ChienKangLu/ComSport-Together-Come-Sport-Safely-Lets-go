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

     * @param port ���f�]�i�q�{�^

     * @param url FTP hostname

     * @param username FTP�n�J�b��

     * @param password FTP�n�J�K�X

     */

    int port = 21;

    String url = "163.14.68.47";

    String username = "QQ";

    String password = "basal";

    FTPClient ftp = new FTPClient();

    public FTPUploader(){

        try{

            ftp.connect(url, port);//�s��FTP�A�Ⱦ�

            //�p�G�ĥ��q�{�ݤf�A�i�H�ϥ�ftp.connect(url)���覡�����s��FTP�A�Ⱦ�

        }catch (Exception e){

            System.out.println(e);

        }

    }

    /**

     * @param path FTP�W���O�s�ؿ�

     * @param filename ���W��

     * @param input ��J�y

     */

    public boolean uploadFile(String path, String filename, InputStream input) {



        boolean success = false;

        try {

            int reply;

            ftp.login(username, password);//�n��

            reply = ftp.getReplyCode();

            ftp.makeDirectory(path);

            if (!FTPReply.isPositiveCompletion(reply)) {

                ftp.disconnect();

                return success;

            }

            //ftp.setFileType(FTP.BINARY_FILE_TYPE);�p�G�O�Ϥ�

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

        return success;//���\��^true

    }



    /**

     * @param remoteFileName ���bftp�W�����|

     * @param localSavePath �U���U�Ӫ����s����|

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

            throw new RuntimeException("FTP�Ȥ�ݥX���I", e);

        } finally {

            IOUtils.closeQuietly(fos);

            try {

                ftp.disconnect();

            } catch (IOException e) {

                e.printStackTrace(); //�����s�����`

            }

        }

    }

}