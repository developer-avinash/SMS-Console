package com.avi.studentmanagement.service;

import com.avi.studentmanagement.exception.DuplicateStudentException;
import com.avi.studentmanagement.exception.StudentNotFoundException;
import com.avi.studentmanagement.model.Student;
import com.avi.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Add Student
    public Student addStudent(Student student) {

        if (studentRepository.existsById(student.getId())) {
            throw new DuplicateStudentException(
                    "Student already exists with ID: " + student.getId()
            );
        }

        return studentRepository.save(student);
    }

    // Get All Students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get Student By ID
    public Student getStudentById(int id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with ID: " + id
                        )
                );
    }

    // Update Student
    public Student updateStudent(Student student) {

        if (!studentRepository.existsById(student.getId())) {
            throw new StudentNotFoundException(
                    "Student not found with ID: " + student.getId()
            );
        }

        // save() existing ID hone par update karega
        return studentRepository.save(student);
    }

    // Delete Student
    public void deleteStudent(int id) {

        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(
                    "Student not found with ID: " + id
            );
        }

        studentRepository.deleteById(id);
    }

    // Search Students By Name
    public List<Student> searchStudentsByName(String name) {

        return studentRepository
                .findByNameContainingIgnoreCase(name);
    }

    // Filter Students By Course
    public List<Student> filterStudentsByCourse(String course) {

        return studentRepository
                .findByCourseIgnoreCase(course);
    }

    // Filter Students By Age
    public List<Student> filterStudentsByAge(int age) {

        return studentRepository.findByAge(age);
    }
}