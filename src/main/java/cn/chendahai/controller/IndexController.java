package cn.chendahai.controller;

import cn.chendahai.entity.Person;
import cn.chendahai.entity.Son;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.elasticsearch.action.ActionListener;
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
@RequestMapping("/index")
public class IndexController {

    @Autowired
    RestHighLevelClient esClient;


    /**
     * 创建索引
     */
    @RequestMapping("/create")
    public ResponseEntity create(String index) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(index);
        CreateIndexResponse response = esClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSONString(response));
        return new ResponseEntity(JSONObject.toJSONString(response), HttpStatus.OK);
    }


    /**
     * 删除索引
     */
    @RequestMapping("/delete")
    public ResponseEntity delete(String index) throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        AcknowledgedResponse response = esClient.indices().delete(request);
        System.out.println(JSONObject.toJSONString(response));
        return new ResponseEntity(JSONObject.toJSONString(response), HttpStatus.OK);
    }

    /**
     * 添加数据
     */
    @PostMapping("add")
    public ResponseEntity add(String index, String type, Person person) throws IOException {

        ArrayList<Son> sonList = new ArrayList<Son>() {
            {
                add(new Son("aa", 1));
                add(new Son("cc", 3));
                add(new Son("bb", 2));
            }
        };
        person.setSonList(sonList);
        IndexRequest request = new IndexRequest(index, type, person.getId().toString());
        request.source(JSON.toJSONString(person), XContentType.JSON);
        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSONString(response));
        return new ResponseEntity(JSONObject.toJSONString(response), HttpStatus.OK);
    }


}
