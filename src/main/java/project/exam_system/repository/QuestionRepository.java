package project.exam_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.exam_system.model.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
