import java.io.*;
import java.util.*;

public class DataSaver {

    public static void main(String[] args) {
        // Initialize the Scanner and ArrayList to store records
        Scanner sc = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();

        // Input loop for collecting multiple records
        while (true) {
            // Collect user data using SafeInput methods
            String firstName = SafeInput.getNonZeroLenString(sc, "Enter First Name");
            String lastName = SafeInput.getNonZeroLenString(sc, "Enter Last Name");
            String idNumber = String.format("%06d", records.size() + 1); // Auto-generate ID number like 000001, 000002
            String email = SafeInput.getRegExString(sc, "Enter Email", "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
            int yearOfBirth = SafeInput.getInt(sc, "Enter Year of Birth");

            // Create the CSV record
            String record = firstName + ", " + lastName + ", " + idNumber + ", " + email + ", " + yearOfBirth;
            records.add(record); // Store the record in the ArrayList

            // Ask if user wants to add another record
            boolean addAnother = SafeInput.getYNConfirm(sc, "Do you want to add another record");
            if (!addAnother) {
                break;
            }
        }

        // Ask for the file name and append .csv extension
        String fileName = SafeInput.getNonZeroLenString(sc, "Enter the file name (without .csv extension)") + ".csv";

        // Write the records to the CSV file in the src directory
        writeFile(fileName, records);
    }

    // Method to write records to the CSV file
    public static void writeFile(String fileName, ArrayList<String> records) {
        // Create the file object in the src directory
        File file = new File("src/" + fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String record : records) {
                writer.write(record);  // Write the record
                writer.newLine();  // Add a new line after each record
            }
            System.out.println("Data has been successfully saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}