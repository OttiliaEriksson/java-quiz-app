package com.github.ottiliaeriksson.views;

import com.github.ottiliaeriksson.controllers.QuizActionListener;
import com.github.ottiliaeriksson.support.AppConfig;

import javax.swing.*;

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

}
