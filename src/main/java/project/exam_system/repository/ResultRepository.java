package project.exam_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.exam_system.model.entities.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
}
