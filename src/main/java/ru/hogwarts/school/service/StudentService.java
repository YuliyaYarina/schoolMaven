package ru.hogwarts.school.service;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.StudentNFE;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {

//    @Value("${avatars.dir.path}")
//    private String avatarsDir;

    private final StudentRepository studentRepository;
//    private final AvatarRepository avatarRepository;

    public StudentService(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;

//        this.avatarRepository = avatarRepository;

    }

    public Student add(Student student) {
//        student.setId(null);
        return studentRepository.save(student);
    }

    public Student get(Long id) {
        return studentRepository.findById(id).orElseThrow(StudentNFE::new);
    }


    public Student update(Long id, Student student) {
        return studentRepository.findById(id).map(student1 -> {
            student1.setName(student.getName());
            student1.setAge(student.getAge());
            return studentRepository.save(student1);
        }).orElse(null);
    }

    public void delete(long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

//    public Student findById(Long id){
//        return studentRepository.findByIdIgnoreCase(id);
//    }

    public Collection<Student> findByName(String name){
        return studentRepository.findByName(name);
    }
    public Collection<Student> getByAgeBetween(int ageFrom, int ageTo){
        return studentRepository.findByAgeBetween(ageFrom, ageTo);
    }
    public List<Student> getByAge(int age) {
        return studentRepository.findAll()
                .stream()
                .filter(f->f.getAge() == age)
                .collect(Collectors.toList());
    }
    public Faculty getFaculty(Long id){
        return studentRepository.findById(id)
                .map(Student::getFaculty)
                .orElse(null);
    }
}
