/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.uaa.facade.user.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tang wei
 * @version AuthVo.java, v 0.1 2023/5/29 19:48 tang wei Exp $
 */
@Data
public class AuthVo implements Serializable {

    private Long authId;

    private String authCode;

    private String authName;

    private String authUrl;

    private Boolean disabled;

    private Boolean deleted;

    private Date createTime;

    private Date modifyTime;
}
