package com.silence.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silence.enetity.Person;
import com.silence.service.ElasticsearchService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author silence
 * @since 2025/3/21 16:41
 **/
@Service
@RequiredArgsConstructor
public class ElasticsearchServiceImpl implements ElasticsearchService {

    private final RestHighLevelClient restHighLevelClient;

    private static final String INDEX_NAME = "person";

    private static final String ID_FIELD = "_id";

    private static final String NAME_FIELD = "name";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Person createPerson(Person person) {
        try {
            IndexRequest indexRequest = new IndexRequest(INDEX_NAME);
            indexRequest.source(personToJson(person), XContentType.JSON);
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            person.setId(indexResponse.getId());
            return person;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create person", e);
        }
    }

    @Override
    public Person getPersonById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Id cannot be null or empty");
        }

        try {
            // 构造查询请求
            SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchQuery(ID_FIELD, id));
            searchRequest.source(searchSourceBuilder);

            // 执行查询
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            // 检查搜索结果
            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits.length == 0) {
                return null;
            }

            // 解析并返回结果
            return objectMapper.readValue(hits[0].getSourceAsString(), Person.class);
        } catch (IOException e) {
            // 处理 IO 异常
            throw new RuntimeException("Failed to execute search request", e);
        }
    }

    @Override
    public Iterable<Person> searchByName(String name) {
        try {
            SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchQuery(NAME_FIELD, name));
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            SearchHit[] hits = searchResponse.getHits().getHits();
            if (hits.length == 0) {
                return null;
            }

            List<Person> persons = new ArrayList<>();
            for (SearchHit hit : hits) {
                persons.add(objectMapper.readValue(hit.getSourceAsString(), Person.class));
            }

            return persons;
        } catch (IOException e) {
            throw new RuntimeException("Failed to execute search request", e);
        }
    }

    @Override
    public Person updatePerson(String id, Person person) {
        try {
            UpdateRequest updateRequest = new UpdateRequest(INDEX_NAME, id);
            updateRequest.doc(personToJson(person), XContentType.JSON);
            UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            person.setId(updateResponse.getId());
            return person;
        } catch (IOException e) {
            throw new RuntimeException("Failed to update person", e);
        }
    }

    @Override
    public void deletePerson(String id) {
        try {
            DeleteRequest deleteRequest = new DeleteRequest(INDEX_NAME, id);
            DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            if (!deleteResponse.status().toString().equals("OK")) {
                throw new RuntimeException("Failed to delete person");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete person", e);
        }
    }

    private String personToJson(Person person) {
        return "{\"name\":\"" + person.getName() + "\",\"age\":" + person.getAge() +
                ",\"birth_date\":\"" + person.getBirthDate() + "\"}";
    }
}
