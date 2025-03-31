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
    // Create a new note
    private void createNote() {
        try (FileWriter writer = new FileWriter(selectedFile)) {
            writer.write(textArea.getText());
            JOptionPane.showMessageDialog(this, "✅ Note saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "❌ Error saving note.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Read the note
    private void readNote() {
        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
            textArea.setText("");
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
            JOptionPane.showMessageDialog(this, "📖 Note loaded successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "❌ No note found! Create a note first.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Append to an existing note
    private void appendNote() {
        try (FileWriter writer = new FileWriter(selectedFile, true)) {
            writer.write("\n" + textArea.getText());
            JOptionPane.showMessageDialog(this, "✅ Text appended successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "❌ Error appending to note.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Delete the note file
    private void deleteNote() {
        if (selectedFile.exists()) {
            if (selectedFile.delete()) {
                textArea.setText("");
                JOptionPane.showMessageDialog(this, "✅ Note deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "❌ Unable to delete note.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "❌ No note found to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // File Chooser - Select a Note File
    private void chooseFile() {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home") + "/Desktop");
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "✅ Selected file: " + selectedFile.getName());
        }
    }
    // Toggle Dark Mode
    private void toggleDarkMode() {
        if (isDarkMode) {
            applyLightMode();
        } else {
            applyDarkMode();
        }
        isDarkMode = !isDarkMode;
    }
    // Apply Dark Mode
    private void applyDarkMode() {
        getContentPane().setBackground(Color.DARK_GRAY);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setCaretColor(Color.WHITE);

        saveButton.setBackground(Color.GRAY);
        readButton.setBackground(Color.GRAY);
        appendButton.setBackground(Color.GRAY);
        deleteButton.setBackground(Color.GRAY);
        chooseFileButton.setBackground(Color.GRAY);
        darkModeButton.setBackground(Color.GRAY);

        saveButton.setForeground(Color.WHITE);
        readButton.setForeground(Color.WHITE);
        appendButton.setForeground(Color.WHITE);
        deleteButton.setForeground(Color.WHITE);
        chooseFileButton.setForeground(Color.WHITE);
        darkModeButton.setForeground(Color.WHITE);
    }