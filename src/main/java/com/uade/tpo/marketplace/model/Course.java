package com.uade.tpo.marketplace.model;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer durationInMinutes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

    public Course() {}

    public Course(Long id, String title, String description, Integer durationInMinutes, User teacher) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.durationInMinutes = durationInMinutes;
        this.teacher = teacher;
    }

    public static CourseBuilder builder() { return new CourseBuilder(); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getDurationInMinutes() { return durationInMinutes; }
    public void setDurationInMinutes(Integer durationInMinutes) { this.durationInMinutes = durationInMinutes; }
    public User getTeacher() { return teacher; }
    public void setTeacher(User teacher) { this.teacher = teacher; }

    public static class CourseBuilder {
        private Long id;
        private String title;
        private String description;
        private Integer durationInMinutes;
        private User teacher;

        public CourseBuilder id(Long id) { this.id = id; return this; }
        public CourseBuilder title(String title) { this.title = title; return this; }
        public CourseBuilder description(String description) { this.description = description; return this; }
        public CourseBuilder durationInMinutes(Integer d) { this.durationInMinutes = d; return this; }
        public CourseBuilder teacher(User teacher) { this.teacher = teacher; return this; }
        public Course build() { return new Course(id, title, description, durationInMinutes, teacher); }
    }
}
