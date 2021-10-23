package com.kafkastreamingdemo.kafkatwitterdemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkastreamingdemo.kafkatwitterdemo.dto.Tweet;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.springframework.stereotype.Component;

@Component
public class KafkaPartitioner implements Partitioner {

    @SneakyThrows
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List partitions = cluster.partitionsForTopic( topic );
        int numberOfPartitions = partitions.size();
        Tweet data = new ObjectMapper().readValue( (String) value, Tweet.class );
        if( numberOfPartitions > 1 && (data.getSource().equalsIgnoreCase( "Twitter for android" )) ) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public void close() {
        // NA.
    }

    @Override
    public void onNewBatch(String topic, Cluster cluster, int prevPartition) {
        // NA
    }

    @Override
    public void configure(Map<String, ?> configs) {
        // NA
    }
}
