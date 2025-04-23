package com.employeeportal.controller;

import com.employeeportal.model.CurrentAddress;
import com.employeeportal.model.RelativeInfo;
import com.employeeportal.service.RelativeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatives")
@CrossOrigin(origins = "*")
public class RelativeInfoController {
    @Autowired
    private RelativeInfoService relativeInfoService;
    @PostMapping
    public ResponseEntity<RelativeInfo> createRelative(@RequestBody RelativeInfo relativeInfo) {
        RelativeInfo savedRelative = relativeInfoService.saveRelative(relativeInfo);
        return new ResponseEntity<RelativeInfo>(savedRelative, HttpStatus.CREATED);
    }
    @GetMapping("/{relativeId}")
    public ResponseEntity<RelativeInfo> getRelativeById(@PathVariable Long relativeId) {
        RelativeInfo relative = relativeInfoService.getRelativeById(relativeId);
        if (relative != null) {
            return new ResponseEntity<RelativeInfo>(relative, HttpStatus.OK);
        } else {
            return new ResponseEntity<RelativeInfo>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/allRelativeInfo")
    public ResponseEntity<List<RelativeInfo>> getAllRelatives() {
        List<RelativeInfo> relatives = relativeInfoService.getAllRelatives();
        return new ResponseEntity<List<RelativeInfo>>(relatives, HttpStatus.OK);
    }
    @PutMapping("/update/{relativeId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    public ResponseEntity<RelativeInfo> updateRelative(@PathVariable Long relativeId, @RequestBody RelativeInfo relativeInfo) {
        RelativeInfo updatedRelative = relativeInfoService.updateRelative(relativeId, relativeInfo);
        if (updatedRelative != null) {
            return new ResponseEntity<RelativeInfo>(updatedRelative, HttpStatus.OK);
        } else {
            return new ResponseEntity<RelativeInfo>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{relativeId}")
    public ResponseEntity<String> deleteRelativeInfoById(@PathVariable Long relativeId) {
        RelativeInfo isDeleted = relativeInfoService.deleteRelativeInfoById(relativeId);
        return new ResponseEntity<>("data deleted", HttpStatus.OK);
    }
}
