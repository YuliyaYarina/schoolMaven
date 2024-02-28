package ru.hogwarts.school.service;

import jakarta.annotation.PostConstruct;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.StudentNFE;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

//import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostConstruct
    public void initStudents() {
        add(new Student("ivan", 33));
        add(new Student("Dmitry", 10));
    }

    public Student add(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(StudentNFE::new);
    }
    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public Student editStudent(Long id, Student student) {
        return studentRepository.findById(id).map(student1 -> {
            student1.setName(student.getName());
            student1.setAge(student.getAge());
            return studentRepository.save(student1);
        }).orElse(null);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getAgeStusent(int age) {
        return studentRepository.findAll()
                .stream()
                .filter(f->f.getAge() == age)
                .collect(Collectors.toList());
    }
}
