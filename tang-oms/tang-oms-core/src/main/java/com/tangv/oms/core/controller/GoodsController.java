package com.tangv.oms.core.controller;

import com.github.pagehelper.PageInfo;
import com.tangv.common.enums.DataBaseType;
import com.tangv.common.response.Response;
import com.tangv.oms.core.config.DataSourceHolder;
import com.tangv.oms.core.service.GoodsServiceImpl;
import com.tangv.oms.facade.goods.dto.GoodsSearchDTO;
import com.tangv.oms.facade.goods.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author:   tangwei
 * Date:     2020/12/24 11:29
 * Description:
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsServiceImpl goodsService;

    @PostMapping("/insert")
    public Response insert(@RequestBody GoodsVo goods) {
        DataSourceHolder.setDatasources(DataBaseType.TANG_FEATURE1);
        goodsService.insertGoods(goods);
        return Response.success();
    }

    @PostMapping("/goodsPage")
    public Response<PageInfo<GoodsVo>> getPage(@RequestBody GoodsSearchDTO goodsSearchDTO) {
        return Response.success(goodsService.getPage(goodsSearchDTO));
    }

}