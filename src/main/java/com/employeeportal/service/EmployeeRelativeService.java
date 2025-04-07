package com.employeeportal.service;

import com.employeeportal.model.EmployeeRelative;
import com.employeeportal.model.dto.EmployeeRelativeDTO;

import java.util.List;

public interface EmployeeRelativeService {
    EmployeeRelative saveEmployeeRelative(EmployeeRelative employeeRelative);
    List<EmployeeRelative> getAllEmployeeRelatives();
    EmployeeRelative getEmployeeRelativeById(Long employeeRelativeId);
    EmployeeRelative updateEmployeeRelativeById(Long employeeRelativeId,EmployeeRelative employeeRelative);
    EmployeeRelative deleteEmployeeRelativeById(Long employeeRelativeId);
}
