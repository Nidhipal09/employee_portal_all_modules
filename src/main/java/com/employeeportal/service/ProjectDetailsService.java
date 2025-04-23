package com.employeeportal.service;

import com.employeeportal.model.ProjectDetails;
import com.employeeportal.model.dto.ProjectDetailsDTO;

import java.util.List;
import java.util.Optional;

public interface ProjectDetailsService {
    ProjectDetails saveProjectDetails(ProjectDetailsDTO projectDetails);
    List<ProjectDetails> getAllProjectDetails();
    Optional<ProjectDetails> getProjectDetailsById(Long projectId);
    ProjectDetails updateProjectDetails(Long projectId, ProjectDetails projectDetails);
    void deleteProjectDetails(Long projectId);
}