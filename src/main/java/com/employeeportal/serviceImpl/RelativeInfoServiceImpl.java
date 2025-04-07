package com.employeeportal.serviceImpl;

import com.employeeportal.model.CurrentAddress;
import com.employeeportal.model.RelativeInfo;
import com.employeeportal.repository.RelativeInfoRepository;
import com.employeeportal.service.RelativeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RelativeInfoServiceImpl implements RelativeInfoService {
    @Autowired
    private RelativeInfoRepository relativeInfoRepository;

    @Override
    public List<RelativeInfo> getAllRelatives() {
        return relativeInfoRepository.findAll();
    }

    @Override
    public RelativeInfo getRelativeById(Long id) {
        return relativeInfoRepository.findById(id).orElse(null);
    }

    @Override
    public RelativeInfo saveRelative(RelativeInfo relativeInfo) {
        return relativeInfoRepository.save(relativeInfo);
    }
    @Override
    public RelativeInfo updateRelative(Long id, RelativeInfo relativeInfo) {
        // Check if the relative exists by ID
        RelativeInfo existingRelativeInfo = relativeInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relative not found for ID: " + id));

        // Update fields only if they are valid (non-null and non-empty)
        existingRelativeInfo.setName(isValidValue(relativeInfo.getName()) ? relativeInfo.getName() : existingRelativeInfo.getName());
        existingRelativeInfo.setEmployeeId(isValidValue(relativeInfo.getEmployeeId()) ? relativeInfo.getEmployeeId() : existingRelativeInfo.getEmployeeId());
        existingRelativeInfo.setRelationship(isValidValue(relativeInfo.getRelationship()) ? relativeInfo.getRelationship() : existingRelativeInfo.getRelationship());
        existingRelativeInfo.setDepartment(isValidValue(relativeInfo.getDepartment()) ? relativeInfo.getDepartment() : existingRelativeInfo.getDepartment());
        existingRelativeInfo.setLocation(isValidValue(relativeInfo.getLocation()) ? relativeInfo.getLocation() : existingRelativeInfo.getLocation());
        existingRelativeInfo.setRemarks(isValidValue(relativeInfo.getRemarks()) ? relativeInfo.getRemarks() : existingRelativeInfo.getRemarks());

        // Save the updated relative information
         relativeInfoRepository.save(existingRelativeInfo);
         return  existingRelativeInfo;
    }


    // Helper method to check if a string value is not null or blank
    private boolean isValidValue(String value) {
        return value != null && !value.trim().isEmpty();
    }

    @Override
    public RelativeInfo deleteRelativeInfoById(Long relativeId) {
        RelativeInfo relativeInfoDelete = relativeInfoRepository.findById(relativeId).orElse(null);
        if (relativeInfoDelete != null) {
            relativeInfoRepository.delete(relativeInfoDelete);
            return relativeInfoDelete;
        } else {
            throw new RuntimeException("currentAddress not found with id: " + relativeId);
        }
    }
    }



