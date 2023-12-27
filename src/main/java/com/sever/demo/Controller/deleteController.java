package com.sever.demo.Controller;

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
public class deleteController {
    @PostMapping("/delete")
    public void delete (@Valid @RequestBody Book book) {
        SQLiteserver sql =SQLiteserver.getInstance();
        sql.deleteBook(book.Bookid);
    }
}
