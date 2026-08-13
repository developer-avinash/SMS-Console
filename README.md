
````markdown
🎓 Student Management System

A **Console-Based Student Management System** built using **Java, Spring Boot, Spring Data JPA, Hibernate, and Oracle Database**.

The application allows users to manage student records through an interactive console menu. It supports CRUD operations, searching, filtering, custom exception handling, and persistent storage using Oracle Database.

---

 🚀 Features

- ➕ Add Student
- 📋 View All Students
- 🔍 Search Student by ID
- ✏️ Update Student
- 🗑️ Delete Student
- 🔎 Search Students by Name
- 🎯 Filter Students by Course
- 🎂 Filter Students by Age
- ⚠️ Custom Exception Handling
- 🗄️ Oracle Database Integration
- 🧩 Layered Architecture
- 🖥️ Interactive Console Menu
- 📄 Hibernate SQL Logging

---

 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Core Programming Language |
| Spring Boot | Application Framework |
| Spring Data JPA | Database Repository Layer |
| Hibernate | ORM Framework |
| Oracle Database XE | Database |
| Maven | Dependency Management |
| Lombok | Boilerplate Code Reduction |
| CommandLineRunner | Console Application Execution |

---

 🏗️ Project Architecture

```text
ConsoleRunner
      │
      ▼
StudentService
      │
      ▼
StudentRepository
      │
      ▼
Spring Data JPA
      │
      ▼
Hibernate
      │
      ▼
Oracle Database
````

The project follows a **Layered Architecture** to keep the application clean, modular, and maintainable.

---

 📁 Project Structure

```text
SMS-Console
│
├── src/main/java
│   │
│   └── com/avi/studentmanagement
│
│       ├── SmsConsoleApplication.java
│       │
│       ├── model
│       │   └── Student.java
│       │
│       ├── repository
│       │   └── StudentRepository.java
│       │
│       ├── service
│       │   └── StudentService.java
│       │
│       ├── runner
│       │   └── ConsoleRunner.java
│       │
│       └── exception
│           ├── DuplicateStudentException.java
│           └── StudentNotFoundException.java
│
├── src/main/resources
│   └── application.properties
│
├── pom.xml
│
└── README.md
```

---

 📚 Student Entity

The `Student` entity contains the following fields:

| Field  | Description           |
| ------ | --------------------- |
| ID     | Unique Student ID     |
| Name   | Student Name          |
| Age    | Student Age           |
| Gender | Student Gender        |
| Course | Student Course        |
| Email  | Student Email Address |
| Phone  | Student Phone Number  |

Example:

```java
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
}
```

---

 🗄️ Database Configuration

The project uses **Oracle Database XE**.

Configure your database inside:

```text
src/main/resources/application.properties
```

```properties
spring.application.name=SMS-Console

# Oracle Database
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=system
spring.datasource.password=root
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8085
```

> ⚠️ Update the username and password according to your local Oracle database configuration.

---

 🧩 Repository Layer

The repository extends `JpaRepository`.

```java
@Repository
public interface StudentRepository
        extends JpaRepository<Student, Integer> {

    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByCourseIgnoreCase(String course);

    List<Student> findByAge(int age);
}
```

Spring Data JPA automatically provides common database operations such as:

```java
save()
findAll()
findById()
existsById()
deleteById()
```

---

 ⚙️ Service Layer

The service layer contains the business logic.

 Add Student

```java
public Student addStudent(Student student) {

    if (studentRepository.existsById(student.getId())) {
        throw new DuplicateStudentException(
            "Student already exists with ID: "
            + student.getId()
        );
    }

    return studentRepository.save(student);
}
```

 Update Student

```java
public Student updateStudent(Student student) {

    if (!studentRepository.existsById(student.getId())) {
        throw new StudentNotFoundException(
            "Student not found with ID: "
            + student.getId()
        );
    }

    return studentRepository.save(student);
}
```

---

 🔎 Search and Filter

The application supports multiple search options.

 Search by ID

```text
Enter Student ID
        ↓
Find Student
        ↓
Display Result
```

 Search by Name

```java
findByNameContainingIgnoreCase(name)
```

 Filter by Course

```java
findByCourseIgnoreCase(course)
```

 Filter by Age

```java
findByAge(age)
```

---

 ⚠️ Exception Handling

The project uses custom exceptions.

 DuplicateStudentException

Triggered when a student with the same ID already exists.

```java
public class DuplicateStudentException
        extends RuntimeException {

    public DuplicateStudentException(String message) {
        super(message);
    }
}
```

 StudentNotFoundException

Triggered when a requested student does not exist.

```java
public class StudentNotFoundException
        extends RuntimeException {

    public StudentNotFoundException(String message) {
        super(message);
    }
}
```

---

 🖥️ Console Menu

When the application starts, the following menu is displayed:

```text
======================================
      STUDENT MANAGEMENT SYSTEM
======================================

1. Add Student
2. View All Students
3. Search Student By ID
4. Update Student
5. Delete Student
6. Search Student By Name
7. Filter Students By Course
8. Filter Students By Age
9. Exit

======================================
Enter your choice:
```

---

 ▶️ How to Run

 1. Clone the Repository

```bash
git clone <your-github-repository-url>
```

 2. Open the Project

Open the project in:

* Eclipse
* Spring Tool Suite
* IntelliJ IDEA

 3. Configure Oracle Database

Make sure Oracle XE is running.

Update:

```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

 4. Build the Project

Using Maven:

```bash
mvn clean install
```

 5. Run the Application

Run:

```java
SmsConsoleApplication.java
```

Or use:

```bash
mvn spring-boot:run
```

---

 📊 Hibernate SQL Logging

Hibernate SQL logging is enabled.

```properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

When performing operations, SQL queries will appear in the console.

Example:

```sql
insert into students
(age, course, email, gender, name, phone, id)
values (?, ?, ?, ?, ?, ?, ?)
```

---

 🧪 Testing Scenarios

| Test Case         | Expected Result               |
| ----------------- | ----------------------------- |
| Add Student       | Student is saved successfully |
| Add Duplicate ID  | DuplicateStudentException     |
| View Students     | All students are displayed    |
| Search Valid ID   | Student details displayed     |
| Search Invalid ID | StudentNotFoundException      |
| Update Student    | Student record updated        |
| Delete Student    | Student removed successfully  |
| Search by Name    | Matching students displayed   |
| Filter by Course  | Matching students displayed   |
| Filter by Age     | Matching students displayed   |

---

 🔮 Future Enhancements

* Input Validation
* Email Validation
* Phone Number Validation
* Automatic Student ID Generation
* Pagination
* Sorting
* Authentication
* Role-Based Access
* REST API Integration
* Web Interface
* CSV/PDF Export

---

 👨‍💻 Author

**Avinash Kumar Pandey**

Java | Spring Boot | Spring Data JPA | Hibernate | Oracle Database

---

 📄 License

This project was developed for educational and internship purposes.

````

### GitHub pe structure aisa dikhega 🔥

```text
📦 SMS-Console
 ┣ 📂 src
 ┣ 📂 exception
 ┣ 📂 model
 ┣ 📂 repository
 ┣ 📂 service
 ┣ 📂 runner
 ┣ 📜 pom.xml
 ┗ 📜 README.md
````
