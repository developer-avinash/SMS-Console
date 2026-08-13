package com.avi.studentmanagement.runner;

import com.avi.studentmanagement.model.Student;
import com.avi.studentmanagement.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

	private final StudentService studentService;
	private final Scanner scanner = new Scanner(System.in);

	public ConsoleRunner(StudentService studentService) {
		this.studentService = studentService;
	}

	@Override
	public void run(String... args) {

		boolean running = true;

		while (running) {

			showMenu();

			System.out.print("Enter your choice: ");

			int choice;

			try {
				choice = Integer.parseInt(scanner.nextLine());

				switch (choice) {

				case 1:
					addStudent();
					break;

				case 2:
					viewAllStudents();
					break;

				case 3:
					searchStudent();
					break;

				case 4:
					updateStudent();
					break;

				case 5:
					deleteStudent();
					break;

				case 6:
					searchStudentsByName();
					break;

				case 7:
					filterStudentsByCourse();
					break;

				case 8:
					filterStudentsByAge();
					break;

				case 9:
					running = false;
					System.out.println("\nThank you for using Student Management System!");
					break;

				default:
					System.out.println("Invalid choice! Please try again.");
				}

			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid number!");
			} catch (RuntimeException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

	private void showMenu() {

		System.out.println("\n======================================");
		System.out.println("      STUDENT MANAGEMENT SYSTEM");
		System.out.println("======================================");

		System.out.println("1. Add Student");
		System.out.println("2. View All Students");
		System.out.println("3. Search Student By ID");
		System.out.println("4. Update Student");
		System.out.println("5. Delete Student");
		System.out.println("6. Search Student By Name");
		System.out.println("7. Filter Students By Course");
		System.out.println("8. Filter Students By Age");
		System.out.println("9. Exit");

		System.out.println("======================================");
	}

	private void addStudent() {

		System.out.println("\n----- ADD STUDENT -----");

		System.out.print("Enter Student ID: ");
		int id = Integer.parseInt(scanner.nextLine());

		System.out.print("Enter Name: ");
		String name = scanner.nextLine();

		System.out.print("Enter Age: ");
		int age = Integer.parseInt(scanner.nextLine());

		System.out.print("Enter Gender: ");
		String gender = scanner.nextLine();

		System.out.print("Enter Course: ");
		String course = scanner.nextLine();

		System.out.print("Enter Email: ");
		String email = scanner.nextLine();

		System.out.print("Enter Phone: ");
		String phone = scanner.nextLine();

		Student student = new Student(id, name, age, gender, course, email, phone);

		studentService.addStudent(student);

		System.out.println("Student added successfully!");
	}

	private void viewAllStudents() {

		System.out.println("\n----- ALL STUDENTS -----");

		List<Student> students = studentService.getAllStudents();

		if (students.isEmpty()) {
			System.out.println("No students found.");
			return;
		}

		for (Student student : students) {
			System.out.println(student);
		}
	}

	private void searchStudent() {

		System.out.println("\n----- SEARCH STUDENT -----");

		System.out.print("Enter Student ID: ");

		int id = Integer.parseInt(scanner.nextLine());

		Student student = studentService.getStudentById(id);

		System.out.println("\nStudent Found:");
		System.out.println(student);
	}

	private void updateStudent() {

		System.out.println("\n----- UPDATE STUDENT -----");

		System.out.print("Enter Student ID: ");
		int id = Integer.parseInt(scanner.nextLine());

		// Check whether student exists
		studentService.getStudentById(id);

		System.out.print("Enter New Name: ");
		String name = scanner.nextLine();

		System.out.print("Enter New Age: ");
		int age = Integer.parseInt(scanner.nextLine());

		System.out.print("Enter New Gender: ");
		String gender = scanner.nextLine();

		System.out.print("Enter New Course: ");
		String course = scanner.nextLine();

		System.out.print("Enter New Email: ");
		String email = scanner.nextLine();

		System.out.print("Enter New Phone: ");
		String phone = scanner.nextLine();

		Student updatedStudent = new Student(id, name, age, gender, course, email, phone);

		studentService.updateStudent(updatedStudent);

		System.out.println("Student updated successfully!");
	}

	private void deleteStudent() {

		System.out.println("\n----- DELETE STUDENT -----");

		System.out.print("Enter Student ID: ");

		int id = Integer.parseInt(scanner.nextLine());

		studentService.deleteStudent(id);

		System.out.println("Student deleted successfully!");
	}
	private void searchStudentsByName() {

	    System.out.println("\n----- SEARCH STUDENT BY NAME -----");

	    System.out.print("Enter Student Name: ");

	    String name = scanner.nextLine();

	    List<Student> students =
	            studentService.searchStudentsByName(name);

	    displayStudents(students);
	}
	private void filterStudentsByCourse() {

	    System.out.println("\n----- FILTER STUDENTS BY COURSE -----");

	    System.out.print("Enter Course Name: ");

	    String course = scanner.nextLine();

	    List<Student> students =
	            studentService.filterStudentsByCourse(course);

	    displayStudents(students);
	}
	private void filterStudentsByAge() {

	    System.out.println("\n----- FILTER STUDENTS BY AGE -----");

	    System.out.print("Enter Age: ");

	    int age = Integer.parseInt(scanner.nextLine());

	    List<Student> students =
	            studentService.filterStudentsByAge(age);

	    displayStudents(students);
	}
	private void displayStudents(List<Student> students) {

	    if (students.isEmpty()) {

	        System.out.println("\nNo students found.");

	        return;
	    }

	    System.out.println("\n----- STUDENT LIST -----");

	    for (Student student : students) {

	        System.out.println(student);
	    }
	}
}