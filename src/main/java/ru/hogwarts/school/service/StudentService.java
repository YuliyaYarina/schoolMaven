package ru.hogwarts.school.service;

import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;
//    private final AvatarRepository avatarRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;

//        this.avatarRepository = avatarRepository;

    }

    public Student add(Student student) {
        logger.info("add method was invoked");
        return studentRepository.save(student);
    }

    public Student get(Long id) {
        logger.info("get method was invoked");
        return studentRepository.findById(id).orElseThrow(StudentNFE::new);
    }


    public Student put(Long id, Student student) {
        logger.info("put method was invoked");
        return studentRepository.findById(id).map(student1 -> {
            student1.setName(student.getName());
            student1.setAge(student.getAge());
            return studentRepository.save(student1);
        }).orElse(null);
    }

    public void delete(long id) {
        logger.info("delete method was invoked");
        studentRepository.deleteById(id);
    }

    public List<Student> getAllStudent() {
        logger.info("getAll method was invoked");
        return studentRepository.findAll();
    }

//    public Student findById(Long id){
//        return studentRepository.findByIdIgnoreCase(id);
//    }

    public Collection<Student> findByName(String name){
        logger.info("findByName method was invoked");
        return studentRepository.findByName(name);
    }
    public Collection<Student> getByAgeBetween(int ageFrom, int ageTo){
        logger.info("getAgeBetween method was invoked");
        return studentRepository.findByAgeBetween(ageFrom, ageTo);
    }
    public List<Student> getByAge(int age) {
        logger.info("getByAge method was invoked");
        return studentRepository.findAll()
                .stream()
                .filter(f->f.getAge() == age)
                .collect(Collectors.toList());
    }
    public Faculty getFaculty(Long id){
        logger.info("getFaculty method was invoked");
        return studentRepository.findById(id)
                .map(Student::getFaculty)
                .orElse(null);
    }

    public int getStudentQuantity() {
        logger.info("getStudentQuantity method was invoked");
        return studentRepository.getStudentQuantity();
    }
    public int getStudentsMiddleAge() {
        logger.info("getStudentsMiddleAge method was invoked");
        return studentRepository.getStudentsMiddleAge();
    }
    public Collection<Student> findFiveStudents() {
        logger.info("findFiveStudents method was invoked");
        return studentRepository.findFiveStudents();
    }


    public List<Student> getStudentsByName(String name){
        logger.info("getStudentsByName method was invoked");
        return studentRepository.getStudentsByName(name);
    }



}
