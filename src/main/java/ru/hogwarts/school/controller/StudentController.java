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
import java.util.Collections;
import java.util.List;

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

//    @GetMapping("{id}")  ///?
//    public Student get(@PathVariable Long id) {
//        return studentService.get(id);
//    }

    @GetMapping  ///  ???
    public Collection<Student> findAllByAgeStudent(@RequestParam(required = false) Long id,
                                                   @RequestParam(required = false) String name,
                                                   @RequestParam(required = false) Integer age) {
        if (!(id == null)) {
            return Collections.singleton(studentService.get(id));
        }
        if (name != null && !name.isBlank()) {
            return studentService.findByName(name);
        }
        if (!(age == null)) {
            return studentService.getByAge(age);
        }
        return studentService.getAllStudent();
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

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Student>> getStudentsByName(@PathVariable("name") String name){
        List<Student> students = studentService.getStudentsByName(name);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/firstLetter")
    public List<String> firstLetter(@RequestParam String firstLetter){
        return studentService.findAllByFirstLetter(firstLetter);
    }

    @GetMapping("/averageAgeAll")
    public double averageAgeAll(){
        return studentService.averageAgeAll();
    }

    @GetMapping("a+b")
    public long iint(){
        return studentService.iint();
    }

    @GetMapping("print-parallel")
    public void printParallel( ){
        studentService.printParallel();
    }

    @GetMapping("print-synchrorized")
    public void printSynchronized(){
        studentService.printSynchronized();
    }
}
