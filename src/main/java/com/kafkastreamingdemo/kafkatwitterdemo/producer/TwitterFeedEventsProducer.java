package com.kafkastreamingdemo.kafkatwitterdemo.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkastreamingdemo.kafkatwitterdemo.dto.TwitterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Component
@Slf4j
public class TwitterFeedEventsProducer {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    ObjectMapper objectMapper;

    @Value( "${spring.kafka.template.default-topic}" )
    private String topic;

    public void sendLibraryEvent(TwitterEvent twitterEvent) {

        twitterEvent.getData().forEach( item -> {
            try {
                String value = objectMapper.writeValueAsString( item );
                ListenableFuture<SendResult<String, String>> listenableFuture
                    = kafkaTemplate.send( topic, value );
                listenableFuture.addCallback(
                    new ListenableFutureCallback<SendResult<String, String>>() {
                        @Override
                        public void onFailure(Throwable ex) {
                            log.error( "...Failure..." + ex );
                        }

                        @Override
                        public void onSuccess(SendResult<String, String> result) {
                            log.info( "...onSuccess..." + result.getProducerRecord().value() );
                        }
                    } );
            } catch( JsonProcessingException e ) {
                e.printStackTrace();
            }
        } );
    }
}