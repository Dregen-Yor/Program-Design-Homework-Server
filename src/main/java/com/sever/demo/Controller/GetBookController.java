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
@RequestMapping ("/auth")
public class GetBookController {
    @PostMapping("/getbook")
    public ArrayList<Book> getBook(@Valid  @RequestBody BookRequest bookRequest)throws Exception {
        SQLiteserver sql =SQLiteserver.getInstance();
        ArrayList<Book> result = new ArrayList<Book>();
        ResultSet rs=sql.SendBookInfo();
        if(bookRequest.getKind().equals("name")){
            while(rs.next()){
                String name=rs.getString("Bookname");
                if(name.indexOf(bookRequest.getInfo())!=-1){
                    int id=rs.getInt("Bookid");
                    String author=rs.getString("Bookauthor");
                    String address=rs.getString("Bookaddress");
                    int count=rs.getInt("Bookcount");
                    Book book=new Book(name,id,author,address,count);
                    result.add(book);
                }
            }
            return result;
        }
        else{
            while(rs.next()){
                int ID=Integer.parseInt(bookRequest.getInfo());
                int id=rs.getInt("Bookid");
                if(id==ID){
                    String name=rs.getString("Bookname");
                    String author=rs.getString("Bookauthor");
                    String address=rs.getString("Bookaddress");
                    int count=rs.getInt("Bookcount");
                    Book book=new Book(name,id,author,address,count);
                    result.add(book);
                }
            }
            return result;
        }
    }
}
