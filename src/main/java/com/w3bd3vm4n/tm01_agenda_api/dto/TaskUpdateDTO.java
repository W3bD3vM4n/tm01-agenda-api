package com.w3bd3vm4n.tm01_agenda_api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskUpdateDTO {

    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int relevance;
    private int energy;
    private String color;
    private String detail;

}
