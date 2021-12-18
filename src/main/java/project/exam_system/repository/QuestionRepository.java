package project.exam_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.exam_system.model.entities.Answer;
import project.exam_system.model.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question getQuestionByOrder(Integer order);
    void deleteById(Long id);

}
