package com.tangv.oms.core.controller;

import com.tangv.common.response.Response;
import com.tangv.notify.api.message.NfMessageService;
import com.tangv.notify.facade.message.vo.MessageVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * author:   tangwei
 * Date:     2020/12/26 11:11
 * Description: 用户
 */
@RestController
public class UserController {

    @DubboReference
    private NfMessageService nfMessageService;

    @GetMapping("/get/{id}")
    public Response<MessageVo> getMsg(@PathVariable Long id) {
        MessageVo messageVo = nfMessageService.selectById(id);
        return Response.success(messageVo);
    }

}