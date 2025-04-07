package com.employeeportal.serviceImpl;
import com.employeeportal.model.EmployeeRelative;
import com.employeeportal.repository.EmployeeRelativeRepository;
import com.employeeportal.service.EmployeeRelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeRelativeServiceImpl implements EmployeeRelativeService {
    @Autowired
    private EmployeeRelativeRepository employeeRelativeRepository;

    @Override
    public EmployeeRelative saveEmployeeRelative(EmployeeRelative employeeRelative) {
        EmployeeRelative addEmployeeRelative = employeeRelativeRepository.save(employeeRelative);
        return addEmployeeRelative;
    }

    @Override
    public List<EmployeeRelative> getAllEmployeeRelatives() {
        List<EmployeeRelative> employeeRelatives = employeeRelativeRepository.findAll();
        return employeeRelatives;
    }

    @Override
    public EmployeeRelative getEmployeeRelativeById(Long employeeRelativeId) {
        EmployeeRelative getEmployee = employeeRelativeRepository.findById(employeeRelativeId).orElse(null);
        return getEmployee;
    }

    @Override
    public EmployeeRelative updateEmployeeRelativeById(Long employeeRelativeId, EmployeeRelative employeeRelative) {
        EmployeeRelative existingEmployeeRelative = employeeRelativeRepository.findById(employeeRelativeId).get();
        existingEmployeeRelative.setHasRelative(employeeRelative.getHasRelative());

        return existingEmployeeRelative;
    }

    @Override
    public EmployeeRelative deleteEmployeeRelativeById(Long employeeRelativeId) {
        EmployeeRelative  employeeRelativeDelete = employeeRelativeRepository.findById(employeeRelativeId).orElse(null);
        if(employeeRelativeDelete != null){
            employeeRelativeRepository.delete(employeeRelativeDelete);
            return employeeRelativeDelete;
        }else{
            throw new RuntimeException("employeeRelative not found with id: " + employeeRelativeId);
        }

    }
    }



