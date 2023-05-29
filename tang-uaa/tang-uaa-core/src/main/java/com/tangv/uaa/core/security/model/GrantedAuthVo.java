/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.uaa.core.security.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author tang wei
 * @version GrantedAuthVo.java, v 0.1 2023/5/29 20:20 tang wei Exp $
 */
@Data
public class GrantedAuthVo implements GrantedAuthority {

    String auth;

    @Override
    public String getAuthority() {
        return auth;
    }
}
