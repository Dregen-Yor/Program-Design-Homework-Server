package com.sever.demo.Controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sever.demo.SQLiteserver;
import com.sever.demo.request.LoginRequest;
@RestController
@RequestMapping ("/auth")
public class LoginController {

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest loginRequest) {
        SQLiteserver sql =SQLiteserver.getInstance();
        try{
            return sql.solvelogin(loginRequest.getUsername(),loginRequest.getPassword());
        }catch(Exception e){
            e.printStackTrace();
            return "error";
        }
        
    }
    @PostMapping("/test")
    public String test(@Valid @RequestBody LoginRequest loginRequest){
        return "success "+loginRequest.getUsername();
    }
}
