package com.employeeportal.repository.onboarding;

import com.employeeportal.model.onboarding.EmployeeOrganizationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeOrganizationDetailsRepository extends JpaRepository<EmployeeOrganizationDetails, Long> {
    EmployeeOrganizationDetails findByEmployeeId(int employeeId);
}