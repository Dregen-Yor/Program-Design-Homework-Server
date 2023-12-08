package com.example;

import java.io.IOException;
import java.sql.Connection;
public class Main {
    public static void main(String[] args) {
        try {
            TCPserver s = new TCPserver(1503);
            s.run();
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }
}