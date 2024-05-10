package com.notepad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.JButton;

public class NotePad extends JFrame implements ActionListener {
	private JTextArea textArea;
	private JFileChooser fileChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotePad window = new NotePad();
//					setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NotePad() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("NotePad v./1.12");
		setSize(561, 441);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Create a JTextArea
		textArea = new JTextArea();
		textArea.setForeground(new Color(38, 38, 38));
		textArea.setFont(new Font("Arial", Font.PLAIN, 14));

		// Create a JScrollPane to hold the JTextArea
		JScrollPane scrollPane = new JScrollPane(textArea);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		// Create a JMenuBar with File menu
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 103, 206));
		JMenu fileMenu = new JMenu("File");
		fileMenu.setForeground(new Color(255, 255, 255));
		JMenu fileMenu1 = new JMenu("Edit");
		fileMenu1.setForeground(new Color(255, 255, 255));
		JMenu fileMenu2 = new JMenu("View");
		fileMenu2.setForeground(new Color(255, 255, 255));
		JMenuItem aboutMenuItem = new JMenuItem("About");
		JMenuItem findMenuItem = new JMenuItem("Find");
		JMenuItem selectAllMenuItem = new JMenuItem("Select All");
		JMenuItem timeDateMenuItem = new JMenuItem("Time/Date");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem newWindowMenuItem = new JMenuItem("New Window");
		JMenuItem openMenuItem = new JMenuItem("Open");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		JMenuItem saveAsMenuItem = new JMenuItem("Save As");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(newMenuItem);
		fileMenu.add(newWindowMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(saveAsMenuItem);
		fileMenu.add(exitMenuItem);
		fileMenu1.add(findMenuItem);
		fileMenu1.add(selectAllMenuItem);
		fileMenu1.add(timeDateMenuItem);
		fileMenu2.add(aboutMenuItem);
		menuBar.add(fileMenu);
		menuBar.add(fileMenu1);
		menuBar.add(fileMenu2);
		setJMenuBar(menuBar);
		;

		// Add ActionListener to menu items
		newMenuItem.addActionListener(this);
		newWindowMenuItem.addActionListener(this);
		openMenuItem.addActionListener(this);
		saveMenuItem.addActionListener(this);
		saveAsMenuItem.addActionListener(this);
		exitMenuItem.addActionListener(this);
		findMenuItem.addActionListener(this);
		selectAllMenuItem.addActionListener(this);
		timeDateMenuItem.addActionListener(this);
		aboutMenuItem.addActionListener(this);

		// Create a JFileChooser for file operations
		fileChooser = new JFileChooser();

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			String command = e.getActionCommand();
			if(command.equals("New")) {
				
			}else if(command.equals("New Window")) {
				NotePad newWindow = new NotePad();
				newWindow.setVisible(true);
			}else if(command.equals("Open")) {
				int result = fileChooser.showOpenDialog(this);
				  if (result == JFileChooser.APPROVE_OPTION) {
		                File file = fileChooser.getSelectedFile();
		                try {
		                    BufferedReader reader = new BufferedReader(new FileReader(file));
		                    textArea.read(reader, null);
		                    reader.close();
		                } catch (IOException ex) {
		                    ex.printStackTrace();
		                }
		            }
			}else if(command.equals("Save")) {
				
			}else if(command.equals("Save As")) {
				 int result = fileChooser.showSaveDialog(this);
		            if (result == JFileChooser.APPROVE_OPTION) {
		                File file = fileChooser.getSelectedFile();
		                try (PrintWriter writer = new PrintWriter(file)) {
		                    writer.write(textArea.getText());
		                } catch (IOException ex) {
		                    ex.printStackTrace();
		                }
		            }
			}else if(command.equals("Exit")) {
				System.exit(0);
			}else if(command.equals("Find")) {
				  String searchText = JOptionPane.showInputDialog(this, "Enter text to find:");
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
		                        JOptionPane.showMessageDialog(this, "Text not found.");
		                    }
		                } catch (BadLocationException ex) {
		                    ex.printStackTrace();
		                }
		            }
		            
			}else if(command.equals("Select All")) {
				textArea.selectAll();
			}else if(command.equals("Time/Date")) {
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
				String formattedTime = now.format(timeFormatter);
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
		        String formattedDate = now.format(dateFormatter);
		        textArea.setText(formattedTime + " " + formattedDate);
			}else if (command.equals("About")){
				// Show About dialog
                JOptionPane.showMessageDialog(this, "This is a simple Notepad application.", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		}catch(Exception exc) {
			
		}

	}
}
