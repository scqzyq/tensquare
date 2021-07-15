package com.scqzy.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/15 10:47
 */
@Configuration
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        RestClientBuilder restClientBuilder = RestClient.builder(
                new HttpHost(
                        "192.168.167.130",
                        9200,
                        "http")
        ).setRequestConfigCallback( // 自定义超时时间
                requestConfigBuilder -> {

                    return requestConfigBuilder.setConnectTimeout(5000 * 1000) // 自定义连接超时时间
                            .setSocketTimeout(6000 * 1000);// 自定义Socket超时时间（默认 30,000 milliseconds ）
                }
        );

        return new RestHighLevelClient(restClientBuilder);
    }

    @Bean
    public ElasticsearchRestTemplate elasticsearchRestTemplate() {
        return new ElasticsearchRestTemplate(elasticsearchClient());
    }
}
