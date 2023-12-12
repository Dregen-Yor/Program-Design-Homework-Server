package com.example;

import java.net.*;
import java.io.*;
public class TCPserver extends Thread{
    private ServerSocket serverSocket;
    private InputStream inFromserver;
    private OutputStream outToserver;
    private DataInputStream in;
    private DataOutputStream out;
    private SQLiteserver sql;
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
        sql=new SQLiteserver();
        action();
    }
    public void action(){
        try{
            inFromserver=socket.getInputStream();
            in=new DataInputStream(inFromserver);
            outToserver=socket.getOutputStream();
            out=new DataOutputStream(outToserver);
            while(true){
                String str=in.readUTF();
                // System.out.println(str);
                if(str.equals("logRequest")){
                    String username=in.readUTF();
                    String password=in.readUTF();
                    String result=sql.solvelogin(username, password);
                    out.writeUTF(result);
                }else if(str.equals("")){

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public TCPserver(){
        // run();
    }
    public static void main(String[] args) throws IOException{
        
    }
}
