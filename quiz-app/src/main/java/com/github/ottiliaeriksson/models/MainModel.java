package com.github.ottiliaeriksson.models;

import com.github.ottiliaeriksson.support.QuizParser;

public class MainModel {

    private Quiz quiz;
    private Question activeQuestion;
    private int points;

    /**
     * Constructor that initializes points to zero.
     */
    public MainModel() {
        points = 0;
    }

    /**
     * This method retrieves the names of all available quizzes.
     * @return String array of all quiz names.
     */
    public String[] getAvailableQuizzes() {
        return QuizParser.getAllQuizNames();
    }

    /**
     * Resets the quiz game by clearing the active question, current quiz,
     * and resetting points.
     */
    public void reset() {
        activeQuestion = null;
        quiz = null;
        points = 0;
    }

    /**
     * Loads a new quiz based on the specified quiz name. Retrieves the fully
     * constructed Quiz object from the parser.
     * @param quizName The name of the quiz to load.
     */
    public void loadQuiz(final String quizName) {
        this.quiz = QuizParser.parseQuiz(quizName);
    }

    /**
     * Advances to the next question in the quiz by polling from the deque.
     */
    public void changeQuestion() {
        if (quiz != null) {
            activeQuestion = quiz.questions().pollFirst(); // or .poll()
        }
    }

    /**
     * Returns the number of questions left in the quiz.
     * @return The number of questions left or -1 if quiz is null.
     */
    public int getQuestionsLeft() {
        if (quiz == null) return -1;
        return quiz.questions().size();
    }

    /**
     * Checks if the provided answer is the correct answer for the active
     * question.
     * @param answer The answer to check.
     * @return true if the answer is correct, and false otherwise.
     */
    public Boolean checkRightAnswer(final String answer) {
        for (AnswerOption option : activeQuestion.answers()) {
            if (option.isCorrect() && option.answerText().equals(answer)) {
                points++;
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the current points accumulated by the player.
     * @return The number of points or -1 if there's no active question.
     */
    public int getPoints() {
        if (activeQuestion == null)  return -1;
        return points;
    }
}
