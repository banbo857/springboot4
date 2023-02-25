package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseMapper<User> {
}
