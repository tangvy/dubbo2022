/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.notify.core.controller;

import com.tangv.common.response.Response;
import com.tangv.notify.api.message.NfMessageService;
import com.tangv.notify.core.model.entity.NfMessage;
import com.tangv.notify.facade.message.vo.MessageVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author tang wei
 * @version NfMessageController.java, v 0.1 2023/4/18 19:28 tang wei Exp $
 */
@RestController("/Nf-message/")
public class NfMessageController {

    @DubboReference
    private NfMessageService nfMessageService;

    @GetMapping("/{id}")
    public Response<MessageVo> selectById(@PathVariable("id") Long id) {
        return Response.success(nfMessageService.selectById(id));
    }

}
