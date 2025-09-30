package com.quiz.service;

import com.quiz.model.*;
import com.quiz.repository.QuizRepository;
import com.quiz.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ResultRepository resultRepository;

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll().stream()
                .map(this::createQuizSummary)
                .collect(Collectors.toList());
    }

    private Quiz createQuizSummary(Quiz quiz) {
        Quiz summary = new Quiz();
        summary.setId(quiz.getId());
        summary.setTitle(quiz.getTitle());
        summary.setCategory(quiz.getCategory());
        summary.setDescription(quiz.getDescription());
        summary.setTimeLimit(quiz.getTimeLimit());
        summary.setQuestions(null); // No enviamos las preguntas en el listado
        return summary;
    }

    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    public List<Quiz> getQuizzesByCategory(String category) {
        return quizRepository.findByCategory(category).stream()
                .map(this::createQuizSummary)
                .collect(Collectors.toList());
    }

    public QuizResult submitQuiz(QuizSubmission submission) {
        Quiz quiz = quizRepository.findById(submission.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz no encontrado"));

        List<QuestionResult> questionResults = new ArrayList<>();
        int totalScore = 0;
        int maxScore = 0;

        for (Question question : quiz.getQuestions()) {
            String userAnswer = submission.getAnswers().get(question.getId());
            boolean isCorrect = false;
            int pointsEarned = 0;

            if (userAnswer != null && userAnswer.equals(question.getCorrectAnswer())) {
                isCorrect = true;
                pointsEarned = question.getPoints();
                totalScore += pointsEarned;
            }

            maxScore += question.getPoints();

            QuestionResult qr = new QuestionResult(
                    question.getId(),
                    question.getText(),
                    userAnswer != null ? userAnswer : "Sin respuesta",
                    question.getCorrectAnswer(),
                    isCorrect,
                    pointsEarned
            );
            questionResults.add(qr);
        }

        double percentage = maxScore > 0 ? (double) totalScore / maxScore * 100 : 0;

        QuizResult result = new QuizResult();
        result.setQuizId(quiz.getId());
        result.setQuizTitle(quiz.getTitle());
        result.setCategory(quiz.getCategory());
        result.setUserId(submission.getUserId());
        result.setTotalScore(totalScore);
        result.setMaxScore(maxScore);
        result.setPercentage(percentage);
        result.setCompletedAt(LocalDateTime.now());
        result.setQuestionResults(questionResults);

        return resultRepository.save(result);
    }

    public List<QuizResult> getUserHistory(String userId) {
        return resultRepository.findByUserId(userId);
    }

    public UserStats getUserStats(String userId) {
        List<QuizResult> userResults = resultRepository.findByUserId(userId);

        if (userResults.isEmpty()) {
            return new UserStats(userId, 0, 0.0, new HashMap<>());
        }

        Map<String, List<QuizResult>> resultsByCategory = userResults.stream()
                .collect(Collectors.groupingBy(QuizResult::getCategory));

        Map<String, UserStats.CategoryStats> categoryStatsMap = new HashMap<>();

        for (Map.Entry<String, List<QuizResult>> entry : resultsByCategory.entrySet()) {
            String category = entry.getKey();
            List<QuizResult> categoryResults = entry.getValue();

            double avgScore = categoryResults.stream()
                    .mapToDouble(QuizResult::getPercentage)
                    .average()
                    .orElse(0.0);

            int totalPoints = categoryResults.stream()
                    .mapToInt(QuizResult::getTotalScore)
                    .sum();

            UserStats.CategoryStats catStats = new UserStats.CategoryStats(
                    category,
                    categoryResults.size(),
                    avgScore,
                    totalPoints
            );

            categoryStatsMap.put(category, catStats);
        }

        double overallAverage = userResults.stream()
                .mapToDouble(QuizResult::getPercentage)
                .average()
                .orElse(0.0);

        return new UserStats(
                userId,
                userResults.size(),
                overallAverage,
                categoryStatsMap
        );
    }
}
