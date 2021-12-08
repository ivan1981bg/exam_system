package project.exam_system.service;

import java.util.List;

public interface AuthenticatedUserService {
    String getUsername();

    List<String> getRoles();
}