package com.employeeportal.repository;
import com.employeeportal.model.UploadedFiles;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UploadedFilesRepository extends JpaRepository<UploadedFiles, Long> {

}

