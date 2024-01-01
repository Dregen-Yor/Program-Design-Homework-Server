package com.sever.demo.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sever.demo.SQLiteserver;
import com.sever.demo.Base.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")

public class modifyController {
    @PostMapping("/modifybook")
    public String modifyBook (@Valid @RequestBody Book book)throws Exception {
        SQLiteserver sql= SQLiteserver.getInstance();
        sql.modifyBook(book);
        return "success";
    }
    @PostMapping("modifyhistory")
    public String modifyHistory(@Valid @RequestBody History history)throws Exception{
        SQLiteserver sql= SQLiteserver.getInstance();
        sql.modifyHistory(history);
        return "success";
    }
    @PostMapping("modifyType")
    public String modifyType(@Valid @RequestBody BookType type)throws Exception{
        SQLiteserver sql= SQLiteserver.getInstance();
        sql.modifyType(type);
        return "success";
    }
}
