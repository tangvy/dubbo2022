package com.tangv.oms.facade.domain.goods.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tangv.common.base.entity.BasePojo;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * author:   tangwei
 * Date:     2020/12/24 11:00
 * Description: 商品
 */
@Data
@TableName(value = "goods")
public class GoodsVo extends BasePojo {

    private String goodsCode;

    private String goodsName;

    private Boolean online;

    private LocalDateTime stackingTime;
}