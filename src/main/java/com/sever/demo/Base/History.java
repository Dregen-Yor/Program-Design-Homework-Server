package com.sever.demo.Base;

import jakarta.persistence.*;

import java.io.Serializable;

import org.hibernate.annotations.Proxy;
import org.springframework.data.relational.core.sql.In;

//@Proxy(lazy = false)
//@Entity
//@Table(name= "history")
public class History implements Serializable{

    private int user;
    private String time;
    private Integer book;
    private String status;
    private Integer id;

    public History(int user,String time,Integer book,String status){
        this.user=user;
        this.time=time;
        this.book=book;
        this.status=status;
    }

    public History(int user,String time,Integer book,String status,Integer id){
        this.user=user;
        this.time=time;
        this.book=book;
        this.status=status;
        this.id=id;
    }
    public History(){

    }
    public int getUser() {
        return user;
    }

    public String getTime() {
        return time;
    }

    public Integer getBook() {
        return book;
    }

    public String getStatus() {
        return status;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setBook(Integer book) {
        this.book = book;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
