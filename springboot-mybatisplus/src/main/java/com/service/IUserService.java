package com.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pojo.User;

public interface IUserService extends IService<User> {
    public User findUserByName(String userName) throws Exception;

    public String findEmail() throws Exception;

    public IPage<User> selectUserPage(Page<User> page, String name);
}
