import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class SecureNoteManager extends JFrame {
    private JTextArea textArea;
    private JButton saveButton, readButton, deleteButton;
    private File selectedFile = new File(System.getProperty("user.home") + "/Desktop/secure_notes.txt");

    public SecureNoteManager() {
        setTitle("🔐 Secure Note Manager");
        setSize(500, 400);
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
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));

        saveButton = new JButton("🔒 Save Encrypted");
        readButton = new JButton("🔓 Read Encrypted");
        deleteButton = new JButton("🗑️ Delete Note");

        buttonPanel.add(saveButton);
        buttonPanel.add(readButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        saveButton.addActionListener(e -> saveEncryptedNote());
        readButton.addActionListener(e -> readEncryptedNote());
        deleteButton.addActionListener(e -> deleteNote());

        setVisible(true);
    }

    // Ask for password & save encrypted note
    private void saveEncryptedNote() {
        String password = JOptionPane.showInputDialog(this, "Enter a password to encrypt:");
        if (password != null && !password.isEmpty()) {
            try (FileWriter writer = new FileWriter(selectedFile)) {
                String encryptedText = encrypt(textArea.getText(), password);
                writer.write(encryptedText);
                JOptionPane.showMessageDialog(this, "✅ Note saved securely!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "❌ Error encrypting note.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Ask for password & read encrypted note
    private void readEncryptedNote() {
        if (!selectedFile.exists()) {
            JOptionPane.showMessageDialog(this, "❌ No note found! Save a note first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String password = JOptionPane.showInputDialog(this, "Enter the password to decrypt:");
        if (password != null && !password.isEmpty()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String encryptedText = reader.readLine();
                String decryptedText = decrypt(encryptedText, password);
                textArea.setText(decryptedText);
                JOptionPane.showMessageDialog(this, "🔓 Note decrypted successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "❌ Incorrect password or error decrypting.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Delete the encrypted note
    private void deleteNote() {
        if (selectedFile.exists() && selectedFile.delete()) {
            textArea.setText("");
            JOptionPane.showMessageDialog(this, "🗑️ Note deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "❌ Unable to delete note.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // AES Encryption
    private String encrypt(String strToEncrypt, String secret) throws Exception {
        SecretKeySpec secretKey = getKey(secret);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
    }

    // AES Decryption
    private String decrypt(String strToDecrypt, String secret) throws Exception {
        SecretKeySpec secretKey = getKey(secret);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    }

    // Generate AES Key from Password
    private SecretKeySpec getKey(String myKey) throws Exception {
        byte[] key = myKey.getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        key = sha.digest(key);
        return new SecretKeySpec(Arrays.copyOf(key, 16), "AES");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SecureNoteManager::new);
    }
}
