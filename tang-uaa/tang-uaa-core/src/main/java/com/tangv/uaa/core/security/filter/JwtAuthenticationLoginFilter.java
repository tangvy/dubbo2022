/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.uaa.core.security.filter;

import com.tangv.uaa.core.consts.UaaConst;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tang wei
 * @version JwtAuthenticationLoginFilter.java, v 0.1 2023/5/21 16:12 tang wei Exp $
 */
public class JwtAuthenticationLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationLoginFilter() {
        super(new AntPathRequestMatcher(UaaConst.Login_URL, HttpMethod.POST.name()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse)
            throws AuthenticationException, IOException, ServletException {
        String username = httpServletRequest.getParameter(UaaConst.USER_NAME);
        String password = httpServletRequest.getParameter(UaaConst.PASSWORD);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        return getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
    }
}
