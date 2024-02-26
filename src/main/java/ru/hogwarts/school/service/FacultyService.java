package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

//import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private Map<Long, Faculty> facultyMap = new HashMap<>();
    private long lastId = 1;

//    @PostConstruct
    public void initStudents() {
        add(new Faculty("Grifindor", "Red"));
        add(new Faculty("Slizerin", "Grey"));
    }

    public Faculty add(Faculty faculty) {
        faculty.setId(++lastId);
        facultyMap.put(lastId, faculty);
        return faculty;
    }

    public Faculty getFaculty(long id) {
        return facultyMap.get(id);
    }

    public Collection<Faculty> getAllFaculty() {
        return facultyMap.values();
    }

    public Faculty editFaculty(Long id, Faculty faculty) {
        Faculty faculty1= facultyMap.get(id);
        faculty1.setName(faculty.getName());
        faculty1.setColor(faculty.getColor());
        return faculty1;
    }

    public Faculty deleteFaculty(long id) {
        return facultyMap.remove(id);
    }

    public List<Faculty> getColorFaculty(String color) {
        return facultyMap.values()
                .stream()
                .filter(f->f.getColor().equals(color))
                .collect(Collectors.toList());
    }

}

