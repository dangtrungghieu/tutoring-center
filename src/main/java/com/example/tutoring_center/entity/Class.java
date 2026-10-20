package com.example.tutoring_center.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

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
}
