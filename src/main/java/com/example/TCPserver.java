package com.example;

import java.net.*;
import java.io.*;
public class TCPserver extends Thread{
    private ServerSocket serverSocket;
    public Socket socket;
    public TCPserver(int port)throws IOException{
        serverSocket=new  ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }
    public void run(){
        while(true){
            try{
                socket=serverSocket.accept();
                System.out.println("连接成功");
                break;
            }
            catch(SocketTimeoutException e){
                System.out.println("连接超时");
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws IOException{
        
    }
}
