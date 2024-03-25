package ru.hogwarts.school.service;

import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.StudentNFE;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<Student> findByName(String name){
        logger.info("findByName method was invoked");
        return studentRepository.findByName(name);
    }
    public List<Student> getByAgeBetween(int ageFrom, int ageTo){
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

    public List<String> findAllByFirstLetter(String firstLetter){
        logger.info("findAllByFirstLetter method was invoked");

        return studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(i -> i.startsWith(firstLetter))
                .sorted()
                .collect(Collectors.toList());
    }

     public double averageAgeAll(){
        logger.info("averageAgeAll method was invoked");

        return studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0);
    }

public long iint() {
    // method 1
    long startTime = System.currentTimeMillis();
    long sum = Stream.iterate(1, a -> a + 1)
            .limit(100_000_000)
            .reduce(0, (a, b) -> a + b);
    long finishTime = System.currentTimeMillis();

    logger.info("Метод 1 занял " + (finishTime - startTime));

    // method 2
    startTime = System.currentTimeMillis();
    sum = Stream.iterate(1, a -> a + 1)
            .limit(100_000_000)
            .parallel()
            .reduce(0, (a, b) -> a + b);
    finishTime = System.currentTimeMillis();

    logger.info("Метод 2 занял " + (finishTime - startTime));

    //method 3
    startTime = System.currentTimeMillis();
    sum = 0;
    for (int i = 0; i <= 100_000_000; i++) {
        sum += i;
    }
    finishTime = System.currentTimeMillis();

    logger.info("Метод 3 занял " + (finishTime - startTime));

    return sum;
}

}
