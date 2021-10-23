package com.kafkastreamingdemo.kafkatwitterdemo.controller;

import com.kafkastreamingdemo.kafkatwitterdemo.dto.TwitterEvent;
import com.kafkastreamingdemo.kafkatwitterdemo.producer.TwitterFeedEventsProducer;
import com.kafkastreamingdemo.kafkatwitterdemo.service.LoadDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/v1/twitter" )
@Slf4j
public class TwitterController {

    private final LoadDataService loadDataService;
    private final TwitterFeedEventsProducer twitterFeedEventsProducer;

    @Autowired
    public TwitterController(LoadDataService loadDataService, TwitterFeedEventsProducer twitterFeedEventsProducer) {
        this.loadDataService = loadDataService;
        this.twitterFeedEventsProducer = twitterFeedEventsProducer;
    }

    @GetMapping( value = "/load-data" )
    public ResponseEntity<String> loadData() {
        log.info( "Calling Twitter Load Data API" );
        TwitterEvent apiResponseDto = loadDataService.loadTwitterData();
        twitterFeedEventsProducer.sendLibraryEvent( apiResponseDto );
        return ResponseEntity.status( HttpStatus.ACCEPTED ).body( "success" );
    }
}
