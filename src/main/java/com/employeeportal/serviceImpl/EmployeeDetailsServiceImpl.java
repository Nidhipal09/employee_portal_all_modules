package com.employeeportal.serviceImpl;

import com.employeeportal.model.EmployeeDetails;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.repository.EmployeeDetailsRepository;
import com.employeeportal.repository.PrimaryDetailsRepository;
import com.employeeportal.service.EmployeeDetailsService;
import com.employeeportal.service.PrimaryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeDetailsServiceImpl  implements EmployeeDetailsService {
    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepo;
    @Override
    public EmployeeDetails saveEmployeeDetails(EmployeeDetails employeeDetails) {
        EmployeeDetails addEmployeeDetails = employeeDetailsRepo.save(employeeDetails);
        return addEmployeeDetails;

    }

    @Override
    public List<EmployeeDetails> getAllEmployeeDetails() {
        List<EmployeeDetails> employeeDetails = employeeDetailsRepo.findAll();
        return employeeDetails;
    }

    @Override
    public EmployeeDetails getEmployeeDetailsById(Long employeeId) {
        EmployeeDetails employeeDetail = employeeDetailsRepo.findById(employeeId).orElse(null);
        return employeeDetail;
    }

    @Override
    public EmployeeDetails updateEmployeeDetailsById(Long employeeId, EmployeeDetails employeeDetails) {

        EmployeeDetails existingEmployeeDetails = employeeDetailsRepo.findById(employeeId).get();

        existingEmployeeDetails.setJoiningDate(employeeDetails.getJoiningDate());
        existingEmployeeDetails.setPlaceOfWork(employeeDetails.getPlaceOfWork());
        existingEmployeeDetails.setEmploymentType(employeeDetails.getEmploymentType());
        employeeDetailsRepo.save(existingEmployeeDetails);
        return existingEmployeeDetails;
    }

    @Override
    public EmployeeDetails deleteEmployeeDetailsById(Long employeeId) {
        EmployeeDetails  employeeDetailsDelete = employeeDetailsRepo.findById(employeeId).orElse(null);
        if (employeeDetailsDelete != null) {
            employeeDetailsRepo.delete(employeeDetailsDelete);
            return employeeDetailsDelete;
        } else {
            throw new RuntimeException("EmployeeDetails not found with id: " + employeeId);
        }
    }
}
