package ru.hogwarts.school.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.io.IOException;
import java.util.Collection;

@RequestMapping("/student")
@RestController
public class StudentController {
    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }


    @PostMapping
    public Student add(@RequestBody Student student) {
        return studentService.add(student);
    }

    @GetMapping("{id}")
    public Student get(@PathVariable Long id) {
        return studentService.get(id);
    }

    @PutMapping("{id}")
    public Student put(@PathVariable Long id, @RequestBody Student student) {
        return studentService.put(id, student);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id){
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public Collection<Student> findAllByAgeStudent(@RequestParam(required = false) String name) {
        if (name != null && !name.isBlank()) {
            return studentService.findByName(name);
        }
        return studentService.getAllStudent();
    }
    @GetMapping("getByAge")

    public Collection<Student> getByAge(@RequestParam int age){
        return studentService.getByAge(age);
    }

    @GetMapping("/max&minAge")
    public Collection<Student> getByAgeBetween(@RequestParam int ageFrom,
                                               @RequestParam int ageTo) {
        return studentService.getByAgeBetween(ageFrom, ageTo);
    }

    @GetMapping("{id}/faculty")
    public Faculty getFaculty(@PathVariable Long id) {
        return studentService.getFaculty(id);
    }

    @PostMapping(value = "/{studentId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long studentId,
                                               @RequestParam MultipartFile avatar)
            throws IOException {
        avatarService.uploadAvatar(studentId, avatar);
        return ResponseEntity.ok().build();
    }
}
