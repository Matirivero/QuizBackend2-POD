package com.quiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizSubmission {
    private Long quizId;
    private String userId;
    private Map<Long, String> answers; // questionId -> respuesta del usuario
}
