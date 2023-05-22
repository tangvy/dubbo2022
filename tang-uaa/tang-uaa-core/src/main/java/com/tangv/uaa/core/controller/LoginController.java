/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.uaa.core.controller;

import com.tangv.common.response.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tang wei
 * @version LoginController.java, v 0.1 2023/5/22 19:10 tang wei Exp $
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping("/success")
    public Response loginSuccess() {
        return Response.success("login success");
    }
}
