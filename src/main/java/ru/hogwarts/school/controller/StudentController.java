package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RequestMapping("/student")
@RestController
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.add(student);
    }

    @GetMapping("{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.findStudentById(id);
    }

    @GetMapping("/findAllStudent")
    public Collection<Student> findAllByAgeStudent(@RequestParam String name) {
        if (name != null && !name.isBlank()) {
            return studentService.findByName(name);
        }
        return studentService.getAllStudent();
    }


    @PutMapping("{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.editStudent(id, student);
    }
    @DeleteMapping("{id}")
    public void deleteBStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }

    @GetMapping("/age")
    public Collection<Student> filterAge(@RequestParam int age){
        return studentService.getAgeStusent(age);
    }

    @GetMapping
    public Collection<Student> getByAgeBetween(@RequestParam int ageFrom,
                                               @RequestParam int ageTo) {
        return studentService.findByAge(ageFrom, ageTo);
    }

    @GetMapping("{id}/faculty")
    public Faculty getFaculty(@PathVariable Long id) {
        return studentService.getFaculty(id);
    }

}
