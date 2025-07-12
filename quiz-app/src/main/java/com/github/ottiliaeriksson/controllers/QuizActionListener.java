package com.github.ottiliaeriksson.controllers;

/**
 * A functional interface for listening to quiz-related actions.
 * @author Ottilia Eriksson
 */
@FunctionalInterface
public interface QuizActionListener {

    /**
     * Invoked when a quiz-related action occurs.
     * @param quizName The name of the quiz associated with the action.
     */
    void onQuizAction(String quizName);
}
