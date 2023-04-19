package com.tangv.notify.core.dao;

import com.tangv.notify.core.model.entity.NfMessage;

public interface NfMessageMapper {
    int deleteByPrimaryKey(Long msgId);

    int insert(NfMessage record);

    int insertSelective(NfMessage record);

    NfMessage selectByPrimaryKey(Long msgId);

    int updateByPrimaryKeySelective(NfMessage record);

    int updateByPrimaryKey(NfMessage record);
}