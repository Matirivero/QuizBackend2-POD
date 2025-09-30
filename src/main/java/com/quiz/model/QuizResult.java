package com.quiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizResult {
    private Long id;
    private Long quizId;
    private String quizTitle;
    private String category;
    private String userId;
    private int totalScore;
    private int maxScore;
    private double percentage;
    private LocalDateTime completedAt;
    private List<QuestionResult> questionResults;
}