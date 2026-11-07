package com.example.tutoring_center.entity;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classes")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private Integer grade;
    @ManyToOne
    @JoinColumn(name = "shift_id")
    private Shift shift;
    @OneToMany(mappedBy = "studentClass", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments =  new ArrayList<>();
    @OneToMany(mappedBy = "clazz",cascade = CascadeType.ALL)
    private List<TeacherAssignment> teacherAssignments =  new ArrayList<>();
    private boolean isDeleted = Boolean.FALSE;
    public Class() {}
    public Class(String subject, Integer grade, Shift shift) {
        this.subject = subject;
        this.grade = grade;
        this.shift = shift;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public List<TeacherAssignment> getTeacherAssignments() {
        return teacherAssignments;
    }

    public void setTeacherAssignments(List<TeacherAssignment> teacherAssignments) {
        this.teacherAssignments = teacherAssignments;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
