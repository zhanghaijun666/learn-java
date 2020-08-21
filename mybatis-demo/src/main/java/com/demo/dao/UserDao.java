package com.demo.dao;

import com.model.User;

public interface UserDao {
    User findUserById(int id);
}
