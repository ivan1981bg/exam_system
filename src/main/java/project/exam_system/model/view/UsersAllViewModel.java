package project.exam_system.model.view;


import project.exam_system.model.service.UserRoleServiceModel;

import java.util.List;

public class UsersAllViewModel {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private List<UserRoleServiceModel> userRoles;

    public List<UserRoleServiceModel> getUserRoles() {
        return userRoles;
    }

    public UsersAllViewModel setUserRoles(List<UserRoleServiceModel> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    public UsersAllViewModel() {
    }

    public String getId() {
        return id;
    }

    public UsersAllViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UsersAllViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UsersAllViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UsersAllViewModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
