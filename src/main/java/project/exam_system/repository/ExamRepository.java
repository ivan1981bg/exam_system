package project.exam_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.exam_system.model.entities.Answer;
import project.exam_system.model.entities.Exam;
import project.exam_system.model.entities.Question;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {


    @Query(value = "SELECT a FROM Exam e JOIN e.questions q JOIN q.answers a WHERE e.id = :examId AND a.order = q.correctAnswer")
    List<Answer> getCorrectAnswers(@Param("examId") Long examId);
}
