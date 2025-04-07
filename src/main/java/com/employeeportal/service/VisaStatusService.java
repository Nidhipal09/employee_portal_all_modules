package com.employeeportal.service;

import com.employeeportal.model.VisaStatus;

import java.util.List;
import java.util.Optional;

public interface VisaStatusService {
    VisaStatus getVisaStatusById(Long visaId);
    List<VisaStatus> getAllVisaStatus();
    VisaStatus saveVisaStatus(VisaStatus visaStatus);

    VisaStatus updateVisaStatusById(Long visaId, VisaStatus visaStatus);

    VisaStatus deleteVisaStatusById(Long visaId);

    //void deleteVisaStatus(Long visaId);
}
