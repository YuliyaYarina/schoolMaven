package ru.hogwarts.school.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

//import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final HashMap<Long, Student> studentMap = new HashMap<>();
    private static long studentId = 1;

    @PostConstruct
    public void initStudents() {
        add(new Student("ivan", 33));
        add(new Student("Dmitry", 10));
    }

    public Student add(Student student) {
        student.setId(studentId++);
        studentMap.put(student.getId(), student);
        return student;
    }

    public Student findStudentById(long id) {
        return studentMap.get(id);
    }
    public Collection<Student> getAllStudent() {
        return studentMap.values();
    }

    public Student editStudent(Long id, Student student) {
        Student student1= studentMap.get(id);
        student1.setName(student.getName());
        student1.setAge(student.getAge());
        return student1;
    }

    public Student deleteStudent(long Id) {
        return studentMap.remove(Id);
    }

    public List<Student> getAgeStusent(int age) {
        return studentMap.values()
                        .stream()
                .filter(s->s.getAge() == age)
                .collect(Collectors.toList());
    }
}
