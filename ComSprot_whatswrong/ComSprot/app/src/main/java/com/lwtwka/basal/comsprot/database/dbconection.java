package com.lwtwka.basal.comsprot.database;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by leo on 2015/7/30.
 */
public class dbconection {//163.14.68.47 basal basal
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String urlDriver = "jdbc:sqlserver";
    String SQLserverName = "163.14.68.47";
    String port = "1433";
    String databaseName = "basal";
    String url = urlDriver + "://" + SQLserverName + ":" + port + ";databaseName=" + databaseName;
    String dbUser = "basal";
    String dbPass = "basal";
    public dbconection() {
        // Load driver


        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }
    public void connect(){
/*
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://163.14.68.47;"
                    + "databaseName=basal;user=basal;password=basal;");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        try {
            connection = DriverManager.getConnection(url, dbUser, dbPass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void dbclose(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<ArrayList<String>> testquery(){
        String query = "";
        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
        try {
            statement = connection.createStatement();
            query = "SELECT * FROM dbo.test_Lu";

            // Execute the query
            resultSet = statement.executeQuery(query);

            // Loops through ALL rows and retrieves Report ID and JOB ID information
            while (resultSet.next()) {
                Log.v("medb",resultSet.getObject("a").toString()+resultSet.getObject("b").toString());
                ArrayList<String> innerdata=new ArrayList<String>();
                innerdata.add(resultSet.getObject("a").toString());
                innerdata.add(resultSet.getObject("b").toString());
                data.add(innerdata);
                //System.out.print("Report ID: " + resultSet.getObject("REPORTID").toString());
               // System.out.print(" correlates with JOB ID: " + resultSet.getObject("JOBID").toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(query);
        }
        return data;
    }



}
