package com.mybatis.demo.dao;

import com.mybatis.model.User;

public interface UserDao {
    User findUserById(int id);
}
