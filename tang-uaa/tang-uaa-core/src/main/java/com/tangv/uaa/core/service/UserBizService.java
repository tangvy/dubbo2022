/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.uaa.core.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tangv.uaa.api.user.UserService;
import com.tangv.uaa.core.dao.TAuthMapper;
import com.tangv.uaa.core.dao.TRoleAuthMapper;
import com.tangv.uaa.core.dao.TRoleMapper;
import com.tangv.uaa.core.dao.TUserMapper;
import com.tangv.uaa.core.dao.TUserRoleMapper;
import com.tangv.uaa.core.model.entity.TAuth;
import com.tangv.uaa.core.model.entity.TRole;
import com.tangv.uaa.core.model.entity.TRoleAuth;
import com.tangv.uaa.core.model.entity.TUser;
import com.tangv.uaa.core.model.entity.TUserRole;
import com.tangv.uaa.facade.user.vo.AuthVo;
import com.tangv.uaa.facade.user.vo.UserVo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author tang wei
 * @version UserBizService.java, v 0.1 2023/5/22 12:16 tang wei Exp $
 */
@DubboService
public class UserBizService implements UserService {

    @Resource
    private TUserMapper tUserMapper;

    @Resource
    private TUserRoleMapper tUserRoleMapper;

    @Resource
    private TRoleAuthMapper tRoleAuthMapper;

    @Resource
    private TAuthMapper tAuthMapper;

    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public UserVo loadUserByUsername(String username) {
        TUser user = tUserMapper.selectOne(Wrappers.lambdaQuery(TUser.class).eq(TUser::getUsername, username));
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
        TUser user = tUserMapper.selectById(userId);
        if (user == null) {
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        redisTemplate.opsForValue().set("user:" + userId, userVo, 60, TimeUnit.SECONDS);
        return userVo;
    }

    @Override
    public List<AuthVo> loadByUserId(Long userId) {
        List<TUserRole> tUserRolesList = tUserRoleMapper.selectList(Wrappers.lambdaQuery(TUserRole.class).eq(TUserRole::getUserId, userId).eq(TUserRole::getDeleted, false));
        if (CollectionUtil.isEmpty(tUserRolesList)) {
            return new ArrayList<>();
        }
        List<Long> roleIdlist = tUserRolesList.stream().map(TUserRole::getRoleId).collect(Collectors.toList());
        List<TRoleAuth> tRoleAuthsList = tRoleAuthMapper.selectList(Wrappers.lambdaQuery(TRoleAuth.class).in(TRoleAuth::getRoleId, roleIdlist).eq(TRoleAuth::getDeleted, false));
        if (CollectionUtil.isEmpty(tRoleAuthsList)) {
            return new ArrayList<>();
        }
        List<Long> roleAuthIdList = tRoleAuthsList.stream().map(TRoleAuth::getAuthId).collect(Collectors.toList());
        List<TAuth> tAuthList = tAuthMapper.selectBatchIds(roleAuthIdList);
        if (CollectionUtil.isEmpty(tAuthList)) {
            return new ArrayList<>();
        }
        List<AuthVo> authVoList = tAuthList.stream().map(auth -> {
            AuthVo authVo = new AuthVo();
            BeanUtils.copyProperties(auth, authVo);
            return authVo;
        }).collect(Collectors.toList());
        return authVoList;
    }

    @Override
    public List<String> loadAuthByUserId(Long userId) {
        List<AuthVo> authVoList = loadByUserId(userId);
        return authVoList.stream().map(AuthVo::getAuthCode).collect(Collectors.toList());
    }

}
