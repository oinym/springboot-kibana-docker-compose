package com.example.accessingdatamysql.elastic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "github.io.truongbn.elasticsearch.repository")
public class ImperativeElasticsearchConfig extends ElasticsearchConfiguration {
    @Value("${elasticsearch.host}")
    private String elkhost;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder().connectedTo(elkhost).build();
    }
}
