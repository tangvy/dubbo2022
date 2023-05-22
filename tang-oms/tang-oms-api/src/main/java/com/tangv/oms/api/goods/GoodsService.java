/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.oms.api.goods;

import com.github.pagehelper.PageInfo;
import com.tangv.oms.facade.goods.dto.GoodsSearchDTO;
import com.tangv.oms.facade.goods.vo.GoodsVo;

import java.util.Collection;

/**
 * @author tang wei
 * @version GoodsService.java, v 0.1 2023/3/27 20:39 tang wei Exp $
 */
public interface GoodsService {

    void insertGoods(GoodsVo goods);

    void stackingGoods(Collection<Long> goodsIds);

    PageInfo<GoodsVo> getPage(GoodsSearchDTO goodsSearchDTO);
}
