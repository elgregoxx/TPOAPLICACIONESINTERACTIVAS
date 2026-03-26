package com.uade.tpo.marketplace.repository;

import com.uade.tpo.marketplace.model.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EnrollmentRepository {

    private final List<Enrollment> enrollments = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public List<Enrollment> findAll() {
        return enrollments;
    }

    public List<Enrollment> findByStudentId(Long studentId) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }

    public Enrollment save(Enrollment enrollment) {
        if (enrollment.getId() == null) {
            enrollment.setId(nextId.getAndIncrement());
            enrollments.add(enrollment);
            return enrollment;
        }

        enrollments.removeIf(currentEnrollment -> currentEnrollment.getId().equals(enrollment.getId()));
        enrollments.add(enrollment);
        return enrollment;
    }
}
