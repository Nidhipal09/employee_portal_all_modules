package com.employeeportal.model;

import javax.persistence.*;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usersId;
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

}
