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
 * @version UserVo.java, v 0.1 2023/5/22 17:33 tang wei Exp $
 */
@Data
public class UserVo implements Serializable {

    private String userId;

    private String username;

    private String password;

    private String fullName;

    private Boolean disabled;

    private Boolean deleted;

    private Date createTime;

    private Date modifyTime;

}
