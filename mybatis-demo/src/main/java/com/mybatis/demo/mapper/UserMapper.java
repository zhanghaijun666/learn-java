package com.mybatis.demo.mapper;

import com.mybatis.model.User;

public interface UserMapper {

    User selectByPrimaryKey(Integer id);

}
