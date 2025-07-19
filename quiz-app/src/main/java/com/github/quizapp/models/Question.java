package com.github.quizapp.models;

/**
 * Represents a single Question in a quiz, containing the question text
 * and an array of possible answer options.
 *
 * @param questionText The text of the question.
 * @param answers An array of AnswerOption representing possible answers.
 * @author Ottilia Eriksson
 */
public record Question(
        String questionText,
        AnswerOption[] answers
) {
}
