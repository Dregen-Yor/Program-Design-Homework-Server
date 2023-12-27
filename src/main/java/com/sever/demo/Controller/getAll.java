package com.sever.demo.Controller;
import java.sql.*;
import java.util.ArrayList;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sever.demo.request.*;
import com.sever.demo.Book;
import com.sever.demo.SQLiteserver;
@RestController
@RequestMapping("/auth")
public class getAll {
    @PostMapping("/getall")
    public ArrayList<Book> getAllBook(@Valid @RequestBody String arg)throws Exception{
        SQLiteserver sql=SQLiteserver.getInstance();
        ResultSet rs=sql.SendBookInfo();
        ArrayList<Book> res=new ArrayList<Book>();
        while(rs.next()){
            String name=rs.getString("Bookname");
            int id=rs.getInt("Bookid");
            String author=rs.getString("Bookauthor");
            String address=rs.getString("Bookaddress");
            int count=rs.getInt("Bookcount");
            Book book=new Book(name,id,author,address,count);
            res.add(book);
        }
        return res;
    }
}
