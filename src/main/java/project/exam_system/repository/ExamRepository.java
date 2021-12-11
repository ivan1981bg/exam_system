package project.exam_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.exam_system.model.entities.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
}
