package com.example;

import java.sql.*;
public class SQLiteserver {
    private Connection c;
    private Statement stmt;
    //返回字符串 "true" 表示登录成功
    public String solvelogin(String username, String password) throws SQLException {
        stmt.close();
        stmt = c.createStatement();
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
        stmt.close();
        stmt = c.createStatement();
        ResultSet rs= stmt.executeQuery("SELECT * FROM book;");
        return rs;
    }
    public void addBook(Book book){
        String updata="INSERT INTO Book (Bookname,Bookid,Bookauthor,Bookaddress,Bookcount) VALUES ('"+book.Bookname+"', "+book.Bookid+", '"+book.Bookauthor+"', '"+book.Bookaddress+"', "+book.Bookcount+" );";
        try{
            stmt.executeUpdate(updata);
            stmt.close();
            c.commit();
            stmt = c.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
        
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
