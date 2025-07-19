package com.github.quizapp.models;

/**
 * Represents a single answer option for a quiz question.
 *
 * @param answerText The text of the answer option.
 * @param isCorrect Boolean flag indicating whether this answer is correct.
 * @author Ottilia Eriksson
 */
public record AnswerOption(
        String answerText,
        Boolean isCorrect
) {
}
