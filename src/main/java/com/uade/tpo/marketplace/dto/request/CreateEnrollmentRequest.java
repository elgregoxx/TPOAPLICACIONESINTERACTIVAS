package com.uade.tpo.marketplace.dto.request;

import lombok.Data;

@Data
public class CreateEnrollmentRequest {
    private Long studentId;
    private Long courseId;
}
