package com.example.accessingdatamysql.interceptors;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.accessingdatamysql.elastic.EcsModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RequestHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
        log.info("Pre handle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
            ModelAndView model) {
        EcsModel ecs = new EcsModel(request, response, object);
        ecs.setMsg("Got a hit");
        try {
            log.info("got a hit");
            log.info((new ObjectMapper().writer().withDefaultPrettyPrinter()).writeValueAsString(ecs));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
            Exception exception) {
        log.info("Got some call on service");
    }

}
