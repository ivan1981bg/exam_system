package project.exam_system.model.view;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class LogsAllViewModel {

    private String username;
    private String description;
    private LocalDateTime time;

    public LogsAllViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public LogsAllViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public LogsAllViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public LogsAllViewModel setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }
}
