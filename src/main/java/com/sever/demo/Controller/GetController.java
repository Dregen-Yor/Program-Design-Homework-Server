package com.sever.demo.Controller;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sever.demo.request.*;

import io.micrometer.core.instrument.distribution.Histogram;

import com.sever.demo.Base.*;
import com.sever.demo.SQLiteserver;
@RestController
@RequestMapping ("/auth")
public class GetController {
    @PostMapping("/getbook")
    public Book getBook(@Valid  @RequestBody int id)throws Exception {
        SQLiteserver sql =SQLiteserver.getInstance();
        return sql.findBook(id);
    }
    @PostMapping("/searchUserInfo")
    public UserInfo searchUserInfo(@Valid @RequestBody int id)throws Exception{
        SQLiteserver sql =SQLiteserver.getInstance();
        UserInfo rs=sql.SendUserInfo(id);
        return rs;
    }

    @PostMapping("/searchType")
    public BookType searchType(@Valid @RequestBody int id)throws Exception{
        SQLiteserver sql =SQLiteserver.getInstance();
        BookType rs=sql.SendType(id);
        return rs;
    }
    @PostMapping("/historyExist")
    public boolean historyExist(@Valid @RequestBody Map map)throws Exception{
        SQLiteserver sql =SQLiteserver.getInstance();
        int Bookid = (int)map.get("Bookid");
        int userid = (int)map.get("userid");
        return sql.historyExist(Bookid,userid);
    }

    @PostMapping("/findhistory")
    public History findhistory(@Valid @RequestBody Map map)throws Exception{
        SQLiteserver sql =SQLiteserver.getInstance();
        int Bookid = (int)map.get("Bookid");
        int userid = (int)map.get("userid");
        return sql.findhistory(Bookid,userid);
    }
}
