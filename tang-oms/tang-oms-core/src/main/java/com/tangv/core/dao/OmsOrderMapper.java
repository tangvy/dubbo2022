package com.tangv.core.dao;

import com.tangv.core.model.entity.OmsOrder;

public interface OmsOrderMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(OmsOrder record);

    int insertSelective(OmsOrder record);

    OmsOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OmsOrder record);

    int updateByPrimaryKeyWithBLOBs(OmsOrder record);

    int updateByPrimaryKey(OmsOrder record);
}