package com.course.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String userName;
    private String password;
    private int sex;
    private int age;
    private int permission;
    private int isDelete;
    private String expected;

    public String toString(){
        return (
                "{id:" + id + "," +
                "userName:" + userName + "," +
                "password:" + password + "," +
                "sex:" + sex + "," +
                "age:" + age + "," +
                "permission:" + permission + "," +
                "isDelete:" + isDelete + "," +
                "expected:" + expected + "}"
                );
    }
}
