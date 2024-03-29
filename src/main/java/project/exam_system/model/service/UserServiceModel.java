package project.exam_system.model.service;


import java.util.List;
import java.util.Map;

public class UserServiceModel {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<UserRoleServiceModel> userRoles;

    public List<UserRoleServiceModel> getUserRoles() {
        return userRoles;
    }

    private Map<QuestionServiceModel, String> answers;

    public UserServiceModel setUserRoles(List<UserRoleServiceModel> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    public UserServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public UserServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
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

    public Map<QuestionServiceModel, String> getAnswers() {
        return answers;
    }

    public UserServiceModel setAnswers(Map<QuestionServiceModel, String> answers) {
        this.answers = answers;
        return this;
    }
}
