package com.employeeportal.repository;

import com.employeeportal.model.DocumentCertificates;
import com.employeeportal.model.PrimaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentCertificatesRepository extends JpaRepository<DocumentCertificates, Long> {

    List<DocumentCertificates> findAllByPrimaryDetails(PrimaryDetails obj);

    Optional<DocumentCertificates> findByDocumentId(Long documentId);
}
