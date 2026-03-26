package com.uade.tpo.marketplace.service;

import com.uade.tpo.marketplace.dto.request.CreateEnrollmentRequest;
import com.uade.tpo.marketplace.dto.response.EnrollmentResponse;
import com.uade.tpo.marketplace.model.Course;
import com.uade.tpo.marketplace.model.Enrollment;
import com.uade.tpo.marketplace.model.Role;
import com.uade.tpo.marketplace.model.User;
import com.uade.tpo.marketplace.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserService userService;
    private final CourseService courseService;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, UserService userService, CourseService courseService) {
        this.enrollmentRepository = enrollmentRepository;
        this.userService = userService;
        this.courseService = courseService;
    }

    public EnrollmentResponse createEnrollment(CreateEnrollmentRequest request) {
        User student = userService.findEntityById(request.getStudentId());
        Course course = courseService.findEntityById(request.getCourseId());

        if (student.getRole() != Role.STUDENT) {
            throw new IllegalArgumentException("El usuario indicado no es un alumno");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(student.getId());
        enrollment.setCourseId(course.getId());
        enrollment.setStatus("ENROLLED");

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return mapToResponse(savedEnrollment);
    }

    public List<EnrollmentResponse> getEnrollmentsByStudentId(Long studentId) {
        userService.findEntityById(studentId);

        return enrollmentRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private EnrollmentResponse mapToResponse(Enrollment enrollment) {
        User student = userService.findEntityById(enrollment.getStudentId());
        Course course = courseService.findEntityById(enrollment.getCourseId());

        return new EnrollmentResponse(
                enrollment.getId(),
                student.getId(),
                student.getName(),
                course.getId(),
                course.getTitle(),
                enrollment.getStatus()
        );
    }
}
