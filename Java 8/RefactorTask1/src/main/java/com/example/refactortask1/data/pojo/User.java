package com.example.refactortask1.data.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class User {
    private String userId;
    private int likePoint;
    private int viewPoint;
    private int questionPoint;
    private int commentPoint;
    private int answerPoint;
    private int blogPoint;
    private int activePoint;
    private long time;
    @JsonProperty("_id")
    private String id;
}
