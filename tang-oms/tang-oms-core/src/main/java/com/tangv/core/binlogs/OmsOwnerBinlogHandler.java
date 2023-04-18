/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.core.binlogs;

import com.alibaba.fastjson.JSONObject;
import com.tangv.common.annotations.DataBase;
import com.tangv.common.enums.BinlogConsumeTableEnum;
import com.tangv.common.enums.DataBaseType;
import com.tangv.core.binlogs.model.OmsOwnerBinlog;
import com.tangv.core.dao.OmsOwnerSyncMapper;
import com.tangv.core.model.entity.OmsOwnerSync;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tang wei
 * @version MarketplaceLogisticCityBinlogService.java, v 0.1 2023/3/2 16:30 tang wei Exp $
 * 处理MarketplaceLogisticCity的binlog
 */
@Service
public class OmsOwnerBinlogHandler extends AbstractBinlogConsumeHandler<OmsOwnerSync> {

    @Resource
    private OmsOwnerSyncMapper omsOwnerSyncMapper;

    @Override
    public BinlogConsumeTableEnum consumeTable() {
        return BinlogConsumeTableEnum.OMS_OWNER;
    }

    @DataBase(value = DataBaseType.TANG_FEATURE)
    @Override
    public int insert(OmsOwnerSync insert) {
        return omsOwnerSyncMapper.insert(insert);
    }

    @DataBase(value = DataBaseType.TANG_FEATURE)
    @Override
    public int update(OmsOwnerSync update) {
        return omsOwnerSyncMapper.updateByPrimaryKey(update);
    }

    @DataBase(value = DataBaseType.TANG_FEATURE)
    @Override
    public int delete(OmsOwnerSync delete) {
        return omsOwnerSyncMapper.deleteByPrimaryKey(delete.getOwnerId());
    }

    @Override
    public OmsOwnerSync convert(JSONObject binlogContent) {
        OmsOwnerBinlog omsOwnerBinlog = JSONObject.toJavaObject(binlogContent, OmsOwnerBinlog.class);
        OmsOwnerSync omsOwner = new OmsOwnerSync();
        BeanUtils.copyProperties(omsOwnerBinlog, omsOwner);
        return omsOwner;
    }
}
