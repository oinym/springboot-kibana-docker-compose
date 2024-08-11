package com.example.accessingdatamysql.elastic;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.servlet.http.HttpServlet;
import lombok.Data;

@Data
@Document(indexName = "user")
public class ElkModel extends HttpServlet{
    
    
}
