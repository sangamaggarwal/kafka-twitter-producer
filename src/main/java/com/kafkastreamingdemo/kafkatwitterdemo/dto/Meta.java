package com.kafkastreamingdemo.kafkatwitterdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Meta {

    @JsonProperty("newest_id")
    private String newestId;
    @JsonProperty("oldest_id")
    private String oldestId;
    @JsonProperty("result_count")
    private Integer resultCount;
    @JsonProperty("next_token")
    private String nextToken;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

}
