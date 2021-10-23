package com.kafkastreamingdemo.kafkatwitterdemo.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TwitterEvent {

    private List<Tweet> data = null;
    private Meta meta;
    private Map<String, Object> additionalProperties = new HashMap<>();

}
