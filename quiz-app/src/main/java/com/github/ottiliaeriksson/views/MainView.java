package com.github.ottiliaeriksson.views;

import com.github.ottiliaeriksson.controllers.QuizActionListener;
import com.github.ottiliaeriksson.models.AnswerOption;
import com.github.ottiliaeriksson.support.AppConfig;

import javax.swing.*;
import java.awt.*;

/**
 * MainView is the primary UI class responsible for rendering the main window
 * of the Quiz application.
 * This class delegates quiz selection actions to a QuizActionListener to allow
 * the controller layer to mediate between the view and the model.
 * @author Ottilia Eriksson
 */
public class MainView {

    private JFrame frame;
    private QuizPanel quizPanel;
    private JPanel quizPreviewPanel;
    private JButton[] quizButtons;
    private final QuizActionListener quizActionListener;

    /**
     * Constructs a new MainView object.
     */
    public MainView(QuizActionListener quizActionListener) {

        this.quizActionListener = quizActionListener;

        initialize();
    }

    /**
     * Initializes the main frame of the application.
     */
    private void initialize() {
        frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(AppConfig.FRAME_WIDTH, AppConfig.FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);

        // Add menu bar
        createMenuBar();

        frame.setVisible(true);

    }

    /**
     * Creates and sets up the menu bar for the application.
     */
    private void createMenuBar() {
        // Create Menu Bar
        final JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Create Menu
        final JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        // Create Menu Item
        final JMenuItem instructionsMenuItem =
                new JMenuItem("Instructions");
        instructionsMenuItem.addActionListener(e -> displayInstructions());
        menu.add(instructionsMenuItem);
    }

    /**
     * Displays the user instructions in a dialog box.
     */
    public void displayInstructions() {
        JOptionPane.showMessageDialog(frame,
                AppConfig.USER_INSTRUCTIONS,
                "Instructions", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Displays the available quizzes.
     * @param quizzes An array of strings representing the available quizzes.
     */
    public void displayQuizzes(final String[] quizzes) {

        quizButtons = new JButton[quizzes.length];

        // Create a panel to hold the quiz list in a grid layout
        final JPanel quizListPanel = new JPanel(
                new GridLayout(0, 2)
        ); // 2 columns and rows adjusted dynamically

        // Create a button for each quiz
        for (int i = 0; i < quizzes.length; i++) {
            quizButtons[i] = new JButton(quizzes[i]);
            quizButtons[i].setFont(AppConfig.QUIZ_BUTTONS_FONT);
            quizButtons[i].setPreferredSize(AppConfig.QUIZ_BUTTON_SIZE);
            quizListPanel.add(quizButtons[i]);
        }

        // Wrap the quiz list panel in a scroll pane
        final JScrollPane quizListScrollPane = new JScrollPane(quizListPanel);

        // Add the quiz list scroll pane to the frame
        frame.getContentPane().removeAll(); // Remove existing components
        frame.getContentPane().add(quizListScrollPane);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Displays a preview of the selected quiz.
     * @param quizName The name of the selected quiz.
     * @param numberOfQuestions The number of questions in the quiz.
     */
    public void displayQuizPreview(final String quizName,
                                   final int numberOfQuestions) {
        // Create a panel to hold the quiz preview components
        quizPreviewPanel = new JPanel();
        quizPreviewPanel.setLayout(
                new BoxLayout(quizPreviewPanel, BoxLayout.Y_AXIS)
        );
        quizPreviewPanel.setBorder(BorderFactory.createEmptyBorder(
                20, 10, 20, 10)
        ); // Padding around the panel

        // Create labels for quiz name and number of questions
        final JLabel nameLabel = new JLabel(quizName, SwingConstants.CENTER);
        nameLabel.setFont(AppConfig.QUIZ_NAME_FONT);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel questionsLabel = new JLabel(
                "Number of Questions: " + numberOfQuestions,
                SwingConstants.CENTER);
        questionsLabel.setFont(AppConfig.NUM_QUESTIONS_FONT);
        questionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a start button
        final JButton startButton = new JButton("Start Quiz");
        startButton.setFont(AppConfig.START_BUTTON_FONT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e ->
                quizActionListener.onQuizAction(quizName)
        ); // Callback to controller if start button is clicked

        // Add labels and button to the quiz preview panel
        quizPreviewPanel.add(nameLabel, BorderLayout.NORTH);
        // Add vertical spacing
        quizPreviewPanel.add(Box.createVerticalStrut(10));
        quizPreviewPanel.add(questionsLabel, BorderLayout.CENTER);
        // Add vertical spacing
        quizPreviewPanel.add(Box.createVerticalStrut(20));
        quizPreviewPanel.add(startButton, BorderLayout.SOUTH);

        // Check if the split pane already exists
        if (frame.getContentPane().getComponentCount() > 0 &&
                frame.getContentPane().getComponent(0)
                        instanceof JSplitPane splitPane) {
            splitPane.setRightComponent(quizPreviewPanel);
        } else {
            // Split pane with quiz list on the left and preview on the right
            final JSplitPane splitPane =
                    new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
            splitPane.setLeftComponent(
                    frame.getContentPane().getComponent(0));
            splitPane.setRightComponent(quizPreviewPanel);

            // Set the resize weight to make both components equally sized
            splitPane.setResizeWeight(0.5);

            // Update the content pane with the split pane
            frame.getContentPane().removeAll();
            frame.getContentPane().add(splitPane, BorderLayout.CENTER);
        }

        frame.revalidate();
        frame.repaint();
    }

    /**
     * Retrieves array of JButtons with quiz names of all available quizzes.
     * @return JButton[] with quiz names of all available quizzes
     */
    public JButton[] getQuizButtons() {
        return quizButtons;
    }

    /**
     * Adds a QuizPanel to the frame to start a new quiz.
     * @param quizName The name of the quiz.
     * @param numberOfQuestions The number of questions in the quiz.
     * @param initialQuestionText The text of the initial question.
     * @param initialAnswerOptions The initial answer options for the question.
     */
    public void displayQuizPanel(final String quizName,
                                 final int numberOfQuestions,
                                 final String initialQuestionText,
                                 final AnswerOption[] initialAnswerOptions) {
        // Create QuizPanel
        quizPanel = new QuizPanel(quizName, numberOfQuestions,
                initialQuestionText, initialAnswerOptions);

        // Add QuizPanel to the frame
        frame.getContentPane().removeAll(); // Remove existing components
        frame.getContentPane().add(quizPanel); // Add QuizPanel to the frame
        frame.revalidate(); // Revalidate the frame to reflect the changes
        frame.repaint(); // Repaint the frame to update its appearance
    }

}
