import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
public class NoteManagerWithGUI extends JFrame{
    private JTextArea textArea;
    private JButton saveButton, readButton, appendButton, deleteButton, chooseFileButton, darkModeButton;
    private File selectedFile = new File(System.getProperty("user.home") + "/Desktop/notes.txt"); // Default file
    private boolean isDarkMode = false;

    public NoteManagerWithGUI() {
        setTitle("📌 Advanced Note Manager");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Text Area for Note Input/Display
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Panel for Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 10, 10));

        saveButton = new JButton("Save Note");
        readButton = new JButton("Read Note");
        appendButton = new JButton("Append Note");
        deleteButton = new JButton("Delete Note");
        chooseFileButton = new JButton("Choose File");
        darkModeButton = new JButton("Dark Mode 🌙");

        buttonPanel.add(saveButton);
        buttonPanel.add(readButton);
        buttonPanel.add(appendButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(chooseFileButton);
        buttonPanel.add(darkModeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        saveButton.addActionListener(e -> createNote());
        readButton.addActionListener(e -> readNote());
        appendButton.addActionListener(e -> appendNote());
        deleteButton.addActionListener(e -> deleteNote());
        chooseFileButton.addActionListener(e -> chooseFile());
        darkModeButton.addActionListener(e -> toggleDarkMode());

        // Apply Default Theme
        applyLightMode();

        setVisible(true);
    }
