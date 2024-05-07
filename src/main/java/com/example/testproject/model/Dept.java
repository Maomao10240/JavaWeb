package com.example.testproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class Dept {
    private int Id;
    private String Name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
