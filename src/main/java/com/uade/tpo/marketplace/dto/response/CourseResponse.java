package com.uade.tpo.marketplace.dto.response;

import com.uade.tpo.marketplace.model.Course;

public class CourseResponse {
    private Long id;
    private String title;
    private String description;
    private Integer durationInMinutes;
    private Long teacherId;
    private String teacherName;

    public static CourseResponse from(Course course) {
        CourseResponse r = new CourseResponse();
        r.id = course.getId();
        r.title = course.getTitle();
        r.description = course.getDescription();
        r.durationInMinutes = course.getDurationInMinutes();
        r.teacherId = course.getTeacher().getId();
        r.teacherName = course.getTeacher().getName();
        return r;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Integer getDurationInMinutes() { return durationInMinutes; }
    public Long getTeacherId() { return teacherId; }
    public String getTeacherName() { return teacherName; }
}
