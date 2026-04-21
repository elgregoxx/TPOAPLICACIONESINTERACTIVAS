package com.uade.tpo.marketplace.dto.response;

import com.uade.tpo.marketplace.model.Enrollment;

public class EnrollmentResponse {
    private Long id;
    private Long studentId;
    private String studentName;
    private Long courseId;
    private String courseTitle;
    private String status;

    public static EnrollmentResponse from(Enrollment e) {
        EnrollmentResponse r = new EnrollmentResponse();
        r.id = e.getId();
        r.studentId = e.getStudent().getId();
        r.studentName = e.getStudent().getName();
        r.courseId = e.getCourse().getId();
        r.courseTitle = e.getCourse().getTitle();
        r.status = e.getStatus();
        return r;
    }

    public Long getId() { return id; }
    public Long getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public Long getCourseId() { return courseId; }
    public String getCourseTitle() { return courseTitle; }
    public String getStatus() { return status; }
}
