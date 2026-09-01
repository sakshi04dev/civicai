package com.civicai.service;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 3000)
    private String description;

    private String location;

    private String language;

    private String category;

    private String department;

    private String priority;

    private String status;

    private boolean duplicateDetected;

    private Double duplicateScore;

    private LocalDateTime createdAt;


    public Complaint() {

        this.createdAt = LocalDateTime.now();

        this.status = "Submitted";
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getLanguage() {
        return language;
    }

    public String getCategory() {
        return category;
    }

    public String getDepartment() {
        return department;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public boolean isDuplicateDetected() {
        return duplicateDetected;
    }

    public Double getDuplicateScore() {
        return duplicateScore;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDuplicateDetected(boolean duplicateDetected) {
        this.duplicateDetected = duplicateDetected;
    }

    public void setDuplicateScore(Double duplicateScore) {
        this.duplicateScore = duplicateScore;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}