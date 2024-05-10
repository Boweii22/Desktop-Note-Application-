package com.notepad;

import javax.swing.*;
import java.awt.*;

public class AppWithIcon {
    private JFrame frame;

    public AppWithIcon() {
        frame = new JFrame("My App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        // Set the icon for the frame
        ImageIcon icon = new ImageIcon("roman-synkevych-vXInUOv1n84-unsplash.jpg"); // Replace with the actual icon path
        frame.setIconImage(icon.getImage());

        // Create the main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create and add components to the main panel
        JLabel titleLabel = new JLabel("Welcome to My App");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.CENTER);

        // Set the content panel of the frame
        frame.setContentPane(mainPanel);

        // Show the frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AppWithIcon();
        });
    }
}

