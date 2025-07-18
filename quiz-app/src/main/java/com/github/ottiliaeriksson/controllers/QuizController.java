package com.github.ottiliaeriksson.controllers;

import com.github.ottiliaeriksson.models.MainModel;
import com.github.ottiliaeriksson.views.MainView;

import javax.swing.*;

/**
 * The controller class is responsible for managing the interaction between
 * the model and the view in the quiz application.
 * @author Ottilia Eriksson
 */
public class QuizController {
    private final MainModel model = new MainModel();
    private final MainView view;

    /**
     * Constructs a new QuizController object and initializes the view.
     */
    public QuizController() {

        // Initialize the view with a reference to this controller
        this.view = new MainView(this::startQuiz);

        // Display initial content in the main view
        displayMainView();
    }

    /**
     * Starts a quiz based on the selected quiz name.
     * @param quizName The name of the quiz to start.
     */
    void startQuiz(final String quizName) {
        // Load the quiz with questions into the model
        model.loadQuiz(quizName);

        // Get the first question of the quiz
        model.changeQuestion();

        // Display the QuizPanel
        view.displayQuizPanel(quizName, model.getNumberOfQuestions(quizName),
                model.getCurrentQuestion().questionText(),
                model.getCurrentQuestion().answers());

        // Add listener to answer option buttons
        final JButton[] answerButtons = view.getQuizPanel().
                getQuestionPanel().getOptionButtons();
        for (JButton button : answerButtons) {
            button.addActionListener(e -> handleUserAnswer(button.getText()));
        }

        // Add listener to the exit button
        final JButton exitButton = view.getQuizPanel().getExitButton();
        exitButton.addActionListener(e -> {
            // reset values
            model.reset();
            // Display main view again
            displayMainView();
        });
    }

    /**
     * Handles user's answer selection in the quiz for each question.
     * @param answer The answer that the user selected.
     */
    private void handleUserAnswer(final String answer) {
        // Update score if answer is correct
        if(model.checkRightAnswer(answer)) {
            view.getQuizPanel().updateScore(model.getPoints());
        }

        // Change to the next question if there are any left
        if(model.getQuestionsLeft() > 0) {
            // Change to next question
            model.changeQuestion();
            // Update Question panel with the next question
            view.getQuizPanel().getQuestionPanel().
                    updateQuestion(model.getCurrentQuestion().questionText(),
                            model.getCurrentQuestion().answers());
        } else {
            // Display final result if no more questions left
            view.displayFinalResult(model.getPoints());
            // Display main view again
            displayMainView();
        }
    }

    /**
     * Displays the initial content in the view.
     */
    private void displayMainView() {
        // Display available quizzes in the view
        view.displayQuizzes(model.getAvailableQuizzes());


        // Listen for user input from quiz buttons
        final JButton[] quizButtons = view.getQuizButtons();
        // Add action listeners to quiz buttons
        for (JButton button : quizButtons) {
            button.addActionListener(e ->
                    handleQuizSelection(button.getText())
            );
        }
    }

    /**
     * Handles the selection of a quiz by displaying its preview.
     * @param quizName The name of the quiz selected by the user.
     */
    void handleQuizSelection(final String quizName) {
        // Display a preview of the quiz with the name and number of questions
        view.displayQuizPreview(quizName,
                model.getNumberOfQuestions(quizName));

    }
}
