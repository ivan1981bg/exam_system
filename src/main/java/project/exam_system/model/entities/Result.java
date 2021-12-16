package project.exam_system.model.entities;

import org.apache.catalina.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "results")
public class Result extends BaseEntity{

    private String fullName;

    @ManyToOne
    private Exam exam;

    private Integer totalCorrect;


    public Result() {
    }

    public String getFullName() {
        return fullName;
    }

    public Result setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Exam getExam() {
        return exam;
    }

    public Result setExam(Exam exam) {
        this.exam = exam;
        return this;
    }

    public Integer getTotalCorrect() {
        return totalCorrect;
    }

    public Result setTotalCorrect(Integer totalCorrect) {
        this.totalCorrect = totalCorrect;
        return this;
    }
}
