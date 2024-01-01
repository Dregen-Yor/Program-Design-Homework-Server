package com.sever.demo.Controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sever.demo.SQLiteserver;
import com.sever.demo.Base.Book;
@RestController
@RequestMapping("/auth")
public class deleteController {
    @PostMapping("/deletebook")
    public void delete (@Valid @RequestBody Book book) {
        SQLiteserver sql =SQLiteserver.getInstance();
        System.out.println(book.getBookname());
        sql.deleteBook(book.getBookid());
    }
}
