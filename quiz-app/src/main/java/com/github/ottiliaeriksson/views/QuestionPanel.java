package com.github.ottiliaeriksson.views;

import com.github.ottiliaeriksson.models.AnswerOption;
import com.github.ottiliaeriksson.support.AppConfig;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.GridLayout;

/**
 * The QuestionPanel class represents a panel that displays a question along
 * with multiple answer options as buttons. It extends the JPanel class to
 * provide a customizable panel for displaying a question and answer options.
 * @author Ottilia Eriksson
 */
public class QuestionPanel extends JPanel {

    /**
     * Label to display the question.
     */
    private final JLabel questionLabel;
    /**
     * Array of buttons representing answer options.
     */
    private final JButton[] optionButtons;

    /**
     * Constructs a new QuestionPanel with the specified question text and
     * answer options.
     * @param questionText The text of the question to be displayed.
     * @param answerOptions An array of strings representing answer options.
     */
    public QuestionPanel(final String questionText,
                         final AnswerOption[] answerOptions) {

        // Set panel layout to BorderLayout
        setLayout(new BorderLayout());

        // Initialize question label and add it to the panel's north position
        questionLabel = new JLabel(questionText);
        // Center label horizontally
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setFont(AppConfig.QUESTION_FONT); // Set font
        add(questionLabel, BorderLayout.NORTH);

        // Initialize option buttons and add them to the panel's center position
        final JPanel optionsPanel = new JPanel(new GridLayout(
                AppConfig.OPTION_BUTTON_GRID_ROWS,
                AppConfig.OPTION_BUTTON_GRID_COLS));
        add(optionsPanel, BorderLayout.CENTER);

        optionButtons = new JButton[AppConfig.NUM_ANSWER_OPTIONS];

        // Create and add buttons for each answer option
        for (int i = 0; i < AppConfig.NUM_ANSWER_OPTIONS; i++) {
            optionButtons[i] = new JButton(answerOptions[i].answerText());
            optionButtons[i].setFont(AppConfig.ANSWER_OPTION_FONT); // Set font
            optionsPanel.add(optionButtons[i]);
        }
    }

    /**
     * Updates the question text and answer options displayed in the panel.
     * @param questionText The new text of the question.
     * @param answerOptions An array of strings representing new answer options.
     */
    public void updateQuestion(final String questionText,
                               final AnswerOption[] answerOptions) {

        // Update the question label text
        questionLabel.setText(questionText);
        // Update the buttons with answer options
        for (int i = 0; i < AppConfig.NUM_ANSWER_OPTIONS; i++) {
            optionButtons[i].setText(answerOptions[i].answerText());
        }

    }

    /**
     * Accessor method to retrieve the text of the label displaying the
     * question.
     * @return question text
     */
    public String getQuestionLabelText() {
        return questionLabel.getText();
    }

    /**
     * Accessor method to retrieve the answer option buttons.
     * @return answer option buttons
     */
    public JButton[] getOptionButtons() {
        return optionButtons;
    }

    /**
     * Accessor method to retrieve the text of a specific option button by
     * index.
     * @param index specifies the option button
     * @return answer option text
     */
    public String getOptionButtonText(final int index) {
        if (index >= 0 && index < AppConfig.NUM_ANSWER_OPTIONS) {
            return optionButtons[index].getText();
        } else {
            return null; // Returns null if index is invalid
        }
    }
}
