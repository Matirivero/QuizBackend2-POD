package com.quiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
    private Long id;
    private String title;
    private String category;
    private String description;
    private List<Question> questions;
    private int timeLimit;

    public enum QuizStatus {
        BORRADOR,
        PUBLICADO,
        CERRADO;

        public QuizStatus siguiente() {
            if (this == BORRADOR) return PUBLICADO;
            if (this == PUBLICADO) return CERRADO;
            return CERRADO;
        }
    }

    private QuizStatus estado = QuizStatus.BORRADOR;

    public void avanzarEstado() {
        if (this.estado == null) this.estado = QuizStatus.BORRADOR;
        this.estado = this.estado.siguiente();
    }
}