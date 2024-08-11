package com.example.accessingdatamysql.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.accessingdatamysql.utils.ObjectConverter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestHandler implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
        String req = "Could not stringify request";
        req = ObjectConverter.stringify(request);
        System.out.println("Got hit on new request\n" + req);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
            ModelAndView model) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
            Exception exception) {
    }

}
