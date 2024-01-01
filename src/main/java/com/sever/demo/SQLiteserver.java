package com.sever.demo;

import com.sever.demo.Base.Book;
import com.sever.demo.Base.History;
import com.sever.demo.Base.BookType;
import com.sever.demo.Base.UserInfo;

import java.sql.*;
import java.util.ArrayList;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;

public class SQLiteserver {
    private Connection c;
    private Statement stmt;
    private static SQLiteserver instance=new SQLiteserver();
    public UserInfo solvelogin(String username, String password) throws SQLException {
        stmt.close();
        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user;");
        while(rs.next()){
            String name=rs.getString("username");
            String pass=rs.getString("password");
            if(name.equals(username)&&pass.equals(password)){
                return new UserInfo(name,pass,rs.getString("level"),rs.getInt("userId"));
            }
        }
        return null; 
    }
    public ResultSet SendBookInfo()throws SQLException{
        stmt.close();
        stmt = c.createStatement();
        ResultSet rs= stmt.executeQuery("SELECT * FROM book;");
        return rs;
    }
    public void addBook(Book book){
        String updata="INSERT INTO Book (Bookname,Bookauthor,Bookaddress,Bookcount,BookType) VALUES ('"+book.getBookname()+"', '"+book.getBookauthor()+"', '"+book.getBookaddress()+"', "+book.getBookcount()+", "+book.getBookType()+" );";
        try{
            stmt.executeUpdate(updata);
            stmt.close();
            c.commit();
            stmt = c.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public void deleteBook(int bookid){
        String updata="DELETE from Book where Bookid= "+bookid+" ;";
        System.out.println("bookid"+bookid);
        try{
            stmt.executeUpdate(updata);
            updata= "UPDATE Book SET 'Bookid'=(Bookid-1) WHERE Bookid>"+bookid+";";
            stmt.executeUpdate(updata);
            stmt.close();
            c.commit();
            stmt = c.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Book findBook(int id)throws SQLException{
        stmt.close();
        stmt = c.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM Book where Bookid="+id+";");
        while(rs.next()){
            String name=rs.getString("Bookname");
            String author=rs.getString("Bookauthor");
            String address=rs.getString("Bookaddress");
            int count=rs.getInt("Bookcount");
            int type= rs.getInt("BookType");
            Book book=new Book(name,id,author,address,count,type);
            return book;
        }
        return null;
    }

    public void RegisterUser(UserInfo user){
        String update="INSERT INTO user (username,password,level) VALUES ('"+user.getUsername()+"', '"+user.getPassword()+"', '"+user.getLevel()+"' );" ;
        try{
            stmt.executeUpdate(update);
            stmt.close();
            c.commit();
            stmt=c.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean userExist(UserInfo user)throws SQLException{
        stmt.close();
        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user;");
        while(rs.next()){
            String name=rs.getString("username");
            if(name.equals(user.getUsername())){
                return true;
            }
        }
        return false;
    }


    public boolean typeExist(BookType type)throws SQLException{
        stmt.close();
        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Type;");
        while(rs.next()){
            String name=rs.getString("Name");
            if(name.equals(type.getname())){
                return true;
            }
        }
        return false;
    }

   public void addType(BookType type){
        try{
            String updata="INSERT INTO Type (Name) VALUES ('"+type.getname()+"' "+");";
            stmt.executeUpdate(updata);
            stmt.close();
            c.commit();
            stmt = c.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public ArrayList<UserInfo> getAlluser()throws Exception{
        stmt.close();
        stmt = c.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM user;");
        ArrayList<UserInfo> res=new ArrayList<UserInfo>();
        while(rs.next()){
            String name=rs.getString("username");
            String pass=rs.getString("password");
            String level=rs.getString("level");
            int id=rs.getInt("userId");
            UserInfo user=new UserInfo(name,pass,level,id);
            res.add(user);
        }
        return res;
    }

    public ArrayList<BookType> getallType()throws Exception{
        stmt.close();
        stmt = c.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM Type;");
        ArrayList<BookType> res=new ArrayList<BookType>();
        while(rs.next()){
            String name=rs.getString("Name");
            int id=rs.getInt("Id");
            BookType type=new BookType(name,id);
            res.add(type);
        }
        return res;
    }


    public void modifyHistory(History history)throws Exception{
        String update="UPDATE history set status='"+history.getStatus()+"' where id="+history.getId()+";";    
        stmt.executeUpdate(update);
        stmt.close();
        c.commit();
        stmt = c.createStatement();
    }

    public void modifyPassword(UserInfo user)throws Exception{
        String update="UPDATE user set password='"+user.getPassword()+"' where userId="+user.getUserId()+";";    
        stmt.executeUpdate(update);
        stmt.close();
        c.commit();
        stmt = c.createStatement();
    }

    public void modifyBook(Book book)throws SQLException{
        String update="UPDATE Book set Bookname='"+book.getBookname()+"',Bookauthor='"+book.getBookauthor()+"',Bookaddress='"+book.getBookaddress()+"',Bookcount="+book.getBookcount()+",BookType="+book.getBookType()+" where Bookid="+book.getBookid()+";";    
        stmt.executeUpdate(update);
        stmt.close();
        c.commit();
        stmt = c.createStatement();
    }

    public ArrayList<History> getAllHistory()throws Exception{
        stmt.close();
        stmt = c.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM history;");
        ArrayList<History> res=new ArrayList<History>();
        while(rs.next()){
            int user=rs.getInt("user");
            String time=rs.getString("time");
            int  bookid=rs.getInt("book");
            String status=rs.getString("status");
            int id=rs.getInt("id");
            History history=new History(user,time,bookid,status,id);
            res.add(history);
        }
        return res;
    }

    public UserInfo SendUserInfo(int id){
        try{
            stmt.close();
            stmt = c.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM user where userId="+id+";");
            while(rs.next()){
                String name=rs.getString("username");
                String pass=rs.getString("password");
                String level=rs.getString("level");
                UserInfo user=new UserInfo(name,pass,level,id);
                return user;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void modifyType(BookType type)throws SQLException{
        String update="UPDATE Type set Name='"+type.getname()+"' where Id="+type.getid()+";";    
        stmt.executeUpdate(update);
        stmt.close();
        c.commit();
        stmt = c.createStatement();
    }

    public BookType SendType(int id){
        try{
            stmt.close();
            stmt = c.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM Type where Id="+id+";");
            while(rs.next()){
                String name=rs.getString("Name");
                BookType type=new BookType(name,id);
                return type;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean historyExist(int Bookid ,int user){
        try{
            stmt.close();
            stmt = c.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM history where book="+Bookid+" and user="+user+";");
            while(rs.next()){
                String status=rs.getString("status");
                if(status.equals("借阅中")){
                    return true;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void addHistory(History history)throws SQLException{
        String updata="INSERT INTO history (time,book,status,user) VALUES ('"+history.getTime()+"', "+history.getBook()+", '"+history.getStatus()+"', "+history.getUser()+" );";
        stmt.executeUpdate(updata);
        stmt.close();
        c.commit();
        stmt = c.createStatement();
    }

    public History findhistory(int Bookid ,int user)throws SQLException{
        try{
            stmt.close();
            stmt = c.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM history where book="+Bookid+" and user="+user+";");
            while(rs.next()){
                String status=rs.getString("status");
                if(status.equals("借阅中")){
                    String time=rs.getString("time");
                    int id=rs.getInt("id");
                    History history=new History(user,time,Bookid,status,id);
                    return history;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
    void run() {
        try {
            Class.forName("org.sqlite.JDBC");
            // c = DriverManager.getConnection("jdbc:sqlite:data.db");
            c = DriverManager.getConnection("jdbc:sqlite:/teach/run/data.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
        } catch (Exception e) {
            // System.err.println(e.getClass().getName() + ": " + e.getMessage());
            // System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
    SQLiteserver(){
        run();
    }
    public static SQLiteserver getInstance(){
        return instance;
    }
}
