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
