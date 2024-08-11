package com.example.accessingdatamysql.elastic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
public class ImperativeElasticsearchConfig extends ElasticsearchConfiguration {
    @Value("${elasticsearch.host}")
    private String elkhost;

    @Override
    public ClientConfiguration clientConfiguration() {

        return ClientConfiguration.builder().connectedTo(elkhost).build();
    }
}
