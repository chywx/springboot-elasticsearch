package cn.chendahai.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
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


    @Value("${es.address}")
    private String esAddress;

    @Bean
    public RestHighLevelClient highLevelClient() {

        ClientConfiguration configuration = ClientConfiguration.builder()
            .connectedTo(esAddress)
            .build();
        RestHighLevelClient client = RestClients.create(configuration).rest();
        return client;
    }

}
