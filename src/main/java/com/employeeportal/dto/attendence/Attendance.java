package com.employeeportal.dto.attendence;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary Key
    private Long userId;  // Who is the user
    private LocalDateTime punchInTime;
    private LocalDateTime punchOutTime;
    private Boolean wfh;
    private String userName;

    public Attendance() {
    }

    public Attendance(Long id, Long userId, LocalDateTime punchInTime, LocalDateTime punchOutTime, Boolean wfh, String userName) {
        this.id = id;
        this.userId = userId;
        this.punchInTime = punchInTime;
        this.punchOutTime = punchOutTime;
        this.wfh = wfh;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getPunchInTime() {
        return punchInTime;
    }

    public void setPunchInTime(LocalDateTime punchInTime) {
        this.punchInTime = punchInTime;
    }

    public LocalDateTime getPunchOutTime() {
        return punchOutTime;
    }

    public void setPunchOutTime(LocalDateTime punchOutTime) {
        this.punchOutTime = punchOutTime;
    }

    public Boolean getWfh() {
        return wfh;
    }

    public void setWfh(Boolean wfh) {
        this.wfh = wfh;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
