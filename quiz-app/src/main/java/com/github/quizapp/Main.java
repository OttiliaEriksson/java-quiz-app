package com.github.quizapp;

import com.github.quizapp.controllers.QuizController;

import javax.swing.*;

/**
 * Entry point of the quiz application.
 * @author Ottilia Eriksson
 */
public class Main {

    /**
     * The main method of the application launches the quiz controller
     * within the Swing event dispatch thread.
     * @param args command arguments.
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(QuizController::new);
    }
}