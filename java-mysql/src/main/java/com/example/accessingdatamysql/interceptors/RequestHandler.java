package com.example.accessingdatamysql.interceptors;

// import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @Component
public class RequestHandler implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
        String req = "Could not stringify request";
        ObjectMapper mapper = new ObjectMapper();
        try {
            req = mapper.writeValueAsString(request);
            System.out.println("Got hit on new request\n" + req);
        } catch (JsonProcessingException e) {

            System.out.println("Got hit on new request\n" + e.getMessage());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
            ModelAndView model) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String resString = mapper.writeValueAsString(object);
            System.out.println("Response: \n" + resString);
        } catch (Exception e) {
            System.out.println("Response ERROR: \n" + e.getMessage());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
            Exception exception) {
    }

}
