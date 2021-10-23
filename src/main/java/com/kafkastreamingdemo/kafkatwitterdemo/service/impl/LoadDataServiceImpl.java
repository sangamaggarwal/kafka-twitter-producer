package com.kafkastreamingdemo.kafkatwitterdemo.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkastreamingdemo.kafkatwitterdemo.dto.TwitterEvent;
import com.kafkastreamingdemo.kafkatwitterdemo.service.LoadDataService;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class LoadDataServiceImpl implements LoadDataService {

    private final RestTemplate restTemplate;

    @Autowired
    public LoadDataServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public TwitterEvent loadTwitterData() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );
        headers.set( "Authorization",
            "Bearer AAAAAAAAAAAAAAAAAAAAAIvSUQEAAAAAyoAqahLTHVrc%2FbcFlG%2BuQoLGPzE%3DNZtwqt18SSTMoGdLjjvf0qnHHktqrMRsWSeLRLjzl49Qry7Gwd" );
        ResponseEntity<Object> responseEntity = restTemplate.exchange(
            "https://api.twitter.com/2/tweets/search/recent?query=(sangam) lang:en&tweet.fields=author_id,created_at,in_reply_to_user_id,lang,source",
            HttpMethod.GET, new HttpEntity<>( headers ), Object.class, new Objects[ 0 ] );
        return new ObjectMapper().convertValue( responseEntity.getBody(),
            new TypeReference<TwitterEvent>() {
            } );
    }
}
