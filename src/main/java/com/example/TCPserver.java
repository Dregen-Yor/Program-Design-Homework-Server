package com.example;

import java.net.*;
import java.io.*;
import java.sql.*;
public class TCPserver extends Thread{
    private ServerSocket serverSocket;
    private InputStream inFromserver;
    private OutputStream outToserver;
    private DataInputStream in;
    private DataOutputStream out;
    private SQLiteserver sql;
    public Socket socket;
    private ObjectOutputStream writer;
    private ObjectInputStream reader;
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
            writer=new ObjectOutputStream(out);
            reader=new ObjectInputStream(inFromserver);
            while(true){
                String str=in.readUTF();
                // System.out.println(str);
                if(str.equals("logRequest")){
                    String username=in.readUTF();
                    String password=in.readUTF();
                    String result=sql.solvelogin(username, password);
                    out.writeUTF(result);
                }else if(str.equals("getBookInfo")){
                    ResultSet rs=sql.SendBookInfo();
                    while(rs.next()){
                        String name=rs.getString("Bookname");
                        String address=rs.getString("Bookaddress");
                        String author=rs.getString("Bookauthor");
                        int id=rs.getInt("Bookid");
                        int count=rs.getInt("Bookcount");
                        Book book=new Book(name, id, author, address, count);
                        System.out.println(book);
                        sendObject(book);
                    }
                    sendObject(null);
                }else if(str.equals("commitInfo")){

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public void sendObject(Object message)throws IOException{
        writer.writeObject(message);
    }
    public void sendMessage(String message)throws IOException{
        out.writeUTF(message);
    }
    public Object getObject()throws IOException,ClassNotFoundException{
        return reader.readObject();
    }
    public String getMessage()throws IOException{
        return in.readUTF();
    }
    public TCPserver(){
        // run();
    }
    public static void main(String[] args) throws IOException{
        
    }
}
