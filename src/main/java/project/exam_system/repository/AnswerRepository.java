package project.exam_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.exam_system.model.entities.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
