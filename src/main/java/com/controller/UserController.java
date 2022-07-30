package com.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dao.UserDao;
import com.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping("/getById")
    public User getUserInfo(Integer userId) {
        return userDao.selectById(userId);
    }

    @RequestMapping("/getAll")
    public Page<User> getAll() {
        // Step1：创建一个 Page 对象
//        Page<User> page = new Page<>();
         Page<User> page = new Page<>(2, 3);
        // Step2：调用 mybatis-plus 提供的分页查询方法
        userDao.selectPage(page, null);
        // Step3：获取分页数据
//        System.out.println(page.getCurrent()); // 获取当前页
//        System.out.println(page.getTotal()); // 获取总记录数
//        System.out.println(page.getSize()); // 获取每页的条数
//        System.out.println(page.getRecords()); // 获取每页数据的集合
//        System.out.println(page.getPages()); // 获取总页数
//        System.out.println(page.hasNext()); // 是否存在下一页
//        System.out.println(page.hasPrevious()); // 是否存在上一页
        return page;

    }

    @RequestMapping("/getUserByAge")
    public User getUserByAge(int age) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //lambda写法:
        queryWrapper.lambda().eq(User::getAge, age);
//        queryWrapper.lambda().gt(User::getAge, 20);
//        queryWrapper.lambda().lt(User::getAge, 25);
        User user = userDao.selectOne(queryWrapper);
        return user;
    }

}