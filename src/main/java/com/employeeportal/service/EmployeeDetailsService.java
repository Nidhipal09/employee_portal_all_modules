package com.employeeportal.service;

import com.employeeportal.model.EmployeeDetails;
import com.employeeportal.model.PrimaryDetails;

import java.util.List;

public interface EmployeeDetailsService {

    EmployeeDetails saveEmployeeDetails(EmployeeDetails employeeDetails);
    List<EmployeeDetails> getAllEmployeeDetails();
    EmployeeDetails getEmployeeDetailsById(Long employeeId);
    EmployeeDetails updateEmployeeDetailsById(Long employeeId, EmployeeDetails employeeDetails);
    EmployeeDetails deleteEmployeeDetailsById(Long employeeId);
}
