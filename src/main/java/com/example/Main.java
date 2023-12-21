package com.example;

import java.io.IOException;
import java.net.*;
import java.sql.Connection;
public class Main {
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket=new ServerSocket(1503);
         while(true){
            try{
                
                Socket socket=serverSocket.accept();
                System.out.println("连接成功");
                new Thread(new TCPserver(socket)).start();
            }
            catch(SocketTimeoutException e){
                System.out.println("连接超时");
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}