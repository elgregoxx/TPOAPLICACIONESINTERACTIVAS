package com.uade.tpo.marketplace.controller;

import com.uade.tpo.marketplace.dto.request.CreateCourseRequest;
import com.uade.tpo.marketplace.dto.response.CourseResponse;
import com.uade.tpo.marketplace.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseResponse> getAllClasses() {
        return courseService.getAllCourses();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponse createClass(@RequestBody CreateCourseRequest request) {
        return courseService.createCourse(request);
    }
}
