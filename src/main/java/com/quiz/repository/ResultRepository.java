package com.quiz.repository;

import com.quiz.model.QuizResult;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class ResultRepository {
    private Map<Long, QuizResult> results = new ConcurrentHashMap<>();
    private Long idCounter = 1L;

    public QuizResult save(QuizResult result) {
        result.setId(idCounter++);
        results.put(result.getId(), result);
        return result;
    }

    public List<QuizResult> findByUserId(String userId) {
        return results.values().stream()
                .filter(r -> r.getUserId().equals(userId))
                .sorted((a, b) -> b.getCompletedAt().compareTo(a.getCompletedAt()))
                .collect(Collectors.toList());
    }

    public List<QuizResult> findAll() {
        return new ArrayList<>(results.values());
    }

    public Optional<QuizResult> findById(Long id) {
        return Optional.ofNullable(results.get(id));
    }
}
