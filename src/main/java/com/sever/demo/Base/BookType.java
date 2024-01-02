package com.sever.demo.Base;

import java.io.Serializable;

public class BookType implements Serializable{
    private String name;
    private Integer id;
    public BookType(String name,Integer id){
        this.name=name;
        this.id=id;
    }
    public BookType(){

    }
    public String getname() {
        return name;
    }

    public Integer getid() {
        return id;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void setid(Integer id) {
        this.id = id;
    }

}
