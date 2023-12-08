package com.example;

import java.sql.*;
public class SQLiteserver {
    public static void main(String[] args) {
        Connection c = null;
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:data.db");
        } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}
