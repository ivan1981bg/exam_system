package project.exam_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.exam_system.model.entities.UserAnswer;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

    UserAnswer getByUserIdAndQuestionId(Long userId, Long questionId);

    void deleteUserAnswerByUserId(Long id);

    Integer deleteUserAnswerByQuestionId(Long qId);
}
