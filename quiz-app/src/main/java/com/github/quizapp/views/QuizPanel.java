package com.github.quizapp.views;

import com.github.quizapp.models.AnswerOption;
import com.github.quizapp.support.AppConfig;

import javax.swing.*;
import java.awt.*;

/**
 * The QuizPanel class is a custom JPanel that displays relevant quiz data and
 * a question with the QuestionPanel class.
 * @author Ottilia Eriksson
 */
public class QuizPanel extends JPanel {

    /** Label for displaying the name of the quiz. */
    private final JLabel quizNameLabel;
    /** Label for displaying the number of questions in the quiz. */
    private final JLabel numQuestionsLabel;
    /** Label for displaying the current score in the quiz. */
    private final JLabel scoreLabel;
    /** Button for exiting the quiz. */
    private final JButton exitButton;
    /** Panel for displaying a question and answer options. */
    private final QuestionPanel questionPanel;

    /**
     * Constructs a new QuizPanel with the specified quiz details.
     * @param quizName The name of the quiz.
     * @param numberOfQuestions The number of questions in the quiz.
     * @param initialQuestionText The text of the initial question.
     * @param initialAnswerOptions The initial answer options for the question.
     */
    public QuizPanel(final String quizName, final int numberOfQuestions,
                     final String initialQuestionText,
                     final AnswerOption[] initialAnswerOptions) {
        setLayout(new BorderLayout());

        // Create a panel for holding the quizNameLabel and exitButton
        final JPanel northPanel = new JPanel(new BorderLayout());

        // Initialize and add quizNameLabel
        this.quizNameLabel = new JLabel(quizName, SwingConstants.CENTER);
        quizNameLabel.setFont(AppConfig.QUIZ_NAME_FONT);
        northPanel.add(quizNameLabel, BorderLayout.CENTER);

        // Initialize and add exitButton
        exitButton = new JButton("Exit");
        northPanel.add(exitButton, BorderLayout.WEST);

        // Initialize and add scoreLabel
        this.scoreLabel = new JLabel("Score: 0"); // Initial score is 0
        scoreLabel.setFont(AppConfig.SCORE_FONT);
        northPanel.add(scoreLabel, BorderLayout.EAST);

        // Initialize and add numQuestionsLabel
        this.numQuestionsLabel = new JLabel(
                numberOfQuestions + " Questions",
                SwingConstants.CENTER
        );
        numQuestionsLabel.setFont(AppConfig.NUM_QUESTIONS_FONT);
        northPanel.add(numQuestionsLabel, BorderLayout.SOUTH);

        // Add northPanel to the NORTH position of the main panel
        add(northPanel, BorderLayout.NORTH);

        // Initialize and add questionPanel
        this.questionPanel = new QuestionPanel(initialQuestionText,
                initialAnswerOptions);
        add(questionPanel, BorderLayout.CENTER);

    }

    /**
     * Updates the displayed score.
     * @param newScore The new score to display.
     */
    public void updateScore(final int newScore) {
        scoreLabel.setText("Score: " + newScore);
    }

    /**
     * Retrieves the QuestionPanel.
     * @return The QuestionPanel.
     */
    public QuestionPanel getQuestionPanel() {
        return questionPanel;
    }

    /**
     * Retrieves the name of the quiz.
     * @return The name of the quiz.
     */
    public String getQuizName() {
        return quizNameLabel.getText();
    }

    /**
     * Retrieves the number of questions in the quiz.
     * @return The number of questions.
     */
    public int getNumQuestions() {
        final String labelText = this.numQuestionsLabel.getText();
        // Remove non-digit characters from the text
        final String numberString = labelText.replaceAll(
                "\\D", ""
        );
        return Integer.parseInt(numberString);
    }

    /**
     * Retrieves the exit button.
     * @return The exit button.
     */
    public JButton getExitButton() {
        return exitButton;
    }

    /**
     * Retrieves the text displayed in the score label.
     * @return The text displayed in the score label.
     */
    public String getScoreLabelText() {
        return scoreLabel.getText();
    }
}
