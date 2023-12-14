package com.example;

import java.sql.*;
public class SQLiteserver {
    private Connection c;
    private Statement stmt;
    //返回字符串 "true" 表示登录成功
    public String solvelogin(String username, String password) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM user;");
        while(rs.next()){
            String name=rs.getString("username");
            String pass=rs.getString("password");
            // System.out.println(name+" "+pass);
            if(name.equals(username)&&pass.equals(password)){
                return "true";
            }
        }
        return "false"; 
    }
    public ResultSet SendBookInfo()throws SQLException{
        ResultSet rs= stmt.executeQuery("SELECT * FROM book;");
        return rs;
    }
    void run() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:data.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            // System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
    SQLiteserver(){
        run();
    }
    public static void main(String[] args) {
        
    }
}
