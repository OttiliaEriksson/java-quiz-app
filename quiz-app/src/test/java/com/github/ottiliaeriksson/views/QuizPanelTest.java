package com.github.ottiliaeriksson.views;

import com.github.ottiliaeriksson.models.AnswerOption;
import com.github.ottiliaeriksson.support.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the QuizPanel class.
 * @author Ottilia Eriksson
 */
public class QuizPanelTest {

    private QuizPanel quizPanel;
    private final int SAMPLE_NR_OF_QUESTIONS = 10;

    /**
     * Set up the QuizPanel instance before each test.
     */
    @BeforeEach
    public void setUp() {
        // Initialize QuizPanel before each test
        final AnswerOption[] answerOptions = {new AnswerOption("1", true),
                new AnswerOption("2", false),
                new AnswerOption("3", false),
                new AnswerOption("4", false)};
        quizPanel = new QuizPanel("Test Quiz", SAMPLE_NR_OF_QUESTIONS,
                "First Question", answerOptions);
    }

    /**
     * Test whether the QuizPanel correctly displays the quiz name.
     */
    @Test
    public void testDisplayQuizName() {
        assertEquals("Test Quiz", quizPanel.getQuizName());
    }

    /**
     * Test whether the QuizPanel correctly displays the number of questions.
     */
    @Test
    public void testDisplayNumQuestions() {
        assertEquals(SAMPLE_NR_OF_QUESTIONS, quizPanel.getNumQuestions());
    }

    /**
     * Test whether the QuizPanel contains a QuestionPanel.
     */
    @Test
    public void testContainsQuestionPanel() {
        assertNotNull(quizPanel.getQuestionPanel());
    }

    /**
     * Test whether the QuizPanel contains an exit button.
     */
    @Test
    public void testContainsExitButton() {
        assertNotNull(quizPanel.getExitButton());
    }

    /**
     * Test whether the QuizPanel correctly displays the initial score.
     */
    @Test
    public void testInitialScoreDisplay() {
        assertEquals("Score: 0", quizPanel.getScoreLabelText());
    }

    /**
     * Test whether the QuizPanel correctly updates the score.
     */
    @Test
    public void testScoreUpdate() {
        quizPanel.updateScore(10); // Update score to 10
        assertEquals("Score: 10", quizPanel.getScoreLabelText());
    }

    /**
     * Test whether the QuestionPanel can be updated with new question text.
     */
    @Test
    public void testUpdateQuestionText() {
        final String newQuestionText = "Second Question";
        quizPanel.getQuestionPanel().updateQuestion(
                newQuestionText,
                new String[]{"5", "6", "7", "8"}
        );

        assertEquals(
                newQuestionText,
                quizPanel.getQuestionPanel().getQuestionLabelText()
        );
    }

    /**
     * Test whether the QuestionPanel can be updated with new answer options.
     */
    @Test
    public void testUpdateAnswerOptions() {
        final String[] newAnswerOptions = {"5", "6", "7", "8"};
        quizPanel.getQuestionPanel().updateQuestion(
                "Second Question",
                newAnswerOptions
        );

        final String[] answerOptions = new String[AppConfig.NUM_ANSWER_OPTIONS];
        for (int i = 0; i < AppConfig.NUM_ANSWER_OPTIONS; i++) {
            answerOptions[i] = quizPanel.getQuestionPanel().
                    getOptionButtonText(i);
        }
        assertArrayEquals(newAnswerOptions, answerOptions);
    }
}
