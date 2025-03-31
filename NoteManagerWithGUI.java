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


