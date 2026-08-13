package com.avi.studentmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "STUDENTS")
public class Student {

    @Id
    private int id;

    private String name;
    private int age;
    private String gender;
    private String course;
    private String email;
    private String phone;

    public Student() {
    }

    public Student(int id, String name, int age,
                   String gender, String course,
                   String email, String phone) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.course = course;
        this.email = email;
        this.phone = phone;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // getters and setters
}