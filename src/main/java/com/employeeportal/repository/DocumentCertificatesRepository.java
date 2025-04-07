package com.employeeportal.repository;

import com.employeeportal.model.DocumentCertificates;
import com.employeeportal.model.PrimaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentCertificatesRepository extends JpaRepository<DocumentCertificates, Long> {

    List<DocumentCertificates> findAllByPrimaryDetails(PrimaryDetails obj);
}
