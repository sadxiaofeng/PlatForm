package com.filter;

import com.util.Constant;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 钱逊 on 2017/4/16.
 */
public class PageAccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String accessToken = null;
        String returnUri = request.getRequestURL().toString();
        if (null != request.getCookies()) {
            for (Cookie cookie : request.getCookies()) {
                if (Constant.ACCESS_TOKEN.equals(cookie.getName())) {
                    accessToken = cookie.getValue();
                    break;
                }
            }
        }
        if(accessToken!=null || returnUri.contains("accessToken")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            request.getRequestDispatcher("/mvc/auth/login").forward(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
