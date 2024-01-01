package com.sever.demo.Controller;
//Spring Boot 新增书籍管理页面
import com.sever.demo.Base.BookType;

import io.micrometer.core.instrument.distribution.Histogram;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sever.demo.SQLiteserver;
import com.sever.demo.Base.*;
@RestController
@RequestMapping("/auth")
public class AddController {
    @PostMapping("/addbook")
    public ResponseEntity<String> addBook (@Valid @RequestBody Book book) {
        SQLiteserver sql =SQLiteserver.getInstance();
        sql.addBook(book);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @PostMapping("/addType")
    public ResponseEntity<String> addType(@Valid @RequestBody BookType type){
        SQLiteserver sql= SQLiteserver.getInstance();
//        type.setName("ceshi");
//        type.setId(100);
        sql.addType(type);

        return new ResponseEntity<String>("success",HttpStatus.OK);
    }
    @PostMapping("/addhistory")

    public ResponseEntity<String> addHistory(@Valid @RequestBody History history){
        SQLiteserver sql= SQLiteserver.getInstance();
        try{
            sql.addHistory(history);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("success",HttpStatus.OK);
    }
}
