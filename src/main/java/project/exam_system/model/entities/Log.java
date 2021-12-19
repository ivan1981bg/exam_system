package project.exam_system.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Log extends BaseEntity{
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    public Log() {
    }

    public String getUsername() {
        return username;
    }

    public Log setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Log setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Log setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }
}
