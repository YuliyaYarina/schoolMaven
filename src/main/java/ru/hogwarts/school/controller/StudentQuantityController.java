package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
public class StudentQuantityController {

    private final StudentService studentService;

    public StudentQuantityController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student-Quantity")
    public int getStudentQuantity() {
        return studentService.getStudentQuantity();
    }

    @GetMapping("/student-Middle-Age")
    public int getStudentsMiddleAge() {
        return studentService.getStudentsMiddleAge();
    }
    @GetMapping("/student-find-Five-Students")
    public Collection<Student> findFiveStudents() {
        return studentService.findFiveStudents();
    }

}
