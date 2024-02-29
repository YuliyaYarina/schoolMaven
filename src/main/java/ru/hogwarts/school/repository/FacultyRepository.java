package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;


import java.util.Collection;


@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

//    Collection<Faculty> findByNameIgnoreCaseOrByColorIgnorCase(String name, String color);

    Collection<Faculty> findBookByNameContainsIgnoreCase(String name);

    Collection<Faculty> findBookByColorContainsIgnoreCase(String color);

}
