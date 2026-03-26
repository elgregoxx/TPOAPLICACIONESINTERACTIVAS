package com.uade.tpo.marketplace.repository;

import com.uade.tpo.marketplace.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CourseRepository {

    private final List<Course> courses = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public CourseRepository() {
        save(new Course(null, "Matemática básica", "Clase inicial para repasar ecuaciones", 60, 2L));
    }

    public List<Course> findAll() {
        return courses;
    }

    public Optional<Course> findById(Long id) {
        return courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst();
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            course.setId(nextId.getAndIncrement());
            courses.add(course);
            return course;
        }

        deleteById(course.getId());
        courses.add(course);
        return course;
    }

    public void deleteById(Long id) {
        courses.removeIf(course -> course.getId().equals(id));
    }
}
