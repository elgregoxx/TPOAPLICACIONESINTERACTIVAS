package com.uade.tpo.marketplace.controller;

import com.uade.tpo.marketplace.dto.request.CreateCourseRequest;
import com.uade.tpo.marketplace.dto.response.CourseResponse;
import com.uade.tpo.marketplace.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseResponse> getAllClasses() { return courseService.getAllCourses(); }

    @GetMapping("/{id}")
    public CourseResponse getClassById(@PathVariable Long id) { return courseService.getCourseById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponse createClass(@RequestBody CreateCourseRequest request) { return courseService.createCourse(request); }

    @PutMapping("/{id}")
    public CourseResponse updateClass(@PathVariable Long id, @RequestBody CreateCourseRequest request) { return courseService.updateCourse(id, request); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClass(@PathVariable Long id) { courseService.deleteCourse(id); }
}
