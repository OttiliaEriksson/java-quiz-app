package com.github.ottiliaeriksson.views;

import com.github.ottiliaeriksson.support.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class contains unit tests for the QuestionPanel class
 * @author Ottilia Eriksson
 */
public class QuestionPanelTest {

    private QuestionPanel questionPanel;

    /**
     * Sets up the test environment by creating a QuestionPanel
     * instance with sample question and answer options.
     */
    @BeforeEach
    public void setUp() {
        // Set up the QuestionPanel with sample question and answer options
        final String questionText = "What is the capital of France?";
        final String[] answerOptions = {
                "Paris", "London", "Berlin", "Copenhagen"
        };
        questionPanel = new QuestionPanel(questionText, answerOptions);
    }

    /**
     * Tests the initialization of the QuestionPanel, verifying that the
     * question label text is set correctly during initialization.
     */
    @Test
    public void testQuestionLabelTextInitialization() {
        assertEquals("What is the capital of France?",
                questionPanel.getQuestionLabelText());
    }

    /**
     * Tests the initialization of option buttons in the QuestionPanel,
     * verifying that each option button is initialized with the correct
     * text.
     */
    @Test
    public void testOptionButtonTextInitialization() {
        final String[] expectedOptions = {
                "Paris", "London", "Berlin", "Copenhagen"
        };
        final String[] actualOptions = new String[AppConfig.NUM_ANSWER_OPTIONS];

        for (int i = 0; i < AppConfig.NUM_ANSWER_OPTIONS; i++) {
            actualOptions[i] = questionPanel.getOptionButtonText(i);
        }

        assertArrayEquals(expectedOptions, actualOptions);
    }

    /**
     * Tests the updating mechanism of the QuestionPanel for the question label
     * text by updating the panel with a new question and answer options
     * before checking if the question label is correctly updated.
     */
    @Test
    public void testUpdateQuestionLabelText() {
        // Update the panel with a new question
        final String newQuestionText = "What is the capital of Germany?";
        final String[] newAnswerOptions = {"Paris", "Berlin", "Rome", "Oslo"};
        questionPanel.updateQuestion(newQuestionText, newAnswerOptions);

        // Check if the question label is correctly updated
        assertEquals(newQuestionText, questionPanel.getQuestionLabelText());
    }


    /**
     * Tests the updating mechanism of the QuestionPanel for option button
     * texts by updating the panel with a new question and answer options
     * before checking if the text for each option button is updated correctly.
     */
    @Test
    public void testUpdateOptionButtonText() {

        // Update the panel with a new question
        final String newQuestionText = "What is the capital of Germany?";
        final String[] newAnswerOptions = {"Paris", "Berlin", "Rome", "Oslo"};
        questionPanel.updateQuestion(newQuestionText, newAnswerOptions);

        // Check if the text for each option button is updated correctly
        final String[] actualOptions = new String[AppConfig.NUM_ANSWER_OPTIONS];
        for (int i = 0; i < AppConfig.NUM_ANSWER_OPTIONS; i++) {
            actualOptions[i] = questionPanel.getOptionButtonText(i);
        }
        assertArrayEquals(newAnswerOptions, actualOptions);
    }
}
