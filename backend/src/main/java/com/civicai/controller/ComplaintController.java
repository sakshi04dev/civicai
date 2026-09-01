package com.civicai.controller;

import com.civicai.Complaint;
import com.civicai.repository.ComplaintRepository;
import com.civicai.service.AIService;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin
public class ComplaintController {

    private final ComplaintRepository repository;
    private final AIService aiService;

    public ComplaintController(
            ComplaintRepository repository,
            AIService aiService) {

        this.repository = repository;
        this.aiService = aiService;
    }


    // Test the backend
    @GetMapping("/test")
    public String test() {

        return "CivicAI Backend is working!";
    }


    // Get all complaints
    @GetMapping
    public List<Complaint> getAllComplaints() {

        return repository
                .findAllByOrderByCreatedAtDesc();
    }


    // Submit a complaint
    @PostMapping
    public Complaint createComplaint(
            @RequestBody Complaint complaint) {

        Map<String, Object> result =
                aiService.analyzeComplaint(
                        complaint.getTitle(),
                        complaint.getDescription()
                );

        complaint.setCategory(
                (String) result.get("category")
        );

        complaint.setDepartment(
                (String) result.get("department")
        );

        complaint.setPriority(
                (String) result.get("priority")
        );

        return repository.save(complaint);
    }


    // Update complaint status
    @PatchMapping("/{id}/status")
    public Complaint updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        Complaint complaint =
                repository.findById(id)
                        .orElseThrow();

        String status =
                body.getOrDefault(
                        "status",
                        "Submitted"
                );

        complaint.setStatus(status);

        return repository.save(complaint);
    }


    // Dashboard statistics
    @GetMapping("/stats")
    public Map<String, Object> getStatistics() {

        List<Complaint> complaints =
                repository.findAll();

        Map<String, Object> stats =
                new LinkedHashMap<>();

        stats.put(
                "total",
                complaints.size()
        );

        stats.put(
                "highPriority",
                complaints.stream()
                        .filter(c ->
                                "High".equals(
                                        c.getPriority()
                                ))
                        .count()
        );

        stats.put(
                "duplicates",
                complaints.stream()
                        .filter(
                                Complaint::isDuplicateDetected
                        )
                        .count()
        );

        stats.put(
                "resolved",
                complaints.stream()
                        .filter(c ->
                                "Resolved".equals(
                                        c.getStatus()
                                ))
                        .count()
        );

        return stats;
    }
}