import javax.swing.*;
import java.io.*;

public class FileInspector {

    public static void main(String[] args) {
        JFrame frame = new JFrame("File Inspector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);

        JFileChooser fileChooser = new JFileChooser("src");
        fileChooser.setDialogTitle("Select a Text File");

        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Processing file: " + selectedFile.getName());
            processFile(selectedFile);
        } else {
            System.out.println("No file selected. Exiting program.");
        }

        frame.setVisible(true);
    }

    public static void processFile(File file) {
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                charCount += line.length();

                String[] words = line.split("\\s+");
                wordCount += words.length;

                lineCount++;

                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        System.out.println("\n--- File Summary Report ---");
        System.out.println("File Name: " + file.getName());
        System.out.println("Number of Lines: " + lineCount);
        System.out.println("Number of Words: " + wordCount);
        System.out.println("Number of Characters: " + charCount);
    }
}