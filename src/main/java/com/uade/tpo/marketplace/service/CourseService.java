package com.uade.tpo.marketplace.service;

import com.uade.tpo.marketplace.dto.request.CreateCourseRequest;
import com.uade.tpo.marketplace.dto.response.CourseResponse;
import com.uade.tpo.marketplace.exception.ResourceNotFoundException;
import com.uade.tpo.marketplace.model.Course;
import com.uade.tpo.marketplace.model.Role;
import com.uade.tpo.marketplace.model.User;
import com.uade.tpo.marketplace.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserService userService;

    public CourseService(CourseRepository courseRepository, UserService userService) {
        this.courseRepository = courseRepository;
        this.userService = userService;
    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CourseResponse createCourse(CreateCourseRequest request) {
        User teacher = userService.findEntityById(request.getTeacherId());

        if (teacher.getRole() != Role.TEACHER) {
            throw new IllegalArgumentException("El usuario indicado no es un profesor");
        }

        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setDurationInMinutes(request.getDurationInMinutes());
        course.setTeacherId(request.getTeacherId());

        Course savedCourse = courseRepository.save(course);
        return mapToResponse(savedCourse);
    }

    public Course findEntityById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clase no encontrada con id " + id));
    }

    public CourseResponse mapToResponse(Course course) {
        User teacher = userService.findEntityById(course.getTeacherId());

        return new CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getDurationInMinutes(),
                course.getTeacherId(),
                teacher.getName()
        );
    }
}
