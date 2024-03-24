package ru.hogwarts.school.service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

//import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(Long id) {
        return facultyRepository.findById(id).get();
    }

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }


    public Faculty editFaculty(Long id, Faculty faculty) {
        return facultyRepository.findById(id).map(f -> {
            f.setName(faculty.getName());
            f.setColor(faculty.getColor());
            return facultyRepository.save(f);
        }).orElse(null);
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getColorFaculty(String color) {
        return facultyRepository.findAll()
                .stream()
                .filter(f->f.getColor().equals(color))
                .collect(Collectors.toList());
    }

    public Collection<Faculty> findByName(String name){
        return facultyRepository.findBookByNameContainsIgnoreCase(name);
    }
    public Collection<Faculty> findByColor(String color){
        return facultyRepository.findBookByColorContainsIgnoreCase(color);
    }
    public Collection<Student> getStudents(Long id){
        return facultyRepository.findById(id)
                .map(Faculty::getStudents)
                .orElse(null);
    }

    public List<Faculty> getFacultiesByName(String name) {
        return facultyRepository.getFacultiesByName(name);
    }
    public List<Faculty> getFacultiesByColor( String color) {
        return facultyRepository.getFacultiesByColor(color);
    }


}

