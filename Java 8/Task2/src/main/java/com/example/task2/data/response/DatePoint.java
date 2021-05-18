package com.example.task2.data.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors (chain = true)
public class DatePoint {
    private Long point;
    private Long date;
}
