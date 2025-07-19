package com.github.quizapp.support;

import java.awt.*;

/**
 * The AppConfig class provides a collection of constants and help methods
 * which are used throughout the application.
 * @author Ottilia Eriksson
 */
public class AppConfig {
    /** Prohibit instantiation. */
    private AppConfig() {
        throw new IllegalStateException("Utility class");
    }

    /** Number of answer options. */
    public static final int NUM_ANSWER_OPTIONS = 4;

    /** Font for the question text. */
    public static final Font QUESTION_FONT =
            new Font("Monaco", Font.BOLD, 20);

    /** Font for displaying the name of the quiz. */
    public static final Font QUIZ_NAME_FONT =
            new Font("Monaco", Font.BOLD, 22);

    /** Font for the score label. */
    public static final Font SCORE_FONT =
            new Font("Monaco", Font.BOLD, 15);

    /** Font for the number of questions label. */
    public static final Font NUM_QUESTIONS_FONT =
            new Font("Monaco", Font.BOLD, 12);

    /** Font for the answer option text. */
    public static final Font ANSWER_OPTION_FONT =
            new Font("Monaco", Font.BOLD, 16);

    /** Font for the quiz buttons to display available quizzes */
    public static final Font QUIZ_BUTTONS_FONT =
            new Font("Monaco", Font.BOLD, 15);

    /** Font for the start button to start a selected quiz */
    public static final Font START_BUTTON_FONT =
            new Font("Monaco", Font.BOLD, 15);

    /** Number of rows in the grid layout for answer option buttons. */
    public static final int OPTION_BUTTON_GRID_ROWS = 2;

    /** Number of columns in the grid layout for answer option buttons. */
    public static final int OPTION_BUTTON_GRID_COLS = 2;

    /** Size of the frame width */
    public static final int FRAME_WIDTH = 500;

    /** Size of the frame height */
    public static final int FRAME_HEIGHT = 400;

    /** The text for user instructions */
    public static final String USER_INSTRUCTIONS = """
                Instructions:
                1. All available quizzes are displayed at the start of the
                   application.
                2. Select a quiz by clicking on one of the available quizzes to
                   see more information.
                3. Start the selected quiz by clicking on the start button.
                4. Answer each question correctly to receive points.
                5. The quiz ends when all questions have been answered or by
                   exiting the quiz by clicking on the exit button.
                6. Have fun!""";

    /** The preferred size for quiz buttons to display available quizzes */
    public static Dimension QUIZ_BUTTON_SIZE =
            new Dimension(150, 100);
}
