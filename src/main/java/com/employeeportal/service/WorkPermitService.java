package com.employeeportal.service;

import com.employeeportal.model.WorkPermit;

import java.util.List;

public interface WorkPermitService {
    WorkPermit createWorkPermit(WorkPermit workPermit);
    WorkPermit getWorkPermitById(Long workId);
    List<WorkPermit> getAllWorkPermit();
    WorkPermit updateWorkPermit(Long workId, WorkPermit workPermit);
    WorkPermit deleteWorkPermitById(Long workId);
}
