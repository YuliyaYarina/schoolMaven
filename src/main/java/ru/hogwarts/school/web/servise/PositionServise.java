package ru.hogwarts.school.web.servise;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.web.model.Position;
import ru.hogwarts.school.web.repository.PositionRepository;

@Service
public class PositionServise {
   private PositionRepository positionRepository;

    public PositionServise(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }
    public Position createPosition(Position position) {
        return positionRepository.save(position);
    }

    public Position findById(Long id) {
        return positionRepository.findById(id).get();
    }
    public void deleteById(Long id) {
        positionRepository.deleteById(id);
    }
//   public List<Position> findByName(String name) {
//        return positionRepository.findBy(name);
//   }

}
