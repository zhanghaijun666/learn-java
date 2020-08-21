package com.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private int id;
    private String username;
    private String sex;
    private Date birthday;
    private String address;
}
