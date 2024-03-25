package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequestMapping("/faculty")
@RestController
public class FacultyController {
    private FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.add(faculty);
    }

    @GetMapping("{id}")
    public ResponseEntity getFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if(faculty == null) {
            return ResponseEntity.notFound() .build();
        }
        return ResponseEntity.ok(faculty);
    }

//    @GetMapping
//    private ResponseEntity<Collection<Faculty>> getAllFaculty () {
//        return ResponseEntity.ok(facultyService.getAllFaculty());
//    }
    @PutMapping("{id}")
    public Faculty updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        return facultyService.editFaculty(id, faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteBook(@PathVariable Long id){
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/color")
    public Collection<Faculty> filterColor(@RequestParam String color){
        return facultyService.getColorFaculty(color);
    }
    @GetMapping
    public Collection<Faculty> getAllFacultyfilterColorAndName(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false) String color){
        if (name != null && !name.isBlank()){
            return facultyService.findByName(name);
        }
        if (color != null && !color.isBlank()){
            return facultyService.findByColor(color);
        }
        return facultyService.getAllFaculty();
    }
    @GetMapping("{id}/student")
    public Collection<Student> getStudent(@PathVariable Long id) {
        return facultyService.getStudents(id);
    }

    @GetMapping("/name/{name}/color/{color}")
    public ResponseEntity<List<Faculty>> getFacultiesByNameAndColor(@RequestParam(required = false) String name,
                                                                    @RequestParam(required = false) String color){
        if (name != null && !name.isBlank()){
            List<Faculty> facultiesN = facultyService.getFacultiesByName(name);
            return ResponseEntity.ok(facultiesN);
        }
        if (color != null && !color.isBlank()){
            List<Faculty> facultiesC =facultyService.getFacultiesByColor(color);
            return ResponseEntity.ok(facultiesC);
        }
        return (ResponseEntity<List<Faculty>>) ResponseEntity.status(500);
    }

    @GetMapping("/longestNameFaculty")
    public String longestNameFaculty(){
        return facultyService.longestNameFaculty();
    }
}


