/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.notify.core.service.impl;

import com.tangv.common.response.Response;
import com.tangv.notify.api.message.NfMessageService;
import com.tangv.notify.core.model.entity.NfMessage;
import com.tangv.notify.facade.message.vo.MessageVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

/**
 * @author tang wei
 * @version NfMessageServiceImpl.java, v 0.1 2023/4/18 19:36 tang wei Exp $
 */
@DubboService
public class NfMessageServiceImpl implements NfMessageService {

    @Override
    public MessageVo selectById(Long id) {
        MessageVo nfMessage = new MessageVo();
        nfMessage.setMsgId(id);
        nfMessage.setGmtCreate(new Date());
        nfMessage.setGmtModify(new Date());
        return nfMessage;
    }

}
