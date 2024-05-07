package com.example.testproject.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Emp {
    private Integer Id;
    private String username;
    private String password;
    private String name;
    private String gender;
    private String image;
    private String job;
    private LocalDate entrydate;
    private Integer depId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
