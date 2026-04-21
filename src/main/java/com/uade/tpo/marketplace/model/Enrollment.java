package com.uade.tpo.marketplace.model;

import jakarta.persistence.*;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private String status;

    public Enrollment() {}

    public Enrollment(Long id, User student, Course course, String status) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.status = status;
    }

    public static EnrollmentBuilder builder() { return new EnrollmentBuilder(); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getStudent() { return student; }
    public void setStudent(User student) { this.student = student; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public static class EnrollmentBuilder {
        private Long id;
        private User student;
        private Course course;
        private String status;

        public EnrollmentBuilder id(Long id) { this.id = id; return this; }
        public EnrollmentBuilder student(User student) { this.student = student; return this; }
        public EnrollmentBuilder course(Course course) { this.course = course; return this; }
        public EnrollmentBuilder status(String status) { this.status = status; return this; }
        public Enrollment build() { return new Enrollment(id, student, course, status); }
    }
}
