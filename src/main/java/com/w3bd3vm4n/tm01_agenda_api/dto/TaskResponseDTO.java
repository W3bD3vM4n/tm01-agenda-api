package com.w3bd3vm4n.tm01_agenda_api.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponseDTO {

    private Long id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int relevance;
    private int energy;
    private String color;
    private String detail;

}
