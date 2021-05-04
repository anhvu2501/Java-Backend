package com.example.task2.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Post {
    @JsonProperty("_id")
    private String id;
//    @JsonProperty("creation_time")
    private Long creationTime;
//    @JsonProperty("title")
    private String title;
//    @JsonProperty("owner_id")
    private String ownerId;
}
