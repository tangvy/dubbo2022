/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.uaa.core.controller;

import com.tangv.common.response.Response;
import com.tangv.uaa.core.service.UserBizService;
import com.tangv.uaa.facade.user.vo.UserVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tang wei
 * @version UserController.java, v 0.1 2023/5/22 12:21 tang wei Exp $
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserBizService userBizService;

    @PreAuthorize("hasAnyAuthority('query_user_auth')")
    @GetMapping("/{user_id}")
    public Response<UserVo> loadById(@PathVariable("user_id") Long userid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        Object details = authentication.getDetails();
        authentication.getCredentials();
        UserVo userVo = userBizService.loadById(userid);
        return Response.success(userVo);
    }

    @GetMapping("/username/{username}")
    public Response<UserVo> loadByName(@PathVariable("username") String username) {
        UserVo userVo = userBizService.loadUserByUsername(username);
        return Response.success(userVo);
    }
}
