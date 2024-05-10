package com.notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.*;

public class NotepadWindow {
    private JFrame frame;
    private JTextArea textArea;

    public NotepadWindow() {
        frame = new JFrame("Notepad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.addActionListener(new NewMenuItemListener());
        fileMenu.add(newMenuItem);

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(new SaveMenuItemListener());
        fileMenu.add(saveMenuItem);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem findMenuItem = new JMenuItem("Find");
        findMenuItem.addActionListener(new FindMenuItemListener());
        editMenu.add(findMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        frame.setJMenuBar(menuBar);

        textArea = new JTextArea();
        frame.add(new JScrollPane(textArea));

        frame.setPreferredSize(new Dimension(400, 300));
        frame.pack();
        frame.setVisible(true);
    }

    private class NewMenuItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.setText("");
        }
    }

    private class SaveMenuItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Code to save the text
        }
    }

    private class FindMenuItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchText = JOptionPane.showInputDialog(frame, "Enter text to find:");
            if (searchText != null && !searchText.isEmpty()) {
                Document document = textArea.getDocument();
                try {
                    String text = document.getText(0, document.getLength());
                    int index = text.indexOf(searchText);
                    if (index != -1) {
                        textArea.setCaretPosition(index);
                        textArea.setSelectionStart(index);
                        textArea.setSelectionEnd(index + searchText.length());
                    } else {
                        JOptionPane.showMessageDialog(frame, "Text not found.");
                    }
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NotepadWindow();
        });
    }
}

