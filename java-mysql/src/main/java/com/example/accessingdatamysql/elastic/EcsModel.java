package com.example.accessingdatamysql.elastic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

@Data
public class EcsModel {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Object obj;
    private String className;
    private String method;
    private String endpoint;
    private String[] params;
    private String msg;

    

    public EcsModel() {
    }



    public EcsModel(HttpServletRequest request, HttpServletResponse response, Object obj) {
        this.method = request.getMethod();
        this.endpoint = request.getContextPath();
        this.params = request.getParameterValues(endpoint);
        this.className = request.getServletContext().getClass().getSimpleName();
    }

}
