package com.demo.mapper;

import com.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int countByExample(User example);

    int deleteByExample(User example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(User example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") User example);

    int updateByExample(@Param("record") User record, @Param("example") User example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
