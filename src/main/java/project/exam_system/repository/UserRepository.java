package project.exam_system.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.exam_system.model.entities.Answer;
import project.exam_system.model.entities.Question;
import project.exam_system.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String userName);
/*
    @Query(value = "SELECT value(answer) from UserEntity user join user.answers answer " +
            "where user.username = :username and key(answer).id = :questionId")
    String getAnswerByUsernameAndQuestion(@Param("username") String username, @Param("questionId") Long questionId);


    @Query(value = "SELECT user FROM UserEntity user join user.answers  " +
            " question JOIN  user.answers  Question    where user.username = :username and question = :questionId")
    Object getAnswersByUsernameAndQuestion(@Param("username") String username, @Param("questionId") String questionId);

    @Modifying
    @Query(value = "DELETE  FROM UserEntity user  WHERE key(user.answers).id = :qId ")
    void removeFromUsersAnswers(@Param("qId") Long qId);*/

}
