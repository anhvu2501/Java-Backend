package com.example.refactortask1.data.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DatePoint {
    private Long time;
    private Double point;
}
