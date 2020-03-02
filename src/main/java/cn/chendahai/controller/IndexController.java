package cn.chendahai.controller;

import cn.chendahai.entity.Person;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 增删改查
 *
 * @author chy
 * @date 2020/3/1 0001
 */
@RestController
public class IndexController {

    @Autowired
    RestHighLevelClient esClient;


    /**
     * 创建索引
     * @param index
     * @return
     * @throws IOException
     */
    @RequestMapping("createIndex")
    public ResponseEntity createIndex(String index) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(index);
        CreateIndexResponse response = esClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSONString(response));
        return new ResponseEntity(JSONObject.toJSONString(response), HttpStatus.OK);
    }


    /**
     * 删除索引
     * @param index
     * @return
     * @throws IOException
     */
    @RequestMapping("deleteIndex")
    public ResponseEntity deleteIndex(String index) throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        AcknowledgedResponse response = esClient.indices().delete(request);
        System.out.println(JSONObject.toJSONString(response));
        return new ResponseEntity(JSONObject.toJSONString(response), HttpStatus.OK);
    }

    /**
     * 添加数据
     * @param index
     * @param type
     * @param person
     * @return
     * @throws IOException
     */
    @PostMapping("add")
    public ResponseEntity add(String index, String type, Person person) throws IOException {
        IndexRequest request = new IndexRequest(index, type, person.getId().toString());
        request.source(JSON.toJSONString(person), XContentType.JSON);
        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSONString(response));
        return new ResponseEntity(JSONObject.toJSONString(response), HttpStatus.OK);
    }

    /**
     * 批量添加数据
     * @param index
     * @param type
     * @return
     * @throws IOException
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
     * @param index
     * @param type
     * @param person
     * @return
     * @throws IOException
     */
    @PostMapping("update")
    public ResponseEntity update(String index, String type, Person person) throws IOException {
        UpdateRequest request = new UpdateRequest(index, type, person.getId().toString());

        request.doc(JSON.toJSONString(person), XContentType.JSON);

        UpdateResponse response = esClient.update(request, RequestOptions.DEFAULT);

        System.out.println(JSONObject.toJSONString(response));
        return new ResponseEntity(JSONObject.toJSONString(response), HttpStatus.OK);
    }

}
