package com.quiz.repository;

import com.quiz.model.Quiz;
import com.quiz.model.Question;
import com.quiz.model.Question.QuestionType;
import org.springframework.stereotype.Repository;
import jakarta.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class QuizRepository {
    private Map<Long, Quiz> quizzes = new HashMap<>();
    private Long idCounter = 1L;

    @PostConstruct
    public void initData() {
        // Quiz de Deportes
        Quiz deportes = new Quiz();
        deportes.setId(idCounter++);
        deportes.setTitle("Conocimientos de Deportes");
        deportes.setCategory("Deportes");
        deportes.setDescription("Pon a prueba tus conocimientos sobre deportes mundiales");
        deportes.setTimeLimit(10);
        deportes.setQuestions(Arrays.asList(
                new Question(1L, "¿Brasil ha ganado 5 Copas del Mundo de fútbol?",
                        QuestionType.TRUE_FALSE, null, "Verdadero", 10),
                new Question(2L, "¿Cuántos jugadores hay en un equipo de baloncesto en la cancha?",
                        QuestionType.MULTIPLE_CHOICE,
                        Arrays.asList("4", "5", "6", "7"), "5", 10),
                new Question(3L, "¿Michael Jordan jugó toda su carrera en los Chicago Bulls?",
                        QuestionType.TRUE_FALSE, null, "Falso", 10),
                new Question(4L, "¿En qué año se celebraron los Juegos Olímpicos de Beijing?",
                        QuestionType.MULTIPLE_CHOICE,
                        Arrays.asList("2004", "2008", "2012", "2016"), "2008", 10),
                new Question(5L, "¿El tenis se juega en sets de 6 juegos?",
                        QuestionType.TRUE_FALSE, null, "Verdadero", 10)
        ));
        quizzes.put(deportes.getId(), deportes);

        // Quiz de Historia
        Quiz historia = new Quiz();
        historia.setId(idCounter++);
        historia.setTitle("Historia Universal");
        historia.setCategory("Historia");
        historia.setDescription("Explora los eventos más importantes de la historia");
        historia.setTimeLimit(15);
        historia.setQuestions(Arrays.asList(
                new Question(6L, "¿La Segunda Guerra Mundial terminó en 1945?",
                        QuestionType.TRUE_FALSE, null, "Verdadero", 10),
                new Question(7L, "¿En qué año llegó Cristóbal Colón a América?",
                        QuestionType.MULTIPLE_CHOICE,
                        Arrays.asList("1490", "1491", "1492", "1493"), "1492", 10),
                new Question(8L, "¿La Revolución Francesa comenzó en 1789?",
                        QuestionType.TRUE_FALSE, null, "Verdadero", 10),
                new Question(9L, "¿Quién fue el primer presidente de Estados Unidos?",
                        QuestionType.MULTIPLE_CHOICE,
                        Arrays.asList("Thomas Jefferson", "George Washington", "Abraham Lincoln", "John Adams"),
                        "George Washington", 10),
                new Question(10L, "¿El Imperio Romano cayó en el siglo V?",
                        QuestionType.TRUE_FALSE, null, "Verdadero", 10)
        ));
        quizzes.put(historia.getId(), historia);

        // Quiz de Geografía
        Quiz geografia = new Quiz();
        geografia.setId(idCounter++);
        geografia.setTitle("Geografía Mundial");
        geografia.setCategory("Geografia");
        geografia.setDescription("Conoce el mundo y sus maravillas geográficas");
        geografia.setTimeLimit(12);
        geografia.setQuestions(Arrays.asList(
                new Question(11L, "¿El río Amazonas es el más largo del mundo?",
                        QuestionType.TRUE_FALSE, null, "Falso", 10),
                new Question(12L, "¿Cuál es la capital de Australia?",
                        QuestionType.MULTIPLE_CHOICE,
                        Arrays.asList("Sydney", "Melbourne", "Canberra", "Brisbane"), "Canberra", 10),
                new Question(13L, "¿Europa y Asia están en el mismo continente?",
                        QuestionType.TRUE_FALSE, null, "Verdadero", 10),
                new Question(14L, "¿Cuántos océanos hay en el mundo?",
                        QuestionType.MULTIPLE_CHOICE,
                        Arrays.asList("3", "4", "5", "6"), "5", 10),
                new Question(15L, "¿El desierto del Sahara es el más grande del mundo?",
                        QuestionType.TRUE_FALSE, null, "Falso", 10)
        ));
        quizzes.put(geografia.getId(), geografia);

        // Quiz de Arte
        Quiz arte = new Quiz();
        arte.setId(idCounter++);
        arte.setTitle("Historia del Arte");
        arte.setCategory("Arte");
        arte.setDescription("Descubre las obras maestras y artistas más importantes");
        arte.setTimeLimit(10);
        arte.setQuestions(Arrays.asList(
                new Question(16L, "¿Leonardo da Vinci pintó La Mona Lisa?",
                        QuestionType.TRUE_FALSE, null, "Verdadero", 10),
                new Question(17L, "¿En qué museo se encuentra 'La Noche Estrellada' de Van Gogh?",
                        QuestionType.MULTIPLE_CHOICE,
                        Arrays.asList("Louvre", "MoMA", "Prado", "British Museum"), "MoMA", 10),
                new Question(18L, "¿Pablo Picasso era español?",
                        QuestionType.TRUE_FALSE, null, "Verdadero", 10),
                new Question(19L, "¿Qué movimiento artístico fundó Salvador Dalí?",
                        QuestionType.MULTIPLE_CHOICE,
                        Arrays.asList("Impresionismo", "Cubismo", "Surrealismo", "Expresionismo"),
                        "Surrealismo", 10),
                new Question(20L, "¿La escultura 'David' fue creada por Miguel Ángel?",
                        QuestionType.TRUE_FALSE, null, "Verdadero", 10)
        ));
        quizzes.put(arte.getId(), arte);

        // Quiz de Inglés
        Quiz ingles = new Quiz();
        ingles.setId(idCounter++);
        ingles.setTitle("English Grammar & Vocabulary");
        ingles.setCategory("Ingles");
        ingles.setDescription("Test your English language skills");
        ingles.setTimeLimit(10);
        ingles.setQuestions(Arrays.asList(
                new Question(21L, "Is 'They is happy' grammatically correct?",
                        QuestionType.TRUE_FALSE, null, "Falso", 10),
                new Question(22L, "What is the past tense of 'go'?",
                        QuestionType.MULTIPLE_CHOICE,
                        Arrays.asList("goed", "went", "gone", "going"), "went", 10),
                new Question(23L, "The word 'beautiful' is an adjective?",
                        QuestionType.TRUE_FALSE, null, "Verdadero", 10),
                new Question(24L, "Choose the correct form: 'She ___ to school every day'",
                        QuestionType.MULTIPLE_CHOICE,
                        Arrays.asList("go", "goes", "going", "gone"), "goes", 10),
                new Question(25L, "Is 'I have been working' present perfect continuous?",
                        QuestionType.TRUE_FALSE, null, "Verdadero", 10)
        ));
        quizzes.put(ingles.getId(), ingles);

        // Quiz de Entretenimiento
        Quiz entretenimiento = new Quiz();
        entretenimiento.setId(idCounter++);
        entretenimiento.setTitle("Cine y Entretenimiento");
        entretenimiento.setCategory("Entretenimiento");
        entretenimiento.setDescription("Películas, series y cultura pop");
        entretenimiento.setTimeLimit(10);
        entretenimiento.setQuestions(Arrays.asList(
                new Question(26L, "¿'Titanic' ganó el Oscar a Mejor Película?",
                        QuestionType.TRUE_FALSE, null, "Verdadero", 10),
                new Question(27L, "¿Cuántas películas de Harry Potter hay?",
                        QuestionType.MULTIPLE_CHOICE,
                        Arrays.asList("6", "7", "8", "9"), "8", 10),
                new Question(28L, "¿Marvel y DC son de la misma compañía?",
                        QuestionType.TRUE_FALSE, null, "Falso", 10),
                new Question(29L, "¿Quién dirigió 'Inception'?",
                        QuestionType.MULTIPLE_CHOICE,
                        Arrays.asList("Steven Spielberg", "Christopher Nolan", "Martin Scorsese", "Quentin Tarantino"),
                        "Christopher Nolan", 10),
                new Question(30L, "¿'Friends' tiene 10 temporadas?",
                        QuestionType.TRUE_FALSE, null, "Verdadero", 10)
        ));
        quizzes.put(entretenimiento.getId(), entretenimiento);
    }

    public List<Quiz> findAll() {
        return new ArrayList<>(quizzes.values());
    }

    public Optional<Quiz> findById(Long id) {
        return Optional.ofNullable(quizzes.get(id));
    }

    public List<Quiz> findByCategory(String category) {
        return quizzes.values().stream()
                .filter(q -> q.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}
