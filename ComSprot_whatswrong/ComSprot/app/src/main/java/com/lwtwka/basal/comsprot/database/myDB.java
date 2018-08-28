package com.lwtwka.basal.comsprot.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.drive.Contents;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by leo on 2015/7/31.
 */
public class myDB{

    String ipaddress = "163.14.68.47";
    String db = "basal";
    String username = "basal";
    String password = "basal";
    Connection Connection=null;
    Context context=null;
    public myDB(Context context){
            this.context=context;
    }
    public void Connect(){
        Connection= Connecthelp(username, password, db, ipaddress);
    }
    @SuppressLint("NewApi")
    private Connection Connecthelp(String user, String password,
                                        String database, String server) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + server + ";"
                    + "databaseName=" + database + ";user=" + user
                    + ";password=" + password + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        //Toast.makeText(context,"good",Toast.LENGTH_LONG).show();
        return connection;
    }
    public ArrayList<ArrayList<String>> query(){
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "SELECT * FROM dbo.test_Lu";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
              //  Toast.makeText(context, resultSet.getString("a").toString()+resultSet.getString("b").toString(),
               //         Toast.LENGTH_LONG).show();
                Log.v("medb", resultSet.getObject("a").toString() + resultSet.getObject("b").toString());
                ArrayList<String> innerdata=new ArrayList<String>();
                innerdata.add(resultSet.getString("a").toString());
                innerdata.add(resultSet.getString("b").toString());
                data.add(innerdata);
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public void closedb(){
        try {
            Connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertbikerecord(int userid,String weather,String date,String totalcount,String totaltime,String totalcal,String totaldis,String avgspeed,String avgalt,String maxspeed,String maxalt,String grade,String pic,String fav,String temp,String title){
        //userid,weather,date,totalcount,totaltime,totalcal,totaldis,avgspeed,avgalt,maxspeed,maxalt,grade,pic,fav,temp
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query ="INSERT INTO dbo.bikerecord (\"userid\",\"weather\",\"date\",\"totalcount\",\"totaltime\",\"totalcal\",\"totaldis\",\"avgspeed\",\"avgalt\",\"maxspeed\",\"maxalt\",\"grade\",\"pic\",\"fav\",\"temp\",\"title\") " +"VALUES ("+userid+", '"+weather+"', '"+date+"', '"+totalcount+"','"+totaltime+"','"+totalcal+"','"+totaldis+"','"+avgspeed+"','"+avgalt+"','"+maxspeed+"','"+maxalt+"','"+grade+"',"+pic+",'"+fav+"','"+temp+"','"+title+"')";
            statement.executeUpdate(query);

//            Toast.makeText(context, "goodinsert",
         //           Toast.LENGTH_LONG).show();
        }catch (SQLException e) {
          //  Toast.makeText(context, "gg",
           //         Toast.LENGTH_LONG).show();
            Log.v("finaltest","gg"+userid);
            e.printStackTrace();
        }
    }
    public void insertbikepoint(int bikerecordid,int count,String lat,String lon,String speed,String alt,String category){
        //bikerecordid,count,lat,lon,speed,alt,category
        // INSERT INTO dbo.bikepoint " +"VALUES"+ "("+bikerecordid+","+count+",'"+lat+"','"+lon+"','"+speed+"','"+alt+"',"+category+")
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query ="INSERT INTO dbo.bikepoint VALUES ("+bikerecordid+","+count+",'"+lat+"','"+lon+"','"+speed+"','"+alt+"','"+category+"')";
            statement.executeUpdate(query);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertbikepoint(String insertsql){
        //bikerecordid,count,lat,lon,speed,alt,category
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query ="INSERT INTO dbo.bikepoint " +"VALUES"+ insertsql;
            statement.executeUpdate(query);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getlatestinsertbikerecordid(){
        //SELECT TOP 1 * from dbo.bikerecord ORDER BY bikerecordid DESC
        Statement statement = null;
        int data=0;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "SELECT TOP 1 bikerecordid from dbo.bikerecord ORDER BY bikerecordid DESC";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data=resultSet.getInt("bikerecordid");
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public boolean login(String accoutin,String codein){
        //SELECT TOP 1 * from dbo.bikerecord ORDER BY bikerecordid DESC
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "SELECT * FROM dbo.[user]";
            resultSet = statement.executeQuery(query);
            String account="";
            String code="";
            while (resultSet.next()) {
                account=resultSet.getString("account");
                code=resultSet.getString("code");
                if(accoutin.equals(account)&&codein.equals(code)){
                    return true;
                }
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public String  getuserid(String account,String code){
        //SELECT TOP 1 * from dbo.bikerecord ORDER BY bikerecordid DESC
        Statement statement = null;
        int data=0;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "SELECT userid from dbo.[user] where account='"+account+"' and code='"+code+"'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data=resultSet.getInt("userid");
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ""+data;
    }

    public ArrayList<String>  getusertotal(String userid){
        //SELECT TOP 1 * from dbo.bikerecord ORDER BY bikerecordid DESC
        Statement statement = null;
        ArrayList<String> data=new ArrayList<>();
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "SELECT * from dbo.[user] where userid="+userid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data.add(""+resultSet.getInt("userid"));
                data.add(""+resultSet.getString("height"));
                data.add(""+resultSet.getString("weight"));
                data.add(""+resultSet.getString("age"));
                data.add(""+resultSet.getString("account"));
                data.add(""+resultSet.getString("code"));
                data.add(""+resultSet.getString("name"));
                data.add(""+resultSet.getString("applydate"));
                data.add(""+resultSet.getString("email"));
                data.add(""+resultSet.getString("pic"));
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public int getlatesttitle(String userid){
        //SELECT TOP 1 * from dbo.bikerecord ORDER BY bikerecordid DESC
        Statement statement = null;
        int data=0;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "SELECT count(*)+1  as count from dbo.bikerecord";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data=resultSet.getInt("count");
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<String>  selectpoint(int bikerecordid,String column){
        ArrayList<String> data=new ArrayList<>();
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "SELECT * FROM dbo.bikepoint where bikerecordid="+bikerecordid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data.add(resultSet.getString(column));
                //resultSet.getString("alt").toString()
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void updateheight(String title,String value,String userid){
        //userid,weather,date,totalcount,totaltime,totalcal,totaldis,avgspeed,avgalt,maxspeed,maxalt,grade,pic,fav,temp
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query ="update dbo.[user] set "+title+"="+value+" where userid="+userid;
            statement.executeUpdate(query);
            //Toast.makeText(context, "goodupdate",
            //        Toast.LENGTH_LONG).show();
        }catch (SQLException e) {
           // Toast.makeText(context, "gg",
            //        Toast.LENGTH_LONG).show();

            e.printStackTrace();
        }
    }

    public void inserttestLu(String name){
        //userid,weather,date,totalcount,totaltime,totalcal,totaldis,avgspeed,avgalt,maxspeed,maxalt,grade,pic,fav,temp
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query ="INSERT INTO dbo.test_Lu " +"VALUES ('"+name+"')";
            statement.executeUpdate(query);
            /*
            Toast.makeText(context, "goodinsert",
                    Toast.LENGTH_LONG).show();
                    */
        }catch (SQLException e) {
            /*
            Toast.makeText(context, "gg",
                    Toast.LENGTH_LONG).show();
                    */
            e.printStackTrace();
        }
    }
    public void inserttestLu2(String name){
        //userid,weather,date,totalcount,totaltime,totalcal,totaldis,avgspeed,avgalt,maxspeed,maxalt,grade,pic,fav,temp
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query ="INSERT INTO dbo.test_Lu " +"VALUES"+name;
            statement.executeUpdate(query);
            /*
            Toast.makeText(context, "goodinsert",
                    Toast.LENGTH_LONG).show();
                    */
        }catch (SQLException e) {
            /*
            Toast.makeText(context, "gg",
                    Toast.LENGTH_LONG).show();
                    */
            e.printStackTrace();
        }
    }
    public ArrayList<ArrayList<String>> selectallbikerecord(String userid){
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "SELECT date,totaltime,totalcal,totaldis,bikerecordid,fav  FROM  dbo.bikerecord a where userid="+userid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ArrayList<String> innerdata=new ArrayList<String>();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String date = format.format(resultSet.getDate("date"));
                innerdata.add(date);
                innerdata.add(resultSet.getString("totaltime").toString());
                innerdata.add(resultSet.getString("totalcal").toString());
                innerdata.add(resultSet.getString("totaldis").toString());
                innerdata.add(resultSet.getString("bikerecordid").toString());
                innerdata.add(resultSet.getString("fav").toString());
                data.add(innerdata);
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<ArrayList<String>> bikepointlonlat(String bikerecordid){
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "Select lat,lon from dbo.bikepoint where bikerecordid="+bikerecordid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ArrayList<String> innerdata=new ArrayList<String>();
                innerdata.add(resultSet.getString("lat").toString());
                innerdata.add(resultSet.getString("lon").toString());
                data.add(innerdata);
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<String> selectshowtotalbikerecord(String bikerecordid){
        ArrayList<String> data=new ArrayList<String>();
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "SELECT totaltime,totaldis,avgspeed,totalcal,maxspeed,maxalt,avgalt,temp,grade,totalcount,fav  FROM  dbo.bikerecord a where bikerecordid="+bikerecordid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String totaltime=resultSet.getString("totaltime").toString();//00:00:06
                totaltime=totaltime.replace(":","'");
                String totaldis=resultSet.getString("totaldis").toString();//3.2868543796277834
                if(Double.parseDouble(totaldis)<0.1){//0.01
                    totaldis=""+(int)(Double.parseDouble(totaldis)*1000)+"m";
                }else{
                    DecimalFormat df = new DecimalFormat("##.0");
                    totaldis=""+Double.parseDouble(df.format(Double.parseDouble(totaldis)))+" km";
                }
                String avgspeed=resultSet.getString("avgspeed").toString();//13.416274298156843

                DecimalFormat df = new DecimalFormat("##.0");
                avgspeed=""+Double.parseDouble(df.format(Double.parseDouble(avgspeed)))+" km/hr";

                String totalcal=resultSet.getString("totalcal").toString()+" kcal";//52.3

                String maxspeed=resultSet.getString("maxspeed").toString();//28.1880220413208
                maxspeed=""+Double.parseDouble(df.format(Double.parseDouble(maxspeed)))+"km/hr";

                String maxalt=resultSet.getString("maxalt").toString()+" 公尺";//56.0

                String avgalt=resultSet.getString("avgalt").toString();//29.919621749408982
                avgalt=""+Double.parseDouble(df.format(Double.parseDouble(avgalt)))+" 公尺";

                String temp=resultSet.getString("temp").toString();//29.84000015258789
                temp=""+Double.parseDouble(df.format(Double.parseDouble(temp)))+"°C";

                String grade=resultSet.getString("grade").toString();//0

                String totalcount=resultSet.getString("totalcount").toString();//238


//totaltime,totaldis,avgspeed,totalcal,maxspeed,maxalt,avgalt,temp,grade,totalcount
                data.add(totaltime);
                data.add(totaldis);
                data.add(avgspeed);
                data.add(totalcal);
                data.add(maxspeed);
                data.add(maxalt);
                data.add(avgalt);
                data.add(temp);
                data.add(grade);
                data.add(totalcount);
                data.add(resultSet.getString("fav").toString());
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<String> zoomppoint(String bikerecordid){
        ArrayList<String> data=new ArrayList<String>();
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "SELECT lat,lon from dbo.bikepoint where bikerecordid="+bikerecordid+" and count=((Select totalcount/2  FROM  dbo.bikerecord a where bikerecordid="+bikerecordid+" ))";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data.add(resultSet.getString("lat").toString());
                data.add(resultSet.getString("lon").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<String> selectuserdetail(String bikerecordid){
        ArrayList<String> data=new ArrayList<String>();
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "select date,title,weather,fav from dbo.bikerecord where bikerecordid="+bikerecordid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
                SimpleDateFormat format2 = new SimpleDateFormat("EEE");
                SimpleDateFormat format3 = new SimpleDateFormat("HH:mm:ss");
                Date dbdate=resultSet.getDate("date");
                String date=format1.format(dbdate);
                String week=format2.format(dbdate);
                Date dbdate2=resultSet.getTime("date");
                String time=format3.format(dbdate2);
                //
                //
                data.add(resultSet.getString("title").toString());//0
                data.add(date);//1
                data.add(week);//2
                data.add(time);//3
                data.add(resultSet.getString("weather").toString());//4
                data.add(resultSet.getString("fav").toString());//5
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<ArrayList<String>>  selectanalyst(String userid){
        Statement statement = null;
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "select  * from dbo.analyst where userid="+userid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ArrayList<String> innerdata=new ArrayList<String>();
                innerdata.add("" + resultSet.getInt("analystid"));
                innerdata.add("" + resultSet.getString("title"));
                data.add(innerdata);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<ArrayList<String>> selectanalystdetail(String analystid){
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "select title,totaldis,date from  dbo.analystdetail a left join dbo.bikerecord b on a.bikerecordid=b.bikerecordid where analystid="+analystid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ArrayList<String> innerdata=new ArrayList<String>();
                //
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
                Date dbdate=resultSet.getDate("date");
                String date=format1.format(dbdate);
                //
                innerdata.add(resultSet.getString("title").toString());
                //
                String totaldis=resultSet.getString("totaldis").toString();
                DecimalFormat df = new DecimalFormat("##.0");
                totaldis=""+Double.parseDouble(df.format(Double.parseDouble(totaldis)))+" km";
                //
                innerdata.add(totaldis);
                //
                innerdata.add(date);
                //
                data.add(innerdata);
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public String  getroadnumber(String analystid){
        //SELECT TOP 1 * from dbo.bikerecord ORDER BY bikerecordid DESC
        Statement statement = null;
        int data=0;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "select count(*)as count from dbo.analystdetail where analystid="+analystid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data=resultSet.getInt("count");
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ""+data;
    }
    public ArrayList<String>  analystrecordid(String analystid){
        ArrayList<String> data=new ArrayList<>();
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "select bikerecordid from dbo.analystdetail where analystid="+analystid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data.add(resultSet.getString("bikerecordid"));
                //resultSet.getString("alt").toString()
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public void deletbikerecord(String bikerecordid){
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query ="DELETE FROM dbo.bikepoint where bikerecordid="+bikerecordid;
            statement.executeUpdate(query);
            String query2 ="DELETE FROM dbo.analystdetail where bikerecordid="+bikerecordid;
            statement.executeUpdate(query2);
            String query3 ="DELETE FROM dbo.bikerecord where bikerecordid="+bikerecordid;
            statement.executeUpdate(query3);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteanalyst(String analystid){
        Statement statement = null;
        try {
            statement = Connection.createStatement();
         //   String query ="DELETE FROM dbo.bikepoint where bikerecordid="+bikerecordid;
        //    statement.executeUpdate(query);
            String query2 ="DELETE FROM dbo.analystdetail where analystid="+analystid;
            statement.executeUpdate(query2);
            String query3 ="DELETE FROM dbo.analyst where analystid="+analystid;
            statement.executeUpdate(query3);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<ArrayList<String>> selectallbikerecordnewtitle(String userid){
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "SELECT title,date,totaltime,totalcal,totaldis,bikerecordid,fav,pic  FROM  dbo.bikerecord a where userid="+userid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ArrayList<String> innerdata=new ArrayList<String>();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String date = format.format(resultSet.getDate("date"));
                innerdata.add(date);
                innerdata.add(resultSet.getString("totaltime").toString());
                innerdata.add(resultSet.getString("totalcal").toString());
                innerdata.add(resultSet.getString("totaldis").toString());
                innerdata.add(resultSet.getString("bikerecordid").toString());
                innerdata.add(resultSet.getString("fav").toString());
                innerdata.add(resultSet.getString("title").toString());
                innerdata.add(""+resultSet.getInt("pic"));
                data.add(innerdata);
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    ///////////////////////////////////////////////////////
    public void insertanalyt(String id,String name){
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query ="INSERT INTO dbo.analyst VALUES ("+id+",'"+name+"')";
            statement.executeUpdate(query);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getlastanalyt(){
        //SELECT TOP 1 * from dbo.bikerecord ORDER BY bikerecordid DESC
        Statement statement = null;
        int data=0;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "SELECT TOP 1 analystid from dbo.analyst ORDER BY analystid DESC";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data=resultSet.getInt("analystid");
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void insertanalytdetail(String insertsql){
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query ="INSERT INTO dbo.analystdetail " +" VALUES "+ insertsql;
            statement.executeUpdate(query);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getimagetite(){
        //SELECT TOP 1 * from dbo.bikerecord ORDER BY bikerecordid DESC
        Statement statement = null;
        String data="";
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "Select TOP 1 imgid  from dbo.image order by imgid DESC";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data+=(resultSet.getInt("imgid")+1);
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public void insertimage(String imgid){
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String name="image"+imgid;
            String query ="INSERT INTO dbo.image " +" VALUES "+ "('"+name+"')";
            statement.executeUpdate(query);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updatebikerecord(String id,String lastbikerecordid){
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query ="update dbo.bikerecord set pic="+id+" where bikerecordid="+lastbikerecordid;
            statement.executeUpdate(query);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertuser(String account,String code,String name,String email,String pic){
        Statement statement = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String applydate = df.format(Calendar.getInstance().getTime());
        Log.v("time",applydate);
        try {
            statement = Connection.createStatement();
            String query ="INSERT INTO dbo.[user] " +" VALUES "+ "('160','50','18','"+account+"','"+code+"','"+name+"','"+applydate+"','"+email+"',"+pic+","+0+")";
            Log.v("sql",query);
            statement.executeUpdate(query);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean addacount(String accoutin){
        //SELECT TOP 1 * from dbo.bikerecord ORDER BY bikerecordid DESC
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "SELECT * FROM dbo.[user]";
            resultSet = statement.executeQuery(query);
            String account="";
            String code="";
            while (resultSet.next()) {
                account=resultSet.getString("account");
                code=resultSet.getString("code");
                if(accoutin.equals(account)){
                    return false;
                }
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public ArrayList<ArrayList<String>> selectallbikerecordshare(){
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "select a.bikerecordid,a.pic as pic1,a.title,a.totaldis,b.name,b.pic as pic2,a.startname,a.endname from dbo.bikerecord a left join dbo.[user] b on a.userid=b.userid where a.share=1";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ArrayList<String> innerdata=new ArrayList<String>();
                innerdata.add(""+resultSet.getInt("bikerecordid"));
                innerdata.add(resultSet.getString("pic1").toString());
                innerdata.add(resultSet.getString("title").toString());
                innerdata.add(resultSet.getString("totaldis").toString());
                innerdata.add(resultSet.getString("name").toString());
                innerdata.add(resultSet.getString("pic2").toString());
                try{
                    innerdata.add(resultSet.getString("startname").toString());
                }catch (NullPointerException e){
                    innerdata.add("");
                }
                try{
                    innerdata.add(resultSet.getString("endname").toString());
                }catch (NullPointerException e){
                    innerdata.add("");
                }
                data.add(innerdata);
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<String>  getstarend(String bikerecordid){
        Statement statement = null;
        ArrayList<String> data=new ArrayList<>();
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "select startname,endname from dbo.bikerecord where bikerecordid="+bikerecordid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data.add(""+resultSet.getString("startname"));
                data.add(""+resultSet.getString("endname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public void updatestartend(String start,String end,String id){
        //userid,weather,date,totalcount,totaltime,totalcal,totaldis,avgspeed,avgalt,maxspeed,maxalt,grade,pic,fav,temp
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query ="update dbo.bikerecord set startname='"+start+"',endname='"+end+"' where bikerecordid="+id;
            statement.executeUpdate(query);

        }catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public ArrayList<String>  gettitle(String bikerecordid){
        Statement statement = null;
        ArrayList<String> data=new ArrayList<>();
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "select title from dbo.bikerecord where bikerecordid="+bikerecordid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data.add(""+resultSet.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public void updatetitle(String title,String id){
        //userid,weather,date,totalcount,totaltime,totalcal,totaldis,avgspeed,avgalt,maxspeed,maxalt,grade,pic,fav,temp
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query ="update dbo.bikerecord set title='"+title+"' where bikerecordid="+id;
            statement.executeUpdate(query);

        }catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public void updatefav(String fav,String id){
        //userid,weather,date,totalcount,totaltime,totalcal,totaldis,avgspeed,avgalt,maxspeed,maxalt,grade,pic,fav,temp
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query ="update dbo.bikerecord set fav='"+fav+"' where bikerecordid="+id;
            statement.executeUpdate(query);

        }catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public ArrayList<String>  getshare(String bikerecordid){
        Statement statement = null;
        ArrayList<String> data=new ArrayList<>();
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "select share from dbo.bikerecord where bikerecordid="+bikerecordid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data.add(""+resultSet.getInt("share"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public void updateshare(String share,String id){
        //userid,weather,date,totalcount,totaltime,totalcal,totaldis,avgspeed,avgalt,maxspeed,maxalt,grade,pic,fav,temp
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query ="update dbo.bikerecord set share="+share+" where bikerecordid="+id;
            statement.executeUpdate(query);

        }catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public void updategame(String game,String id){
        //userid,weather,date,totalcount,totaltime,totalcal,totaldis,avgspeed,avgalt,maxspeed,maxalt,grade,pic,fav,temp
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query ="update dbo.bikerecord set game="+game+" where bikerecordid="+id;
            statement.executeUpdate(query);
        }catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public ArrayList<String>  getgame(String bikerecordid){
        Statement statement = null;
        ArrayList<String> data=new ArrayList<>();
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "select game from dbo.bikerecord where bikerecordid="+bikerecordid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data.add(""+resultSet.getInt("game"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<ArrayList<String>> selectseven(String[]  place ){//String[] place
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "SELECT * from dbo.seven where address  like'%"+place[0]+"%' and place='"+place[1]+"' ";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ArrayList<String> innerdata=new ArrayList<String>();

                innerdata.add(resultSet.getString("storename").toString());
                innerdata.add(resultSet.getString("address").toString());

                data.add(innerdata);
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<ArrayList<String>> selectpolice(String[]  place ){//String[] place
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "SELECT * from dbo.police where policeaddress  like'%"+place[0]+"%' and policeaddress  like'%"+place[1]+"%'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ArrayList<String> innerdata=new ArrayList<String>();

                innerdata.add(resultSet.getString("policename").toString());
                innerdata.add(resultSet.getString("policeaddress").toString());
                innerdata.add(resultSet.getString("policephone").toString());

                data.add(innerdata);
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<ArrayList<String>> selecttravel(String[]  place ){//String[] place
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "SELECT * from dbo.travel where traveladdress  like'%"+place[0]+"%' and traveladdress  like'%"+place[1]+"%' ";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ArrayList<String> innerdata=new ArrayList<String>();

                innerdata.add(resultSet.getString("travelname").toString());
                innerdata.add(resultSet.getString("traveladdress").toString());
                innerdata.add(resultSet.getString("travelphone").toString());

                data.add(innerdata);
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<ArrayList<String>> selectshowgamebikerecord(){
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "select bikerecordid,title,totaltime,totalcal,totaldis,avgspeed,avgalt,maxspeed,maxalt,a.pic,b.name from dbo.bikerecord a Left join dbo.[user] b on a.userid=b.userid  where game=1";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ArrayList<String> innerdata=new ArrayList<String>();
                String totaltime=resultSet.getString("totaltime").toString();//00:00:06
                totaltime=totaltime.replace(":","'");
                String totaldis=resultSet.getString("totaldis").toString();//3.2868543796277834
                if(Double.parseDouble(totaldis)<0.1){//0.01
                    totaldis=""+(int)(Double.parseDouble(totaldis)*1000)+"m";
                }else{
                    DecimalFormat df = new DecimalFormat("##.0");
                    totaldis=""+Double.parseDouble(df.format(Double.parseDouble(totaldis)))+" km";
                }
                String avgspeed=resultSet.getString("avgspeed").toString();//13.416274298156843

                DecimalFormat df = new DecimalFormat("##.0");
                avgspeed=""+Double.parseDouble(df.format(Double.parseDouble(avgspeed)))+" km/hr";

                String totalcal=resultSet.getString("totalcal").toString()+" kcal";//52.3

                String maxspeed=resultSet.getString("maxspeed").toString();//28.1880220413208
                maxspeed=""+Double.parseDouble(df.format(Double.parseDouble(maxspeed)))+"km/hr";

                String maxalt=resultSet.getString("maxalt").toString()+" 公尺";//56.0

                String avgalt=resultSet.getString("avgalt").toString();//29.919621749408982
                avgalt=""+Double.parseDouble(df.format(Double.parseDouble(avgalt)))+" 公尺";

               // String temp=resultSet.getString("temp").toString();//29.84000015258789
               // temp=""+Double.parseDouble(df.format(Double.parseDouble(temp)))+"°C";

               // String grade=resultSet.getString("grade").toString();//0

               // String totalcount=resultSet.getString("totalcount").toString();//238
                String title=resultSet.getString("title").toString();
                String pic=resultSet.getString("pic").toString();
                String name=resultSet.getString("name").toString();
//totaltime,totaldis,avgspeed,totalcal,maxspeed,maxalt,avgalt,temp,grade,totalcount
                innerdata.add(totaltime);
                innerdata.add(totaldis);
                innerdata.add(avgspeed);
                innerdata.add(totalcal);
                innerdata.add(maxspeed);
                innerdata.add(maxalt);
                innerdata.add(avgalt);
                innerdata.add(title);
                innerdata.add(pic);
                innerdata.add(name);
                innerdata.add(""+resultSet.getInt("bikerecordid"));
                innerdata.add(resultSet.getString("totaldis").toString());
            //    innerdata.add(temp);
            //    innerdata.add(grade);
            //    innerdata.add(totalcount);
            //    innerdata.add(resultSet.getString("fav").toString());
                data.add(innerdata);
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public void insertrank(String bikerecordid,int userid,String totaltime,String date){
        Statement statement = null;
        try {
            statement = Connection.createStatement();

            //判斷是否是第一筆
            String query = "";
            ResultSet resultSet = null;
            query = "select count(*) as count from dbo.rank where bikerecordid="+bikerecordid;
            resultSet = statement.executeQuery(query);
            String datacount="0";
            while (resultSet.next()) {
                datacount=resultSet.getString("count");
            }
            if(Integer.parseInt(datacount)>0) {
                try {
                    String queryh = "INSERT INTO dbo.rank " + "VALUES (" + bikerecordid + "," + userid + ",'" + totaltime + "','" + date + "')";
                    statement.executeUpdate(queryh);
                }catch (SQLException e){
                    String queryh = "update dbo.rank set totaltime='"+totaltime+"' where userid="+userid+" and bikerecordid="+bikerecordid;
                    statement.executeUpdate(queryh);
                }
            }else{//如果是-->先insert自己
                ResultSet resultSet2 = null;
                query = "select bikerecordid,userid,totaltime,[date] from dbo.bikerecord where bikerecordid="+bikerecordid;
                resultSet2 = statement.executeQuery(query);
                ArrayList<String> data=new ArrayList<String>();
                while (resultSet2.next()) {
                    data.add(resultSet2.getString("bikerecordid").toString());
                    data.add(resultSet2.getString("userid").toString());
                    data.add(resultSet2.getString("totaltime").toString());
                    data.add(resultSet2.getString("date").toString());
                }
                String queryh="";
                queryh = "INSERT INTO dbo.rank " + "VALUES (" + bikerecordid + "," + userid + ",'" + totaltime + "','" + date + "')";
                statement.executeUpdate(queryh);

                queryh = "INSERT INTO dbo.rank " + "VALUES (" + data.get(0) + "," + data.get(1) + ",'" + data.get(2) + "','" + data.get(3) + "')";
                statement.executeUpdate(queryh);
            }

//            Toast.makeText(context, "goodinsert",
            //           Toast.LENGTH_LONG).show();
        }catch (SQLException e) {
            //  Toast.makeText(context, "gg",
            //         Toast.LENGTH_LONG).show();
            Log.v("finaltest","gg"+userid);
            e.printStackTrace();
        }
    }
    public ArrayList<String>  getlastgame(String bikerecordid){
        Statement statement = null;
        ArrayList<String> data=new ArrayList<>();
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "select title,weather,temp,[date],totaldis,totaltime,avgspeed from dbo.bikerecord where bikerecordid="+bikerecordid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data.add(resultSet.getString("title"));//0
                data.add(resultSet.getString("weather"));//1

                String temp=resultSet.getString("temp").toString();//29.84000015258789
                DecimalFormat df = new DecimalFormat("##.0");
                temp=""+Double.parseDouble(df.format(Double.parseDouble(temp)))+"°C";
                data.add(temp);//2
                /////////////////////////
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat format3 = new SimpleDateFormat("HH:mm:ss");
                Date dbdate=resultSet.getDate("date");
                String date=format1.format(dbdate);
                Date dbdate2=resultSet.getTime("date");
                String time=format3.format(dbdate2);
                data.add(date);//3
                data.add(time);//4
                ///////////////////////////////////////////////
                String totaldis=resultSet.getString("totaldis").toString();//3.2868543796277834
                data.add(totaldis);//5
                ////////////////
                data.add(resultSet.getString("totaltime"));//6
                ///////////
                String avgspeed=resultSet.getString("avgspeed").toString();//13.416274298156843

                avgspeed=""+(int)(Double.parseDouble(avgspeed));
                data.add(avgspeed);//7


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<ArrayList<String>> selectallrank(String bikerecordid){
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "select b.pic,b.name,a.totaltime,a.[date] from dbo.rank a left join dbo.[user] b on a.userid=b.userid where a.bikerecordid="+bikerecordid+" order by a.totaltime ASC";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ArrayList<String> innerdata=new ArrayList<String>();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String date = format.format(resultSet.getDate("date"));
                innerdata.add(date);
                innerdata.add(resultSet.getString("pic").toString());
                innerdata.add(resultSet.getString("name").toString());
                innerdata.add(resultSet.getString("totaltime").toString());
                innerdata.add(resultSet.getString("date").toString());
                data.add(innerdata);
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<ArrayList<String>> selectallbikerecordshare2(){
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "select a.bikerecordid,a.pic as pic1,a.title,a.totaldis,b.name,b.pic as pic2,a.startname,a.endname,a.totaltime from dbo.bikerecord a left join dbo.[user] b on a.userid=b.userid where a.share=1";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ArrayList<String> innerdata=new ArrayList<String>();
                innerdata.add(""+resultSet.getInt("bikerecordid"));//0
                innerdata.add(resultSet.getString("pic1").toString());//1
                innerdata.add(resultSet.getString("title").toString());//2
                innerdata.add(resultSet.getString("totaldis").toString());//3
                innerdata.add(resultSet.getString("name").toString());//4
                innerdata.add(resultSet.getString("pic2").toString());//5
                try{
                    innerdata.add(resultSet.getString("startname").toString());//6
                }catch (NullPointerException e){
                    innerdata.add("");
                }
                try{
                    innerdata.add(resultSet.getString("endname").toString());//7
                }catch (NullPointerException e){
                    innerdata.add("");
                }
                innerdata.add(resultSet.getString("totaltime").toString());//8
                data.add(innerdata);
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<ArrayList<String>>  selectfrd(String userid){
        Statement statement = null;
        /*A_userid name pic online B_userid name2 pic2 online2*/
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "select f.A_userid,u1.name,u1.pic,u1.online,f.B_userid,u2.name as name2,u2.pic as [pic2],u2.online as online2 from dbo.freind f left join dbo.[user] u1 on f.A_userid=u1.userid left join dbo.[user] u2 on f.B_userid=u2.userid where A_userid="+userid+" or  B_userid="+userid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

              //  Log.v("freind", "" + resultSet.getInt("A_userid") + resultSet.getString("name") + resultSet.getInt("pic") + resultSet.getInt("online"));
               // Log.v("freind", "" + resultSet.getInt("B_userid") + resultSet.getString("name2") + resultSet.getInt("pic2") + resultSet.getInt("online2"));
                int A_userid=resultSet.getInt("A_userid");
                int B_userid=resultSet.getInt("B_userid");
                ArrayList<String> innerdata=new ArrayList<String>();
                if(!(""+A_userid).equals(userid)){
                    innerdata.add("" + resultSet.getInt("A_userid"));//0
                    innerdata.add("" + resultSet.getString("name"));//1
                    innerdata.add("" + resultSet.getInt("pic"));//2
                    innerdata.add("" + resultSet.getInt("online"));//3
                    data.add(innerdata);
                }

                ArrayList<String> innerdata2=new ArrayList<String>();
                if(!(""+B_userid).equals(userid)){
                    innerdata2.add("" + resultSet.getInt("B_userid"));//0
                    innerdata2.add("" + resultSet.getString("name2"));//1
                    innerdata2.add("" + resultSet.getInt("pic2"));//2
                    innerdata2.add("" + resultSet.getInt("online2"));//3
                    data.add(innerdata2);
                }


                //innerdata.add("" + resultSet.getInt("analystid"));
               // innerdata.add("" + resultSet.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<ArrayList<String>>  selectsend(String userid){
        Statement statement = null;
        /*A_userid name pic online B_userid name2 pic2 online2*/
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "select userid,pic,name from dbo.[user] where userid not in (select B_userid from dbo.freind where  A_userid="+userid+")  and userid not in (select A_userid from dbo.freind where  B_userid="+userid+") and userid not in (select send_userid from dbo.send where  receive_userid="+userid+")and userid not in (select receive_userid from dbo.send where  send_userid="+userid+") and userid!="+userid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                Log.v("freind", "" + resultSet.getInt("userid") + resultSet.getString("pic") + resultSet.getString("name"));

                ArrayList<String> innerdata=new ArrayList<String>();

                    innerdata.add("" + resultSet.getInt("userid"));//0
                    innerdata.add("" + resultSet.getString("pic"));//1
                    innerdata.add("" + resultSet.getString("name"));//2
                    data.add(innerdata);

                //innerdata.add("" + resultSet.getInt("analystid"));
                // innerdata.add("" + resultSet.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public void insertsend(String userid1,String userid2){
        //bikerecordid,count,lat,lon,speed,alt,category
        // INSERT INTO dbo.bikepoint " +"VALUES"+ "("+bikerecordid+","+count+",'"+lat+"','"+lon+"','"+speed+"','"+alt+"',"+category+")
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query ="INSERT INTO dbo.send VALUES ("+userid1+","+userid2+")";
            statement.executeUpdate(query);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ArrayList<String>>  selectcheck(String userid){
        Statement statement = null;
        /*A_userid name pic online B_userid name2 pic2 online2*/
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "select f.send_userid,u1.name,u1.pic,f.receive_userid,u2.name as name2,u2.pic as [pic2] from dbo.send f left join dbo.[user] u1 on f.send_userid=u1.userid left join dbo.[user] u2 on f.receive_userid=u2.userid where send_userid="+userid+" or  receive_userid="+userid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int A_userid=resultSet.getInt("send_userid");
                int B_userid=resultSet.getInt("receive_userid");
                ArrayList<String> innerdata=new ArrayList<String>();
                if (!(""+A_userid).equals(userid)){//我是接受者
                    innerdata.add("" + resultSet.getInt("send_userid"));//0
                    innerdata.add("" + resultSet.getString("name"));//1
                    innerdata.add("" + resultSet.getInt("pic"));//2
                    innerdata.add(""+"0");
                    data.add(innerdata);
                }

                ArrayList<String> innerdata2=new ArrayList<String>();
                if(!(""+B_userid).equals(userid)){//我是發送者
                    innerdata2.add("" + resultSet.getInt("receive_userid"));//0
                    innerdata2.add("" + resultSet.getString("name2"));//1
                    innerdata2.add("" + resultSet.getInt("pic2"));//2
                    innerdata2.add("" + "1");
                    data.add(innerdata2);
                }


                //innerdata.add("" + resultSet.getInt("analystid"));
                // innerdata.add("" + resultSet.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public void insertfreiend(String userid1,String userid2){
        //bikerecordid,count,lat,lon,speed,alt,category
        // INSERT INTO dbo.bikepoint " +"VALUES"+ "("+bikerecordid+","+count+",'"+lat+"','"+lon+"','"+speed+"','"+alt+"',"+category+")
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query ="INSERT INTO dbo.freind VALUES ("+userid1+","+userid2+")";
            statement.executeUpdate(query);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deletsend(String userid1,String userid2){
        //bikerecordid,count,lat,lon,speed,alt,category
        // INSERT INTO dbo.bikepoint " +"VALUES"+ "("+bikerecordid+","+count+",'"+lat+"','"+lon+"','"+speed+"','"+alt+"',"+category+")
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query ="DELETE FROM dbo.send where send_userid="+userid1+" and receive_userid="+userid2;
            statement.executeUpdate(query);
            String query2 ="DELETE FROM dbo.send where send_userid="+userid2+" and receive_userid="+userid1;
            statement.executeUpdate(query2);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateonline(int online,String userid){
        //userid,weather,date,totalcount,totaltime,totalcal,totaldis,avgspeed,avgalt,maxspeed,maxalt,grade,pic,fav,temp
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query ="update dbo.[user] set online ="+online+" where userid="+userid;
            statement.executeUpdate(query);
            //Toast.makeText(context, "goodupdate",
            //        Toast.LENGTH_LONG).show();
        }catch (SQLException e) {
            // Toast.makeText(context, "gg",
            //        Toast.LENGTH_LONG).show();

            e.printStackTrace();
        }
    }
    public String  getuseronline(String userid){
        //SELECT TOP 1 * from dbo.bikerecord ORDER BY bikerecordid DESC
        Statement statement = null;
        int data=0;
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "SELECT online from dbo.[user] where userid="+userid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data=resultSet.getInt("online");
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
                // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ""+data;
    }
    public ArrayList<ArrayList<String>>  selectjointbike(String userid){
        Statement statement = null;
        /*A_userid name pic online B_userid name2 pic2 online2*/
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query ="select j.id,j.sentid,j.receive_id,u.pic,u.name,js.sentense,j.datetime from dbo.joinbike j left join dbo.[user] u on j.sentid=u.userid left join dbo.jointsent js on j.jointsentid=js.joinsentid  where j.receive_id="+userid+" order by j.datetime DESC";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ArrayList<String> innerdata=new ArrayList<String>();
                innerdata.add("" + resultSet.getInt("sentid"));//0v
                innerdata.add("" + resultSet.getInt("receive_id"));//1v
                innerdata.add("" + resultSet.getInt("pic"));//2v
                innerdata.add("" + resultSet.getString("name"));//3
                innerdata.add("" + resultSet.getString("sentense"));//4v

                /*~~~~~~~*/
                Date now = new Date();
              //  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date today=null;
                try {
                    today = dateFormat.parse(resultSet.getString("datetime"));
                   // DateFormat chtDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                   // System.out.println(chtDateFormat.format(today));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
               // String myDate= sdf.format(date);
               // Date find = new Date(resultSet.getString("datetime"));
                Long t1 =today.getTime();
                Long t2=now.getTime();
                Long time=t2-t1;//毫秒差

                String temp="";
                Long misec=time%1000;//豪秒差
                time=time/1000;
                Long sec=time%(60);//秒差
                time=time/60;
                Long min=time%(60);//分差
                time=time/60;
                Long hr=time%(24);//時差
                time=time/24;
                Long day=time%30;//日差
                time=time/30;
                Long month=time%12;//月差
                time=time/12;
                Long year=time%365;//年差
                time=time%365;
                String showdate="";
                if(year!=0){
                    showdate=year+"年前";
                }else if(month!=0){
                    showdate=month+"月前";
                }else if(day!=0){
                    showdate=day+"天前";
                }else if(hr!=0){
                showdate=hr+"小時前";
            }else if(min!=0){
                showdate=min+"分鐘前";
            }else if(sec!=0){
                showdate=sec+"秒前";
            }else if(misec!=0){
                showdate=misec+"數秒前";
            }

                //Long day=time%24;//日差
                Log.v("jontbike", resultSet.getInt("id")+"->("+resultSet.getDate("datetime")+")"+year + ":" + month + ":" + day + ":" + hr + ":" + min + ":" + sec + ":" + misec);

                /*~~~~~~~*/

                innerdata.add("" + showdate);//5
                innerdata.add("" + resultSet.getInt("id"));//6
                data.add(innerdata);

                //innerdata.add("" + resultSet.getInt("analystid"));
                // innerdata.add("" + resultSet.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public void deletjoinbike(String id){
        //userid,weather,date,totalcount,totaltime,totalcal,totaldis,avgspeed,avgalt,maxspeed,maxalt,grade,pic,fav,temp
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            String query ="delete from dbo.joinbike where id="+id;
            statement.executeUpdate(query);
            //Toast.makeText(context, "goodupdate",
            //        Toast.LENGTH_LONG).show();
        }catch (SQLException e) {
            // Toast.makeText(context, "gg",
            //        Toast.LENGTH_LONG).show();

            e.printStackTrace();
        }
    }
    public ArrayList<ArrayList<String>>  selectallsentence(){
        Statement statement = null;
        /*A_userid name pic online B_userid name2 pic2 online2*/
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query = "select joinsentid,sentense from dbo.jointsent";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                //  Log.v("freind", "" + resultSet.getInt("A_userid") + resultSet.getString("name") + resultSet.getInt("pic") + resultSet.getInt("online"));
                // Log.v("freind", "" + resultSet.getInt("B_userid") + resultSet.getString("name2") + resultSet.getInt("pic2") + resultSet.getInt("online2"));

                ArrayList<String> innerdata=new ArrayList<String>();
                    innerdata.add("" + resultSet.getInt("joinsentid"));//0
                    innerdata.add("" + resultSet.getString("sentense"));//1
                    data.add(innerdata);


                //innerdata.add("" + resultSet.getInt("analystid"));
                // innerdata.add("" + resultSet.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public void insertjoinbike(String user,String id,String sentid){
        //userid,weather,date,totalcount,totaltime,totalcal,totaldis,avgspeed,avgalt,maxspeed,maxalt,grade,pic,fav,temp
        Statement statement = null;
        try {
            statement = Connection.createStatement();
            Date now=new Date();
            //2015-08-05 22:41:17.000

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date=df.format(now);


            String query ="Insert into dbo.joinbike (\"sentid\",\"receive_id\",\"jointsentid\",\"datetime\") values("+user+","+id+","+sentid+",'"+date+"')";
            statement.executeUpdate(query);
            //Toast.makeText(context, "goodupdate",
            //        Toast.LENGTH_LONG).show();
        }catch (SQLException e) {
            // Toast.makeText(context, "gg",
            //        Toast.LENGTH_LONG).show();

            e.printStackTrace();
        }
    }
    public ArrayList<ArrayList<String>>  selectrank(String userid){
        Statement statement = null;
        /*A_userid name pic online B_userid name2 pic2 online2*/
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        try {
            statement = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query ="select r.userid,u.name,u.pic,b.bikerecordid,r.date,b.title,b.startname,b.endname,r.totaltime,u1.pic as pic2 from dbo.rank r  left join dbo.bikerecord b on r.bikerecordid=b.bikerecordid left join dbo.[user] u on r.userid=u.userid  left join dbo.[user] u1 on b.userid=u1.userid   where r.bikerecordid in (Select bikerecordid from dbo.bikerecord where userid="+userid+") and r.userid!="+userid;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ArrayList<String> innerdata=new ArrayList<String>();
                innerdata.add("" + resultSet.getInt("userid"));//0
                innerdata.add("" + resultSet.getString("name"));//1
                innerdata.add("" + resultSet.getInt("pic"));//2
                innerdata.add("" + resultSet.getInt("bikerecordid"));//3

                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat format3 = new SimpleDateFormat("HH:mm:ss");
                Date dbdate=resultSet.getDate("date");
                String date=format1.format(dbdate);
                Date dbdate2=resultSet.getTime("date");
                String time=format3.format(dbdate2);

                innerdata.add("" + date+" "+time);//4
                innerdata.add("" + resultSet.getString("title"));//5
                innerdata.add("" + resultSet.getString("startname"));//6
                innerdata.add("" + resultSet.getString("endname"));//7
                innerdata.add("" + resultSet.getString("totaltime"));//8
                innerdata.add("" + resultSet.getString("pic2"));//9
                data.add(innerdata);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<ArrayList<String>>  selectnav(){
        Statement statement = null;
        /*A_userid name pic online B_userid name2 pic2 online2*/
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        try {
            statement = Connection.createStatement();
            Statement statement2 = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query ="select bikerecordid,startname,endname,title, b.pic from bikerecord a left join dbo.[user] b on a.userid=b.userid  where share=1";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ArrayList<String> innerdata=new ArrayList<String>();

               // Statement statement2 = Connection.createStatement();
/*
                int now=resultSet.getInt("bikerecordid");
                Log.v("mynavigtion",""+now);
                //statement = Connection.createStatement();
                String query2 ="select lat,lon from dbo.bikepoint where count =(select totalcount-1 from dbo.bikerecord where bikerecordid="+now+") and bikerecordid="+now+" or count=0 and bikerecordid="+now;
                ResultSet resultSet2 = statement2.executeQuery(query2);
                while(resultSet2.next()){
                    Log.v("mynavigtion",now+":"+resultSet2.getString("lat")+","+resultSet2.getString("lon"));
                }
*/

                innerdata.add("" + resultSet.getInt("bikerecordid"));//0
                innerdata.add("" + resultSet.getString("startname"));//1
                innerdata.add("" + resultSet.getString("endname"));//2
                innerdata.add("" + resultSet.getString("title"));//3
                innerdata.add("" + resultSet.getString("pic"));//4
                data.add(innerdata);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<ArrayList<String>>  selecstart(String id){
        Statement statement = null;
        /*A_userid name pic online B_userid name2 pic2 online2*/
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        try {
            statement = Connection.createStatement();
            Statement statement2 = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query ="select lat,lon from dbo.bikepoint where bikerecordid="+id+" and count=0";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ArrayList<String> innerdata=new ArrayList<String>();
                innerdata.add("" + resultSet.getString("lat"));//0
                innerdata.add("" + resultSet.getString("lon"));//1
                data.add(innerdata);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ArrayList<ArrayList<String>>  selecend(String id){
        Statement statement = null;
        /*A_userid name pic online B_userid name2 pic2 online2*/
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        try {
            statement = Connection.createStatement();
            Statement statement2 = Connection.createStatement();
            String query = "";
            ResultSet resultSet = null;
            query ="select top(1) lat,lon from dbo.bikepoint where bikerecordid="+id+" order by count DESC";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ArrayList<String> innerdata=new ArrayList<String>();
                innerdata.add("" + resultSet.getString("lat"));//0
                innerdata.add("" + resultSet.getString("lon"));//1
                data.add(innerdata);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }


}
