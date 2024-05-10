package com.notepad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class NotepadApplication extends JFrame {

    public NotepadApplication() {
        initComponents();
    }

    private void initComponents() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");

        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("NotePad.exe");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        fileMenu.add(newMenuItem);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
        setTitle("Notepad Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NotepadApplication().setVisible(true);
            }
        });
    }
}

