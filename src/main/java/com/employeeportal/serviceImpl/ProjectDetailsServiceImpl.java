package com.employeeportal.serviceImpl;

import com.employeeportal.exception.NotFoundException;
import com.employeeportal.model.ProjectDetails;
import com.employeeportal.model.dto.ProjectDetailsDTO;
import com.employeeportal.repository.ProjectDetailsRepository;
import com.employeeportal.repository.UsersRepository;
import com.employeeportal.service.ProjectDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectDetailsServiceImpl implements ProjectDetailsService {

    @Autowired
    private ProjectDetailsRepository repository;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public ProjectDetails saveProjectDetails(ProjectDetailsDTO projectDetails) {
        ProjectDetails obj = new ProjectDetails();
        obj.setClient(projectDetails.getClient());
        obj.setLocation(projectDetails.getLocation());
      //  obj.setReportingManager(projectDetails.getReportingManager());
        obj.setReportingManagerId(projectDetails.getReportingManagerId());

        return repository.save(obj);
    }

    @Override
    public List<ProjectDetails> getAllProjectDetails() {
        return repository.findAll();
    }

    @Override
    public Optional<ProjectDetails> getProjectDetailsById(Long projectId) {
        return repository.findById(projectId);
    }

    @Override
    public ProjectDetails updateProjectDetails(Long projectId, ProjectDetails updatedDetails) {
        ProjectDetails existingProject = repository.findById(projectId)
                .orElseThrow(() -> new NotFoundException("ProjectDetails not found with id: " + projectId));

        // Update only if values are present
        existingProject.setClient(isValidValue(updatedDetails.getClient()) ? updatedDetails.getClient() : existingProject.getClient());
        existingProject.setReportingManager(isValidValue(updatedDetails.getReportingManager()) ? updatedDetails.getReportingManager() : existingProject.getReportingManager());
        existingProject.setLocation(isValidValue(updatedDetails.getLocation()) ? updatedDetails.getLocation() : existingProject.getLocation());

        return repository.save(existingProject);
    }

    @Override
    public void deleteProjectDetails(Long projectId) {
        ProjectDetails project = repository.findById(projectId)
                .orElseThrow(() -> new NotFoundException("ProjectDetails not found with id: " + projectId));
        repository.delete(project);
    }

    // Helper method
    private boolean isValidValue(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
