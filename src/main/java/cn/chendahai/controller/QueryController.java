package cn.chendahai.controller;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询相关
 *
 * @author chy
 * @date 2020/3/2 0002
 */
@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    RestHighLevelClient esClient;

    /**
     * 根据id获取信息
     */
    @GetMapping("getById")
    public ResponseEntity getById(String index, String type, Integer id) throws IOException {
        GetRequest request = new GetRequest(index, type, id.toString());
        GetResponse response = esClient.get(request, RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSONString(response));
        return new ResponseEntity(JSONObject.toJSONString(response.getSource()), HttpStatus.OK);

    }

    /**
     * 硬编码查询
     */
    @GetMapping("search")
    public ResponseEntity search(String index, String type, String name) throws IOException {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("name", name));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.from(0);
        // 默认为10
        searchSourceBuilder.size(20);

        // 第一个是获取字段，第二个是过滤的字段，默认获取全部
//        searchSourceBuilder.fetchSource(new String[]{"id", "name", "age"}, new String[]{"name"});

        List<String> highlightFieldList = new ArrayList<>();
        highlightFieldList.add("name");
        highlightFieldList.add("scheduledDate");
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        for (int x = 0; x < highlightFieldList.size(); x++) {
            HighlightBuilder.Field field = new HighlightBuilder.Field(highlightFieldList.get(x)).preTags("<high>").postTags("</high>");
            highlightBuilder.field(field);
        }
        searchSourceBuilder.highlighter(highlightBuilder);

        SearchRequest request = new SearchRequest(index);
        request.types(type);
        request.source(searchSourceBuilder);

        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSONString(response));
        SearchHits hits = response.getHits();
        System.out.println(hits);
        for (SearchHit hit : hits) {
            System.out.println(hit);
            System.out.println(hit.getSourceAsString());
        }
        return new ResponseEntity(JSONObject.toJSONString(response), HttpStatus.OK);


    }


}
