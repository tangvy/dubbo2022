package com.tangv.oms.core.service;

/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */


import com.tangv.oms.facade.order.vo.OrderVo;

/**
 * @author tang wei
 * @version OmsOrderService.java, v 0.1 2023/3/26 21:10 tang wei Exp $
 */
public interface OmsOrderService {

    OrderVo queryOrderByCode(String orderCode);

}
