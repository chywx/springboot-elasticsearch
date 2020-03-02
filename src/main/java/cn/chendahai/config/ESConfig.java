package cn.chendahai.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

/**
 * 功能描述
 *
 * @author chy
 * @date 2020/3/1 0001
 */
@Configuration
public class ESConfig {

    @Bean
    public RestHighLevelClient highLevelClient() {

        ClientConfiguration configuration = ClientConfiguration.builder()
            .connectedTo("192.168.1.10:9200")
            .build();
        RestHighLevelClient client = RestClients.create(configuration).rest();
        return client;
    }

}
