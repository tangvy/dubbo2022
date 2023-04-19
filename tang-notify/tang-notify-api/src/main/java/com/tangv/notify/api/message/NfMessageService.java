/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.notify.api.message;

import com.tangv.notify.facade.message.vo.MessageVo;

/**
 * @author tang wei
 * @version NfMessageService.java, v 0.1 2023/4/19 10:12 tang wei Exp $
 */
public interface NfMessageService {

    MessageVo selectById(Long id);

}
