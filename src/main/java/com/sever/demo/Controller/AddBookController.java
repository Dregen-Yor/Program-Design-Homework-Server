package com.sever.demo.Controller;
//Spring Boot 新增书籍管理页面
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sever.demo.SQLiteserver;
import com.sever.demo.Book;
@RestController
@RequestMapping("/auth")
public class AddBookController {
    @PostMapping("/addbook")
    public ResponseEntity<String> addBook (@Valid @RequestBody Book book) {
        SQLiteserver sql =SQLiteserver.getInstance();
        sql.addBook(book);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }
}
