package com.employeeportal.controller;

import com.employeeportal.model.CurrentAddress;
import com.employeeportal.model.WorkPermit;
import com.employeeportal.service.WorkPermitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/work-permit")
public class WorkPermitController {

    @Autowired
    private WorkPermitService workPermitService;

    @PostMapping("/save")
    public ResponseEntity<WorkPermit> createVisaStatus(@RequestBody WorkPermit workPermit) {
        return ResponseEntity.ok(workPermitService.createWorkPermit(workPermit));
    }

    @GetMapping("/{workId}")
    public ResponseEntity<WorkPermit> getVisaStatusById(@PathVariable Long workId) {
        WorkPermit workPermit = workPermitService.getWorkPermitById(workId);
        return workPermit != null ? ResponseEntity.ok(workPermit) : ResponseEntity.notFound().build();
    }

    @GetMapping("/allWorkPermit")
    public ResponseEntity<List<WorkPermit>> getAllVisaStatuses() {
        return ResponseEntity.ok(workPermitService.getAllWorkPermit());
    }

    @PutMapping("/update/{workId}")
    public ResponseEntity<WorkPermit> updateWorkPermit(@PathVariable Long workId, @RequestBody WorkPermit workPermit) {
        WorkPermit updatedWorkPermit = workPermitService.updateWorkPermit(workId, workPermit);
        return updatedWorkPermit != null ? ResponseEntity.ok(updatedWorkPermit) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{workId}")
    public ResponseEntity<String> deleteWorkPermitById(@PathVariable Long workId) {
        WorkPermit isDeleted = workPermitService.deleteWorkPermitById(workId);
        return new ResponseEntity<>("data deleted", HttpStatus.OK);
    }
}
