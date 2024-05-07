package com.example.testproject.filter;

import com.example.testproject.model.Result;
import com.example.testproject.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.cloudinary.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import java.io.IOException;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

@Slf4j
@WebFilter(urlPatterns = "/*")//拦截所有
public class DemoFilter implements jakarta.servlet.Filter {



    @Override
    public void init(jakarta.servlet.FilterConfig filterConfig) throws jakarta.servlet.ServletException {
        jakarta.servlet.Filter.super.init(filterConfig);
        System.out.println("DemoFilter init开始");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("DemoFilter 拦截");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //let go
        //1. get the url
        String url = request.getRequestURI().toString();
        //2. whether login
        if(url.contains("login")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //3. get token
        String jwt = request.getHeader("token");
        if(!StringUtils.hasLength(jwt)){
            log.info("token is empty");
            Result error = Result.error("invalid login");
            String notLogin = JSONObject.valueToString(error);
            response.getWriter().write(notLogin);
            return;
        }
        //4. check token
        try{
            JwtUtils.parseJwt(jwt);
        }catch (Exception e){
            log.info("fail to parse jwt");
            Result error = Result.error("invalid login");
            String notLogin = JSONObject.valueToString(error);
            response.getWriter().write(notLogin);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);


    }


}
