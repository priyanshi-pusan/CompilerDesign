import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int id;
    private String fatherName;
    private String motherName;
    private String dob; // Date of birth
    private String guardianPhoneNumber;
   
    public Student(String name, int id, String fatherName, String motherName, String dob, String guardianPhoneNumber) {
        this.name = name;
        this.id = id;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.dob = dob;
        this.guardianPhoneNumber = guardianPhoneNumber;
    }
   
    public String getName() {
        return name;
    }
   
    public int getId() {
        return id;
    }
   
    public String getFatherName() {
        return fatherName;
    }
   
    public String getMotherName() {
        return motherName;
    }
   
    public String getDOB() {
        return dob;
    }
   
    public String getGuardianPhoneNumber() {
        return guardianPhoneNumber;
    }
   
    @Override
    public String toString() {
        return name + "," + id + "," + fatherName + "," + motherName + "," + dob + "," + guardianPhoneNumber;
    }
}

public class StudentDatabase {
    private ArrayList<Student> students;
    private final String FILENAME = "students.csv";
   
    public StudentDatabase() {
        students = new ArrayList<>();
        loadStudentsFromFile();
    }
   
    private void loadStudentsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String name = parts[0];
                    int id = Integer.parseInt(parts[1]);
                    String fatherName = parts[2];
                    String motherName = parts[3];
                    String dob = parts[4];
                    String guardianPhoneNumber = parts[5];
                    students.add(new Student(name, id, fatherName, motherName, dob, guardianPhoneNumber));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
   
    private void saveStudentsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            for (Student student : students) {
                bw.write(student.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
   
    public void addStudent(String name, int id, String fatherName, String motherName, String dob, String guardianPhoneNumber) {
        students.add(new Student(name, id, fatherName, motherName, dob, guardianPhoneNumber));
        saveStudentsToFile();
    }
   
    public void removeStudent(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                saveStudentsToFile();
                System.out.println("Student with ID " + id + " removed successfully.");
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }
   
    public void displayStudents() {
        System.out.println("List of Students:");
        for (Student student : students) {
            System.out.println("Name: " + student.getName() + ", ID: " + student.getId() +
                               ", Father's Name: " + student.getFatherName() +
                               ", Mother's Name: " + student.getMotherName() +
                               ", Date of Birth: " + student.getDOB() +
                               ", Guardian's Phone Number: " + student.getGuardianPhoneNumber());
        }
    }
   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentDatabase database = new StudentDatabase();
       
        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. Add Student\n2. Remove Student\n3. Display Students\n4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
           
            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter father's name: ");
                    String fatherName = scanner.nextLine();
                    System.out.print("Enter mother's name: ");
                    String motherName = scanner.nextLine();
                    System.out.print("Enter date of birth (YYYY-MM-DD): ");
                    String dob = scanner.nextLine();
                    System.out.print("Enter guardian's phone number: ");
                    String guardianPhoneNumber = scanner.nextLine();
                    database.addStudent(name, id, fatherName, motherName, dob, guardianPhoneNumber);
                    System.out.println("Student added successfully.");
                    break;
                case 2:
                    System.out.print("Enter student ID to remove: ");
                    int removeId = scanner.nextInt();
                    database.removeStudent(removeId);
                    break;
                case 3:
                    database.displayStudents();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
       
        scanner.close();
    }
}
