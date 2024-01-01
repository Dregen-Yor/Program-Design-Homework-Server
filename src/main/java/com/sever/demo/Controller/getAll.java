package com.sever.demo.Controller;
import java.sql.*;
import java.util.ArrayList;

import com.sever.demo.Base.*;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sever.demo.SQLiteserver;
@RestController
@RequestMapping("/auth")
public class getAll {
    @GetMapping("/getallBook")
    public ArrayList<Book> getAllBook()throws Exception{
        SQLiteserver sql=SQLiteserver.getInstance();
        ResultSet rs=sql.SendBookInfo();
        ArrayList<Book> res=new ArrayList<Book>();
        while(rs.next()){
            String name=rs.getString("Bookname");
            int id=rs.getInt("Bookid");
            String author=rs.getString("Bookauthor");
            String address=rs.getString("Bookaddress");
            int count=rs.getInt("Bookcount");
            int type= rs.getInt("BookType");
            Book book=new Book(name,id,author,address,count,type);
            res.add(book);
        }
        return res;
    }
    @GetMapping("/getallUser")
    public ArrayList<UserInfo> getAlluser()throws Exception{
        SQLiteserver sql=SQLiteserver.getInstance();
        return sql.getAlluser();
    }

    @GetMapping("/getallType")
    public ArrayList<BookType> getAllType()throws Exception{
        SQLiteserver sql=SQLiteserver.getInstance();
        return sql.getallType();
    }

    @GetMapping("/getallHistory")
    public ArrayList<History> getAllHistory()throws Exception{
        SQLiteserver sql=SQLiteserver.getInstance();
        return sql.getAllHistory();
    }
}
