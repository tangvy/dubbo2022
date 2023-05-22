/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.uaa.core.controller;

import com.tangv.common.response.Response;
import com.tangv.uaa.core.service.UserBizService;
import com.tangv.uaa.facade.user.vo.UserVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/{user_id}")
    public Response<UserVo> loadById(@PathVariable("user_id") Long userid) {
        UserVo userVo = userBizService.loadById(userid);
        return Response.success(userVo);
    }
}
