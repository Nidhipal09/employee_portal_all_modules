package com.employeeportal.repository;

import com.employeeportal.model.EmployeeRelative;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.RelativeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelativeInfoRepository extends JpaRepository<RelativeInfo,Long> {
    List<RelativeInfo> findAllByEmployeeRelative(EmployeeRelative employeeRelative);
}
