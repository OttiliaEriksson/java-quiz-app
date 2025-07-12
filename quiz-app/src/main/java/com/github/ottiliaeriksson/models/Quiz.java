package com.github.ottiliaeriksson.models;

import java.util.Deque;

/**
 * Represents a Quiz consisting of a name, the number of questions,
 * and a collection of questions stored in a deque.
 *
 * @param name The name of the quiz.
 * @param nrOfQuestions The total number of questions in the quiz.
 * @param questions A deque containing the questions in the quiz.
 * @author Ottilia Eriksson
 */
public record Quiz(
        String name,
        Integer nrOfQuestions,
        Deque<Question> questions
) {
}
