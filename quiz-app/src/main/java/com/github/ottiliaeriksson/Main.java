package com.github.ottiliaeriksson;

import com.github.ottiliaeriksson.controllers.QuizController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(QuizController::new);
    }
}