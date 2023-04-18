package com.tangv.core.dao;

import com.tangv.core.model.entity.OmsOwnerSync;

public interface OmsOwnerSyncMapper {

    int deleteByPrimaryKey(Integer ownerId);

    int insert(OmsOwnerSync record);

    int insertSelective(OmsOwnerSync record);

    OmsOwnerSync selectByPrimaryKey(Integer ownerId);

    int updateByPrimaryKeySelective(OmsOwnerSync record);

    int updateByPrimaryKey(OmsOwnerSync record);
}