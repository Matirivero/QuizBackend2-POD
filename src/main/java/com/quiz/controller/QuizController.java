package com.quiz.controller;

import com.quiz.model.*;
import com.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    // Obtener todos los cuestionarios disponibles
    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    // Obtener cuestionarios por categoría
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Quiz>> getQuizzesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(quizService.getQuizzesByCategory(category));
    }

    // Obtener un cuestionario específico con todas sus preguntas
    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        return quizService.getQuizById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Enviar respuestas y obtener resultado
    @PostMapping("/submit")
    public ResponseEntity<QuizResult> submitQuiz(@RequestBody QuizSubmission submission) {
        try {
            QuizResult result = quizService.submitQuiz(submission);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Obtener historial de resultados de un usuario
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<QuizResult>> getUserHistory(@PathVariable String userId) {
        return ResponseEntity.ok(quizService.getUserHistory(userId));
    }

    // Obtener estadísticas del usuario
    @GetMapping("/stats/{userId}")
    public ResponseEntity<UserStats> getUserStats(@PathVariable String userId) {
        return ResponseEntity.ok(quizService.getUserStats(userId));
    }
}
