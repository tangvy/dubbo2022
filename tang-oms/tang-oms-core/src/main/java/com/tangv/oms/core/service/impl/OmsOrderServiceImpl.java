/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.oms.core.service.impl;

import com.tangv.oms.api.order.OmsOrderService;
import com.tangv.oms.core.dao.OmsOrderMapper;
import com.tangv.oms.core.model.entity.OmsOrder;
import com.tangv.oms.facade.order.vo.OrderVo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;

/**
 * @author tang wei
 * @version OmsOrderServiceImpl.java, v 0.1 2023/3/26 21:10 tang wei Exp $
 */
@DubboService
public class OmsOrderServiceImpl implements OmsOrderService {

    @Resource
    private OmsOrderMapper omsOrderMapper;

    @Override
    public OrderVo queryOrderByCode(String orderCode) {
        OrderVo orderVo = new OrderVo();
        OmsOrder omsOrder = omsOrderMapper.selectByPrimaryKey(12);
        BeanUtils.copyProperties(omsOrder, orderVo);
        return orderVo;
    }
}
