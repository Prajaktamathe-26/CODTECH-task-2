package studentgradetracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradeTracker {
    private List<Subject> subjects;
    private Scanner scanner;

    public GradeTracker() {
        this.subjects = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Grade Tracker Menu:");
            System.out.println("1. Add a Subject");
            System.out.println("2. Add Grades to a Subject");
            System.out.println("3. Calculate and Display Average Grades");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addSubject();
                    break;
                case 2:
                    addGradesToSubject();
                    break;
                case 3:
                    calculateAndDisplayAverageGrades();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting Grade Tracker...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
        scanner.close();
    }

    private void addSubject() {
        System.out.print("Enter the name of the subject: ");
        String subjectName = scanner.nextLine();
        Subject newSubject = new Subject(subjectName);
        subjects.add(newSubject);
        System.out.println("Subject '" + subjectName + "' added successfully.");
    }

    private void addGradesToSubject() {
        if (subjects.isEmpty()) {
            System.out.println("No subjects added yet. Please add a subject first.");
            return;
        }

        System.out.println("Select a subject to add grades:");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + ". " + subjects.get(i).getName());
        }
        System.out.print("Enter your choice: ");
        int subjectIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline character

        Subject selectedSubject = subjects.get(subjectIndex);

        boolean addMoreGrades = true;
        while (addMoreGrades) {
            System.out.print("Enter a grade for " + selectedSubject.getName() + " (or -1 to stop): ");
            double grade = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character

            if (grade == -1) {
                addMoreGrades = false;
            } else {
                selectedSubject.addGrade(grade);
                System.out.println("Grade added.");
            }
        }
    }

    private void calculateAndDisplayAverageGrades() {
        if (subjects.isEmpty()) {
            System.out.println("No subjects added yet. Please add a subject first.");
            return;
        }

        for (Subject subject : subjects) {
            double averageGrade = subject.calculateAverageGrade();
            System.out.println("Subject: " + subject.getName());
            System.out.println("Average Grade: " + averageGrade);
            System.out.println("-----------------------");
        }
    }

    public static void main(String[] args) {
        GradeTracker gradeTracker = new GradeTracker();
        gradeTracker.start();
    }
}
