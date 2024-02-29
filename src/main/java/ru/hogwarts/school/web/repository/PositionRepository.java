package ru.hogwarts.school.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.web.model.Position;


@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

}
