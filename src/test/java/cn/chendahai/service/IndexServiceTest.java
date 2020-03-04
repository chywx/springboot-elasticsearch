package cn.chendahai.service;

import cn.chendahai.entity.Person;
import com.alibaba.fastjson.JSON;
import java.io.IOException;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IndexServiceTest {

    @Autowired
    RestHighLevelClient esClient;

    @Test
    public void update() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("person", "person", "118");
        Person person = new Person();
        person.setName("what");
        updateRequest.doc(JSON.toJSONString(person), XContentType.JSON);
        UpdateResponse update = esClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(update.getResult());
    }

}