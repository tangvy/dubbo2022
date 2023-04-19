/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.oms.core.service;

import com.github.pagehelper.PageInfo;
import com.tangv.oms.core.model.dto.GoodsSearchDTO;
import com.tangv.oms.core.model.entity.Goods;

import java.util.Collection;

/**
 * @author tang wei
 * @version GoodsService.java, v 0.1 2023/3/27 20:39 tang wei Exp $
 */
public interface GoodsService {

    void insertGoods(Goods goods);

    void stackingGoods(Collection<Long> goodsIds);

    PageInfo<Goods> getPage(GoodsSearchDTO goodsSearchDTO);
}
