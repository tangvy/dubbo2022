package com.tangv.oms.api.order;

/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */


import com.github.pagehelper.PageInfo;
import com.tangv.oms.facade.domain.order.dto.OrderDownloadDto;
import com.tangv.oms.facade.domain.order.vo.OrderVo;
import com.tangv.oms.facade.domain.tasks.UploadOssResult;

/**
 * @author tang wei
 * @version OmsOrderService.java, v 0.1 2023/3/26 21:10 tang wei Exp $
 */
public interface OmsOrderService {

    OrderVo queryOrderByCode(String orderCode);

    UploadOssResult downloadOrder(OrderDownloadDto orderDownloadDto);

    Integer countOrder(OrderDownloadDto orderDownloadDto);

    PageInfo<OrderVo> queryPage(OrderDownloadDto orderDownloadDto, Integer pageSize, Integer pageNum);

}
