package project.exam_system.model.service;

public class UserRoleServiceModel {
    private Long id;
    private String userRole;

    public UserRoleServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public UserRoleServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUserRole() {
        return userRole;
    }

    public UserRoleServiceModel setUserRole(String userRole) {
        this.userRole = userRole;
        return this;
    }
}
