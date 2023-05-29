/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.uaa.api.user;

import com.tangv.uaa.facade.user.vo.AuthVo;
import com.tangv.uaa.facade.user.vo.UserVo;

import java.util.List;

/**
 * @author tang wei
 * @version UserService.java, v 0.1 2023/5/22 12:29 tang wei Exp $
 */
public interface UserService {

    UserVo loadUserByUsername(String username);

    UserVo loadById(Long userId);

    List<AuthVo> loadByUserId(Long userId);

    List<String> loadAuthByUserId(Long userId);
}
