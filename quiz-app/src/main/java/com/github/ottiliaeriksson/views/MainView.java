package com.github.ottiliaeriksson.views;

import com.github.ottiliaeriksson.controllers.QuizActionListener;
import com.github.ottiliaeriksson.support.AppConfig;

import javax.swing.*;
import java.awt.*;

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

}
