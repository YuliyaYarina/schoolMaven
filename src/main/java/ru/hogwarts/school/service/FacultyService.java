package ru.hogwarts.school.service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

//import javax.annotation.PostConstruct;
import java.util.*;
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
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Faculty getFaculty(Long id) {
        return facultyRepository.findById(id).get();
    }

    public List<Faculty> getAllFaculty() {
        logger.info("getAllFaculty method was invoked");
        return facultyRepository.findAll();
    }


    public Faculty editFaculty(Long id, Faculty faculty) {
        logger.info("editFaculty method was invoked");
        return facultyRepository.findById(id).map(f -> {
            f.setName(faculty.getName());
            f.setColor(faculty.getColor());
            return facultyRepository.save(f);
        }).orElse(null);
    }

    public void deleteFaculty(long id) {
        logger.info("deleteFaculty method was invoked");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getColorFaculty(String color) {
        logger.info("getColorFaculty method was invoked");
        return facultyRepository.findAll()
                .stream()
                .filter(f->f.getColor().equals(color))
                .collect(Collectors.toList());
    }

    public Collection<Faculty> findByName(String name){
        logger.info("findByName method was invoked");
        return facultyRepository.findBookByNameContainsIgnoreCase(name);
    }
    public Collection<Faculty> findByColor(String color){
        logger.info("findByColor method was invoked");
        return facultyRepository.findBookByColorContainsIgnoreCase(color);
    }
    public Collection<Student> getStudents(Long id){
        logger.info("getStudents method was invoked");
        return facultyRepository.findById(id)
                .map(Faculty::getStudents)
                .orElse(null);
    }

    public List<Faculty> getFacultiesByName(String name) {
        logger.info("getFacultiesByName method was invoked");
        return facultyRepository.getFacultiesByName(name);
    }
    public List<Faculty> getFacultiesByColor( String color) {
        logger.info("getFacultiesByColor method was invoked");
        return facultyRepository.getFacultiesByColor(color);
    }

    public String longestNameFaculty() {
        return facultyRepository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElse("Нет данных");
    }
}

