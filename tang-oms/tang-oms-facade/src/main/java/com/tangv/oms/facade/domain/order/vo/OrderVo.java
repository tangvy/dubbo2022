/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.oms.facade.domain.order.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author tang wei
 * @version OrderVo.java, v 0.1 2023/4/18 20:04 tang wei Exp $
 */
@Data
public class OrderVo implements Serializable {

    @ExcelProperty(value = "ID", order = 1)
    private Integer id;

    @ExcelProperty(value = "货主", order = 2)
    private String ownerCode;

    @ExcelProperty(value = "仓库", order = 3)
    private String warehouseCode;

    @ExcelProperty(value = "订单号", order = 4)
    private String orderNo;

    @ExcelProperty(value = "业务类型", order = 5)
    private String businessType;

}
