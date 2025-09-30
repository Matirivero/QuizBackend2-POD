package com.quiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private Long id;
    private String text;
    private QuestionType type;
    private List<String> options; // Para multiple choice
    private String correctAnswer;
    private int points;
    private String messi;
    public enum QuestionType {
        TRUE_FALSE,
        MULTIPLE_CHOICE
    }
}
