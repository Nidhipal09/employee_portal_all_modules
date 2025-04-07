package com.employeeportal.model.dto;
import java.time.LocalDate;

public class EmployeeDetailsDTO {

    private LocalDate joiningDate;
    private String placeOfWork;
    private String employmentType;

    public EmployeeDetailsDTO() {
    }

    public EmployeeDetailsDTO(LocalDate joiningDate, String placeOfWork, String employmentType) {
        this.joiningDate = joiningDate;
        this.placeOfWork = placeOfWork;
        this.employmentType = employmentType;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }
}
