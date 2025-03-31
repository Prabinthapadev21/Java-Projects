import java.io.*;
import java.util.Scanner;
public class NoteManager {
    static final String FILE_PATH = System.getProperty("user.home") + "/Desktop/notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n📌 Simple Note Manager 📌");
            System.out.println("1. Create Note");
            System.out.println("2. Read Note");
            System.out.println("3. Append to Note");
            System.out.println("4. Delete Note");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // ✅ Fix: Consume newline after integer input

            switch (choice) {
                case 1:
                    createNote(sc);
                    break;
                case 2:
                    readNote();
                    break;
                case 3:
                    appendNote(sc);
                    break;
                case 4:
                    deleteNote();
                    break;
                case 5:
                    System.out.println("Exiting... 🚀");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    // Create a new note
    public static void createNote(Scanner scanner) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            System.out.println("Enter your note:");
            String note = scanner.nextLine();
            writer.write(note);
            System.out.println("✅ Note saved successfully!");
        } catch (IOException e) {
            System.out.println("❌ Error creating note.");
            e.printStackTrace();
        }
    }
    // Read the note
    public static void readNote() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            System.out.println("\n📄 Your Note Content:");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("❌ No note found! Create a note first.");
        }
    }
    // Append to an existing note
    public static void appendNote(Scanner scanner) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            System.out.println("Enter text to append:");
            String note = scanner.nextLine();
            writer.write("\n" + note); // ✅ Fixed 'Writer' error
            System.out.println("✅ Text appended successfully!");
        } catch (IOException e) {
            System.out.println("❌ Error appending to note.");
            e.printStackTrace();
        }
    }
    // Delete the note file
    public static void deleteNote() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("✅ Note deleted successfully!");
            } else {
                System.out.println("❌ Unable to delete note.");
            }
        } else {
            System.out.println("❌ No note found to delete!");
        }
    }
}
