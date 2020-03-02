package cn.chendahai.controller;

import cn.chendahai.entity.Match;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
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
 * 功能描述
 *
 * @author chy
 * @date 2020/3/1 0001
 */
@RestController
public class IndexController {

    @Autowired
    RestHighLevelClient esClient;


    @RequestMapping("createIndex")
    public ResponseEntity createIndex(String index) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(index);
        CreateIndexResponse response = esClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSONString(response));
        return new ResponseEntity(JSONObject.toJSONString(response), HttpStatus.OK);
    }


    @RequestMapping("deleteIndex")
    public ResponseEntity deleteIndex(String index) throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        AcknowledgedResponse response = esClient.indices().delete(request);
        System.out.println(JSONObject.toJSONString(response));
        return new ResponseEntity(JSONObject.toJSONString(response), HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity add(String index, String type, Match match) throws IOException {
        IndexRequest request = new IndexRequest(index, type, match.getId().toString());
        request.source(JSON.toJSONString(match), XContentType.JSON);
        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSONString(response));
        return new ResponseEntity(JSONObject.toJSONString(response), HttpStatus.OK);
    }


    @PostMapping("add2")
    public ResponseEntity add2() throws IOException {
        IndexRequest request = new IndexRequest("dahai", "facebook", "3");
        String jsonString = "{\"user\":\"kimchy\",\"postDate\":\"2013-01-30\",\"message\":\"trying out Elasticsearch\"}";
        request.source(jsonString, XContentType.JSON);
        IndexResponse response = esClient.index(request);
        System.out.println(JSONObject.toJSONString(response));
        return new ResponseEntity(JSONObject.toJSONString(response), HttpStatus.OK);
    }

}
