package project.exam_system.model.service;

import java.util.List;

public class UserServiceModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<UserRoleServiceModel> userRoles;

    public List<UserRoleServiceModel> getUserRoles() {
        return userRoles;
    }

    public UserServiceModel setUserRoles(List<UserRoleServiceModel> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    public UserServiceModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
