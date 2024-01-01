package com.sever.demo.Controller;

import com.sever.demo.Base.UserInfo;
//import com.sever.demo.repository.UserRepository;
import jakarta.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sever.demo.SQLiteserver;
import com.sever.demo.request.LoginRequest;

import ch.qos.logback.classic.Logger;
@RestController
@RequestMapping ("/auth")
public class LoginController {

    Logger logger = (Logger) LoggerFactory.getLogger(LoginController.class);
    @PostMapping("/login")
    public UserInfo login(@Valid @RequestBody LoginRequest loginRequest) {
        SQLiteserver sql =SQLiteserver.getInstance();
        try{

            UserInfo result= sql.solvelogin(loginRequest.getUsername(),loginRequest.getPassword());
            logger.info("登录信息："+loginRequest.getUsername()+" "+loginRequest.getPassword());
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        
    }
    @PostMapping("/test")
    public String test(@Valid @RequestBody LoginRequest loginRequest){
        return "success "+loginRequest.getUsername();
    }
    @PostMapping("/register")
    public String regist(@Valid @RequestBody UserInfo user){
        SQLiteserver sql =SQLiteserver.getInstance();
        logger.info("注册信息："+user.getUsername()+" "+user.getPassword());
        try{
            if(sql.userExist(user)){
                return "用户名已存在";
            }
            sql.RegisterUser(user);
            return "注册成功";
        }catch(Exception e){
            e.printStackTrace();
            return "注册失败";
        }

    }

    @PostMapping("/modifyPassword")
    public String modifyPassword(@Valid @RequestBody UserInfo user){
        SQLiteserver sql =SQLiteserver.getInstance();
        try{
            if(sql.userExist(user)){
                sql.modifyPassword(user);
                return "修改成功";
            }
            return "用户名不存在";
        }catch(Exception e){
            e.printStackTrace();
            return "修改失败";
        }

    }
}
