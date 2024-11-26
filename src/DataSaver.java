import java.io.*;
import java.util.*;

public class DataSaver {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();

        while (true) {
            String firstName = SafeInput.getNonZeroLenString(sc, "Enter First Name");
            String lastName = SafeInput.getNonZeroLenString(sc, "Enter Last Name");
            String idNumber = String.format("%06d", records.size() + 1); // Auto-generate ID number like 000001, 000002
            String email = SafeInput.getRegExString(sc, "Enter Email", "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
            int yearOfBirth = SafeInput.getInt(sc, "Enter Year of Birth");

            String record = firstName + ", " + lastName + ", " + idNumber + ", " + email + ", " + yearOfBirth;
            records.add(record);

            boolean addAnother = SafeInput.getYNConfirm(sc, "Do you want to add another record");
            if (!addAnother) {
                break;
            }
        }
        String fileName = SafeInput.getNonZeroLenString(sc, "Enter the file name (without .csv extension)") + ".csv";
        writeFile(fileName, records);
    }


    public static void writeFile(String fileName, ArrayList<String> records) {

        File file = new File("src/" + fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String record : records) {
                writer.write(record);
                writer.newLine();
            }
            System.out.println("Data has been successfully saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}