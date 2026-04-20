package com.uade.tpo.marketplace.service;

import com.uade.tpo.marketplace.dto.request.CreateEnrollmentRequest;
import com.uade.tpo.marketplace.dto.response.EnrollmentResponse;
import com.uade.tpo.marketplace.exception.ResourceNotFoundException;
import com.uade.tpo.marketplace.model.Course;
import com.uade.tpo.marketplace.model.Enrollment;
import com.uade.tpo.marketplace.model.Role;
import com.uade.tpo.marketplace.model.User;
import com.uade.tpo.marketplace.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserService userService;
    private final CourseService courseService;

    public EnrollmentResponse createEnrollment(CreateEnrollmentRequest request) {
        User student = userService.findEntityById(request.getStudentId());
        if (student.getRole() != Role.ROLE_STUDENT) {
            throw new IllegalArgumentException("El usuario indicado no es un alumno");
        }
        Course course = courseService.findEntityById(request.getCourseId());
        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .status("ENROLLED")
                .build();
        return EnrollmentResponse.from(enrollmentRepository.save(enrollment));
    }

    public List<EnrollmentResponse> getEnrollmentsByStudentId(Long studentId) {
        userService.findEntityById(studentId);
        return enrollmentRepository.findByStudentId(studentId).stream()
                .map(EnrollmentResponse::from).toList();
    }

    public List<EnrollmentResponse> getAllEnrollments() {
        return enrollmentRepository.findAll().stream().map(EnrollmentResponse::from).toList();
    }

    public void deleteEnrollment(Long id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Inscripción no encontrada con id: " + id);
        }
        enrollmentRepository.deleteById(id);
    }
}
