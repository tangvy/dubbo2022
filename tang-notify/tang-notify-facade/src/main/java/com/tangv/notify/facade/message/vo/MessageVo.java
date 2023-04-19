/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.notify.facade.message.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tang wei
 * @version MessageVo.java, v 0.1 2023/4/18 19:43 tang wei Exp $
 */
public class MessageVo implements Serializable {

    private Long msgId;

    private String msgCode;

    private String gmtCreateBy;

    private Date gmtCreate;

    private String gmtModifyBy;

    private Date gmtModify;

    private static final long serialVersionUID = 1L;

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode == null ? null : msgCode.trim();
    }

    public String getGmtCreateBy() {
        return gmtCreateBy;
    }

    public void setGmtCreateBy(String gmtCreateBy) {
        this.gmtCreateBy = gmtCreateBy == null ? null : gmtCreateBy.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModifyBy() {
        return gmtModifyBy;
    }

    public void setGmtModifyBy(String gmtModifyBy) {
        this.gmtModifyBy = gmtModifyBy == null ? null : gmtModifyBy.trim();
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", msgId=").append(msgId);
        sb.append(", msgCode=").append(msgCode);
        sb.append(", gmtCreateBy=").append(gmtCreateBy);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModifyBy=").append(gmtModifyBy);
        sb.append(", gmtModify=").append(gmtModify);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
