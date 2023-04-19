package com.tangv.oms.core.dao;

import com.tangv.oms.core.model.entity.OmsOrder;

public interface OmsOrderMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(OmsOrder record);

    int insertSelective(OmsOrder record);

    OmsOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OmsOrder record);

    int updateByPrimaryKeyWithBLOBs(OmsOrder record);

    int updateByPrimaryKey(OmsOrder record);
}