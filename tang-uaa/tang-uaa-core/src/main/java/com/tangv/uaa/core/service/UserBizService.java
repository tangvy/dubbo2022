/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.uaa.core.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tangv.uaa.api.user.UserService;
import com.tangv.uaa.core.dao.UserMapper;
import com.tangv.uaa.core.model.entity.User;
import com.tangv.uaa.facade.user.vo.UserVo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @author tang wei
 * @version UserBizService.java, v 0.1 2023/5/22 12:16 tang wei Exp $
 */
@DubboService
public class UserBizService implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public UserVo loadUserByUsername(String username) {
        User user = userMapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getUsername, username));
        if (user == null) {
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    @Override
    public UserVo loadById(Long userId) {
        String key = "user:" + userId;
        UserVo userVoCache = (UserVo) redisTemplate.opsForValue().get(key);
        if (userVoCache != null) {
            return userVoCache;
        }
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        redisTemplate.opsForValue().set("user:" + userId, userVo, 60, TimeUnit.SECONDS);
        return userVo;
    }

}
