package com.civicai;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AIService {

    // ==========================================
    // MAIN AI ANALYSIS
    // ==========================================

    public Map<String, Object> analyzeComplaint(
            String title,
            String description) {

        String text =
                (title + " " + description)
                        .toLowerCase();


        Map<String, Object> result =
                new LinkedHashMap<>();


        // 1. CLASSIFICATION

        String category =
                classifyCategory(text);

        result.put(
                "category",
                category
        );


        // 2. DEPARTMENT

        String department =
                findDepartment(category);

        result.put(
                "department",
                department
        );


        // 3. PRIORITY

        String priority =
                calculatePriority(text);

        result.put(
                "priority",
                priority
        );


        // 4. AI EXPLANATION

        String explanation =
                generateExplanation(
                        category,
                        priority
                );

        result.put(
                "explanation",
                explanation
        );


        return result;
    }


    // ==========================================
    // CLASSIFICATION
    // ==========================================

    private String classifyCategory(
            String text) {


        if (contains(
                text,
                "water",
                "pipe",
                "leak",
                "water supply")) {

            return "Water Supply";
        }


        if (contains(
                text,
                "garbage",
                "waste",
                "dustbin",
                "sanitation")) {

            return "Sanitation";
        }


        if (contains(
                text,
                "road",
                "pothole",
                "street",
                "footpath")) {

            return "Roads & Infrastructure";
        }


        if (contains(
                text,
                "electricity",
                "electric",
                "streetlight",
                "light")) {

            return "Electricity / Street Lighting";
        }


        if (contains(
                text,
                "traffic",
                "signal",
                "parking")) {

            return "Traffic";
        }


        if (contains(
                text,
                "drain",
                "drainage",
                "sewage",
                "sewer")) {

            return "Drainage & Sewage";
        }


        return "Other";
    }


    // ==========================================
    // DEPARTMENT ROUTING
    // ==========================================

    private String findDepartment(
            String category) {


        switch (category) {

            case "Water Supply":
                return "Water Department";


            case "Sanitation":
                return "Sanitation Department";


            case "Roads & Infrastructure":
                return "Public Works Department";


            case "Electricity / Street Lighting":
                return "Electricity Department";


            case "Traffic":
                return "Traffic Department";


            case "Drainage & Sewage":
                return "Drainage Department";


            default:
                return "General Grievance Department";
        }
    }


    // ==========================================
    // PRIORITY
    // ==========================================

    private String calculatePriority(
            String text) {


        // HIGH PRIORITY

        if (contains(
                text,
                "emergency",
                "urgent",
                "danger",
                "accident",
                "fire",
                "unsafe",
                "life threatening")) {

            return "High";
        }


        // MEDIUM PRIORITY

        if (contains(
                text,
                "broken",
                "blocked",
                "overflow",
                "dark",
                "weeks",
                "week")) {

            return "Medium";
        }


        // DEFAULT

        return "Low";
    }


    // ==========================================
    // AI EXPLANATION
    // ==========================================

    private String generateExplanation(
            String category,
            String priority) {


        return "AI classified this complaint "
                + "as "
                + category
                + " and assigned "
                + priority
                + " priority based on "
                + "the complaint description.";
    }


    // ==========================================
    // KEYWORD CHECK
    // ==========================================

    private boolean contains(
            String text,
            String... words) {


        for (String word : words) {

            if (text.contains(word)) {

                return true;
            }
        }


        return false;
    }
}