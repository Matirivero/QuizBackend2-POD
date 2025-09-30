package com.quiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStats {
    private String userId;
    private int totalQuizzesTaken;
    private double averageScore;
    private Map<String, CategoryStats> categoryStats;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryStats {
        private String category;
        private int quizzesTaken;
        private double averageScore;
        private int totalPoints;
    }
}
