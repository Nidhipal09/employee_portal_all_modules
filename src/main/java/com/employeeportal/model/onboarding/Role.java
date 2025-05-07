package com.employeeportal.model.onboarding;

import java.util.List;

import javax.persistence.*;

import com.employeeportal.dto.onboarding.EmployeeOrganizationDetailsDTO;
import com.employeeportal.model.registration.EmployeeReg;

import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(name = "role_name", nullable = false, length = 50)
    private String roleName;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    List<EmployeeReg> employeeRegList;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    List<EmployeeOrganizationDetails> employeeOrganizationDetailsList;

}