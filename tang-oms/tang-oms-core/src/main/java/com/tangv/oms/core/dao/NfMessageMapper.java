package com.tangv.oms.core.dao;

import com.tangv.oms.core.model.entity.NfMessage;

public interface NfMessageMapper {
    int deleteByPrimaryKey(Long msgId);

    int insert(NfMessage record);

    int insertSelective(NfMessage record);

    NfMessage selectByPrimaryKey(Long msgId);

    int updateByPrimaryKeySelective(NfMessage record);

    int updateByPrimaryKey(NfMessage record);
}