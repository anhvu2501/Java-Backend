package com.example.task2.data.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors (chain = true)
public class TimePoint {
    private Long day;
    private Long hour;
    private Long point;
}
