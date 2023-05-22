package com.tangv.oms.core.service;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangv.common.enums.CodeType;
import com.tangv.common.util.DateUtil;
import com.tangv.common.util.NumUtil;
import com.tangv.oms.api.goods.GoodsService;
import com.tangv.oms.core.consts.RedisConst;
import com.tangv.oms.core.dao.GoodsMapper;
import com.tangv.oms.core.model.entity.Goods;
import com.tangv.oms.facade.goods.dto.GoodsSearchDTO;
import com.tangv.oms.facade.goods.vo.GoodsVo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * author:   tangwei
 * Date:     2020/12/24 11:30
 * Description:
 */
@DubboService
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    public void insertGoods(GoodsVo goods) {
        goods.setGoodsCode(NumUtil.getCode(CodeType.GOODS_CODE));
        goods.setStackingTime(LocalDateTime.now());
        //goodsMapper.insertSelective(goods);
        redisTemplate.opsForZSet().add(
                RedisConst.GOODS_OFFLINE,goods.getId(), DateUtil.getTimeStemp(goods.getStackingTime().plusMinutes(5L)));
    }

    @Transactional(rollbackFor = Exception.class)
    public void stackingGoods(Collection<Long> goodsIds) {
        if (CollectionUtil.isNotEmpty(goodsIds)) {
            goodsMapper.switchOnline(Boolean.FALSE,goodsIds,LocalDateTime.now());
        }
    }

    public PageInfo<GoodsVo> getPage(GoodsSearchDTO goodsSearchDTO) {
        /*Page<Goods> page = PageHelper.startPage(goodsSearchDTO.getPage(), goodsSearchDTO.getSize());
        goodsMapper.getGoodsList(goodsSearchDTO);*/
        PageInfo<GoodsVo> goodsPageInfo = new PageInfo<>();
        return goodsPageInfo;
    }
}