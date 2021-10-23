package com.kafkastreamingdemo.kafkatwitterdemo;

import com.kafkastreamingdemo.kafkatwitterdemo.config.KafkaPartitioner;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Configuration
@EnableScheduling
public class KafkaTwitterProducerApplication {

    @Value( "${spring.kafka.producer.bootstrap-servers}" )
    private String brokers;

    public static void main(String[] args) {
        SpringApplication.run( KafkaTwitterProducerApplication.class, args );
    }

    @Bean( "kafkaTemplate" )
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<String, String>( producerFactory() );
    }

    public ProducerFactory<String, String> producerFactory() {

        // set the producer properties
        Map<String, Object> properties = new HashMap<>();
        properties.put( ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers );
        properties.put( ProducerConfig.RETRIES_CONFIG, 0 );
        properties.put( ProducerConfig.BATCH_SIZE_CONFIG, 4096 );
        properties.put( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class );
        properties.put( ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class );
        properties.put( ProducerConfig.PARTITIONER_CLASS_CONFIG, KafkaPartitioner.class );
        return new DefaultKafkaProducerFactory<>( properties );
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
