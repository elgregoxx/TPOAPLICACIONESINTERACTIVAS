package com.uade.tpo.marketplace.repository;

import com.uade.tpo.marketplace.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
