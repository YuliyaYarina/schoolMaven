package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

//    Student findByIdIgnoreCase(Long id);

    Collection<Student> findByName(String name);
    Collection<Student> findByAgeBetween(int ageFrom, int ageTo);

//    Collection<Student> findAllByAgeContainsIgnoreCase(Integer age);



}
