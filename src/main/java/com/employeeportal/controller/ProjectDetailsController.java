package com.employeeportal.controller;

import com.employeeportal.exception.ResourceNotFoundException;
import com.employeeportal.model.ProjectDetails;
import com.employeeportal.model.dto.ProjectDetailsDTO;
import com.employeeportal.service.ProjectDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projectDetails")
@CrossOrigin(origins = "*")
public class ProjectDetailsController {

    @Autowired
    private ProjectDetailsService projectDetailsService;

    @PostMapping("/save")
    public ResponseEntity<ProjectDetails> saveProjectDetails(@RequestBody ProjectDetailsDTO projectDetails) {
        ProjectDetails savedProject = projectDetailsService.saveProjectDetails(projectDetails);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProjectDetails>> getAllProjectDetails() {
        List<ProjectDetails> projects = projectDetailsService.getAllProjectDetails();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDetails> getProjectDetailsById(@PathVariable("projectId") Long projectId) {
        Optional<ProjectDetails> projectDetails = projectDetailsService.getProjectDetailsById(projectId);

        if (projectDetails.isPresent()) {
            return new ResponseEntity<>(projectDetails.get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Project not found with id " + projectId);
        }
    }

    @PutMapping("/update/{projectId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<ProjectDetails> updateProjectDetails(@PathVariable("projectId") Long projectId,
                                                               @RequestBody ProjectDetails updatedDetails) {
        ProjectDetails updated = projectDetailsService.updateProjectDetails(projectId, updatedDetails);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<String> deleteProjectDetails(@PathVariable("projectId") Long projectId) {
        projectDetailsService.deleteProjectDetails(projectId);
        return new ResponseEntity<>("Project deleted successfully", HttpStatus.OK);
    }
}
