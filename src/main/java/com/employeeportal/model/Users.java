package com.employeeportal.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersId;
    private Long employeeId; // Changed variable name from employeeeId to employeeId
    private String name;
    private String roleName;
    private LocalDateTime joiningDate;
    private String designation;
    private String projects;
    private String projectManager;
    private String email;
    private String mobileNumber;
    private String password;

    // Default constructor
    public Users() {
    }

    // Constructor with all fields
    public Users(Long usersId, Long employeeId, String name, String role, LocalDateTime joiningDate, String designation,
                 String projects, String projectManager, String email, String mobileNumber, String password) {
        this.usersId = usersId;
        this.employeeId = employeeId;  // Updated variable name to match field name
        this.name = name;
        this.roleName = role;
        this.joiningDate = joiningDate;
        this.designation = designation;
        this.projects = projects;
        this.projectManager = projectManager;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;
    }

    // Getters and Setters
    public Long getUsersId() {
        return usersId;
    }

    public void setUsersId(Long usersId) {
        this.usersId = usersId;
    }

    public Long getEmployeeId() {  // Getter for employeeId
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {  // Setter for employeeId
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public LocalDateTime getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDateTime joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmployeeeId(Long aLong) {

    }
}
