package com.tangv.core.dao;

import com.tangv.core.model.entity.OmsOwner;

public interface OmsOwnerMapper {

    int deleteByPrimaryKey(Integer ownerId);

    int insert(OmsOwner record);

    int insertSelective(OmsOwner record);

    OmsOwner selectByPrimaryKey(Integer ownerId);

    int updateByPrimaryKeySelective(OmsOwner record);

    int updateByPrimaryKey(OmsOwner record);
}