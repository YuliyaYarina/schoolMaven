package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
@Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
int getStudentQuantity();

@Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
int getStudentsMiddleAge();

//    Student findByIdIgnoreCase(Long id);

@Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    Collection<Student> findFiveStudents();
    Collection<Student> findByName(String name);
    Collection<Student> findByAgeBetween(int ageFrom, int ageTo);

//    Collection<Student> findAllByAgeContainsIgnoreCase(Integer age);
}
