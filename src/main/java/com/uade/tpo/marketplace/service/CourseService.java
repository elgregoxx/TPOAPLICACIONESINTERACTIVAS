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
        return courseRepository.findAll().stream().map(CourseResponse::from).toList();
    }

    public CourseResponse getCourseById(Long id) {
        return CourseResponse.from(findEntityById(id));
    }

    public CourseResponse createCourse(CreateCourseRequest request) {
        User teacher = userService.findEntityById(request.getTeacherId());
        if (teacher.getRole() != Role.ROLE_TEACHER && teacher.getRole() != Role.ROLE_ADMIN) {
            throw new IllegalArgumentException("El usuario indicado no es un profesor");
        }
        Course course = Course.builder().title(request.getTitle()).description(request.getDescription())
                .durationInMinutes(request.getDurationInMinutes()).teacher(teacher).build();
        return CourseResponse.from(courseRepository.save(course));
    }

    public CourseResponse updateCourse(Long id, CreateCourseRequest request) {
        Course course = findEntityById(id);
        User teacher = userService.findEntityById(request.getTeacherId());
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setDurationInMinutes(request.getDurationInMinutes());
        course.setTeacher(teacher);
        return CourseResponse.from(courseRepository.save(course));
    }

    public void deleteCourse(Long id) {
        findEntityById(id);
        courseRepository.deleteById(id);
    }

    public Course findEntityById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con id: " + id));
    }
}
