package com.kafkastreamingdemo.kafkatwitterdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tweet {

    @JsonProperty( "source" )
    private String source;
    @JsonProperty( "in_reply_to_user_id" )
    private String inReplyToUserId;
    @JsonProperty( "lang" )
    private String lang;
    @JsonProperty( "created_at" )
    private String createdAt;
    @JsonProperty( "id" )
    private String id;
    @JsonProperty( "author_id" )
    private String authorId;
    @JsonProperty( "text" )
    private String text;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
}
