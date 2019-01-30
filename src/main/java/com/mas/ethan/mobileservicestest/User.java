package com.mas.ethan.mobileservicestest;

import com.google.firebase.*;

public class User {
    private String userId;
    private String name;
    private String favFood;

    public User(){

    }

    public User(String id, String name, String favFood){
        this.userId = id;
        this.name = name;
        this.favFood = favFood;
    }

    public String getUserId(){
        return  this.userId;
    }


    public String getName(){
        return  this.name;
    }

    public String getFavFood(){
        return  this.favFood;
    }

}
