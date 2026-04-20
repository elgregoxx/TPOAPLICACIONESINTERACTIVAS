package com.uade.tpo.marketplace.dto.request;

import lombok.Data;

@Data
public class CreateCourseRequest {
    private String title;
    private String description;
    private Integer durationInMinutes;
    private Long teacherId;
}
