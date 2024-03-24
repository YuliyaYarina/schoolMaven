package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;


import java.util.Collection;
import java.util.List;


@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection<Faculty> findBookByNameContainsIgnoreCase(String name);

    Collection<Faculty> findBookByColorContainsIgnoreCase(String color);

    List<Faculty> getFacultiesByNameAndColor(String name, String color);
}
