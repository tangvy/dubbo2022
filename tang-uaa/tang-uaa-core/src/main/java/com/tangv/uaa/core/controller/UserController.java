/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.uaa.core.controller;

import com.tangv.common.response.Response;
import com.tangv.uaa.core.service.UserBizService;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/test")
    public Response test() {
        userBizService.test();
        return Response.success();
    }

}
