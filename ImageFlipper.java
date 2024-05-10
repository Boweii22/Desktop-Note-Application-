package com.notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageFlipper extends JFrame {
    private JLabel imageLabel;
    private BufferedImage image;

    public ImageFlipper() {
        setTitle("Image Flipper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the image label
        imageLabel = new JLabel();
        add(imageLabel, BorderLayout.CENTER);

        // Create the flip buttons
        JButton flipHorizontalButton = new JButton("Flip Horizontal");
        JButton flipVerticalButton = new JButton("Flip Vertical");

        // Add action listeners to the flip buttons
        flipHorizontalButton.addActionListener(new FlipHorizontalListener());
        flipVerticalButton.addActionListener(new FlipVerticalListener());

        // Create the button panel and add the flip buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(flipHorizontalButton);
        buttonPanel.add(flipVerticalButton);

        // Add the button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class FlipHorizontalListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            flipImageHorizontally();
        }
    }

    private class FlipVerticalListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            flipImageVertically();
        }
    }

    private void flipImageHorizontally() {
        if (image != null) {
            BufferedImage flippedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    flippedImage.setRGB(x, y, image.getRGB(image.getWidth() - x - 1, y));
                }
            }

            image = flippedImage;
            displayImage();
        }
    }

    private void flipImageVertically() {
        if (image != null) {
            BufferedImage flippedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    flippedImage.setRGB(x, y, image.getRGB(x, image.getHeight() - y - 1));
                }
            }

            image = flippedImage;
            displayImage();
        }
    }

    private void displayImage() {
        if (image != null) {
            ImageIcon icon = new ImageIcon(image);
            imageLabel.setIcon(icon);
        }
    }

    public void loadImage(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
            displayImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImageFlipper flipper = new ImageFlipper();
            flipper.loadImage("salad.png"); // Replace with the actual image path
        });
    }
}

