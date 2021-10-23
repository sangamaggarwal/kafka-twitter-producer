package com.kafkastreamingdemo.kafkatwitterdemo.job;

import com.kafkastreamingdemo.kafkatwitterdemo.dto.TwitterEvent;
import com.kafkastreamingdemo.kafkatwitterdemo.producer.TwitterFeedEventsProducer;
import com.kafkastreamingdemo.kafkatwitterdemo.service.LoadDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TwitterLoadData {

    private final LoadDataService loadDataService;
    private final TwitterFeedEventsProducer twitterFeedEventsProducer;

    @Autowired
    public TwitterLoadData(LoadDataService loadDataService, TwitterFeedEventsProducer twitterFeedEventsProducer) {
        this.loadDataService = loadDataService;
        this.twitterFeedEventsProducer = twitterFeedEventsProducer;
    }

    @Scheduled( cron = "${application.integration.twitter.load-data.cron-expression}" )
    public void run() {
        TwitterEvent apiResponseDto = loadDataService.loadTwitterData();
        twitterFeedEventsProducer.sendLibraryEvent( apiResponseDto );
    }
}
