package com.sever.demo.Base;

import lombok.Getter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


//@Proxy(lazy = false)
public class UserInfo implements Serializable {


    private String username;

    private String password;

    private String level;
    private Integer userId;

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserInfo(String username, String password,String level,int userId) {
        this.username = username;
        this.password = password;
        this.level=level;
        this.userId=userId;
    }

    public UserInfo() {
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLevel() {
        return level;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

