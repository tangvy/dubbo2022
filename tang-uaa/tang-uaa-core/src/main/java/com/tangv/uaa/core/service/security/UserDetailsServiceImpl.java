/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.uaa.core.service.security;

import com.tangv.common.exception.BusinessException;
import com.tangv.uaa.core.service.UserBizService;
import com.tangv.uaa.facade.user.vo.UserVo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author tang wei
 * @version UserDetailsServiceImpl.java, v 0.1 2023/5/22 17:54 tang wei Exp $
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserBizService userBizService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVo userVo = userBizService.loadUserByUsername(username);
        if (userVo == null) {
            throw new BusinessException(500, "用户不存在!");
        }
        User userDetails = new User(userVo.getUsername(), userVo.getPassword(), new ArrayList<>());
        return userDetails;
    }
}
