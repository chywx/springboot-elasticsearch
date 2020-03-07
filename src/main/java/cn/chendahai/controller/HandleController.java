package cn.chendahai.controller;

import cn.chendahai.entity.Person;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述
 *
 * @author chy
 * @date 2020/3/6 0006
 */
@RestController
@RequestMapping("/handle")
public class HandleController {

    @Autowired
    RestHighLevelClient esClient;

    /**
     * 批量添加数据
     */
    @PostMapping("addAll")
    public ResponseEntity addAll(String index, String type) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (Person person : Person.people) {
            IndexRequest indexRequest = new IndexRequest(index, type, person.getId().toString());
            indexRequest.source(JSON.toJSONString(person), XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        BulkResponse response = esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSONString(response));
        return new ResponseEntity(JSONObject.toJSONString(response), HttpStatus.OK);
    }


    /**
     * 更新数据
     */
    @PostMapping("update")
    public ResponseEntity update(String index, String type, Person person) throws IOException {
        UpdateRequest request = new UpdateRequest(index, type, person.getId().toString());

        request.doc(JSON.toJSONString(person), XContentType.JSON);

        esClient.updateAsync(request, RequestOptions.DEFAULT, new ActionListener<UpdateResponse>() {
            @Override
            public void onResponse(UpdateResponse updateResponse) {
                System.out.println(updateResponse.getResult());
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("error");
                e.printStackTrace();
                System.out.println(e.toString());
            }
        });
        return null;
    }

}
