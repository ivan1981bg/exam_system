package project.exam_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.exam_system.model.entities.Log;

import java.util.Optional;

@Repository
public interface LogRepository extends JpaRepository<Log, String> {

    Optional<Log> findLogByUsername(String username);

}
