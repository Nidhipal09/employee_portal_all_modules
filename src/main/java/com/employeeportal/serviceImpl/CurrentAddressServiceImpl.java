package com.employeeportal.serviceImpl;

import com.employeeportal.model.CurrentAddress;
import com.employeeportal.model.PermanentAddress;
import com.employeeportal.repository.CurrentAddressRepository;
import com.employeeportal.repository.PermanentAddressRepository;
import com.employeeportal.service.CurrentAddressService;
import com.employeeportal.service.PermanentAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CurrentAddressServiceImpl   implements CurrentAddressService {
    @Autowired
    private CurrentAddressRepository currentAddressRepo;
    @Override
    public CurrentAddress saveCurrentAddress(CurrentAddress currentAddress) {
        CurrentAddress addCurrentAddress = currentAddressRepo.save(currentAddress);
        return addCurrentAddress;
    }

    @Override
    public List<CurrentAddress> getAllCurrentAddress() {
        List<CurrentAddress> currentAddress = currentAddressRepo.findAll();
        return currentAddress;
    }

    @Override
    public CurrentAddress getCurrentAddressById(Long currentId) {
        CurrentAddress currentAddress = currentAddressRepo.findById(currentId).orElse(null);
        return currentAddress;
    }

//    @Override
//    public CurrentAddress updateCurrentAddressById(Long currentId, CurrentAddress currentAddress) {
//        CurrentAddress existingCurrentAddress=currentAddressRepo.findById(currentId).get();
//
//        existingCurrentAddress.setSameAsPermanentAddress(CurrentAddress.getSameAsPermanentAddress());
//        existingCurrentAddress.setHouseNumber(CurrentAddress.getHouseNumber());
//        existingCurrentAddress.setStreetName(CurrentAddress.getStreetName());
//        existingCurrentAddress.setTown(CurrentAddress.getTown());
//        existingCurrentAddress.setPincode(CurrentAddress.getPincode());
//        existingCurrentAddress.setState(PermanentAddress.getState());
//        existingCurrentAddress.setCity(PermanentAddress.getCity());
//        existingCurrentAddress.setStayFrom(PermanentAddress.getStayFrom());
//        existingCurrentAddress.setStayTo(PermanentAddress.getStayTo());
//        existingCurrentAddress.setEmergencyContactNumber(PermanentAddress.getEmergencyContactNumber());
//        existingCurrentAddress.setEmergencyContactNameAndRelationship(PermanentAddress.getEmergencyContactNameAndRelationship());
//        currentAddressRepo.save(existingCurrentAddress);
//        return existingCurrentAddress;
//    }
@Override
public CurrentAddress updateCurrentAddressById(Long currentId, CurrentAddress currentAddress) {
    // Check if currentAddress is null
    if (currentAddress == null) {
        throw new IllegalArgumentException("CurrentAddress cannot be null");
    }

    // Fetch existing CurrentAddress by ID
    Optional<CurrentAddress> existingOptional = currentAddressRepo.findById(currentId);

    // Check if the existing CurrentAddress is present
    if (!existingOptional.isPresent()) {
        throw new EntityNotFoundException("CurrentAddress not found for ID: " + currentId);
    }

    CurrentAddress existingCurrentAddress = existingOptional.get();

    // Update fields using the provided currentAddress object, but only if the value is not null or blank
    existingCurrentAddress.setSameAsPermanentAddress(currentAddress.getSameAsPermanentAddress() != null ? currentAddress.getSameAsPermanentAddress() : existingCurrentAddress.getSameAsPermanentAddress());
    existingCurrentAddress.setHouseNumber(isValidValue(currentAddress.getHouseNumber()) ? currentAddress.getHouseNumber() : existingCurrentAddress.getHouseNumber());
    existingCurrentAddress.setStreetName(isValidValue(currentAddress.getStreetName()) ? currentAddress.getStreetName() : existingCurrentAddress.getStreetName());
    existingCurrentAddress.setTown(isValidValue(currentAddress.getTown()) ? currentAddress.getTown() : existingCurrentAddress.getTown());
    existingCurrentAddress.setPincode(isValidValue(currentAddress.getPincode()) ? currentAddress.getPincode() : existingCurrentAddress.getPincode());
    existingCurrentAddress.setState(isValidValue(currentAddress.getState()) ? currentAddress.getState() : existingCurrentAddress.getState());
    existingCurrentAddress.setCity(isValidValue(currentAddress.getCity()) ? currentAddress.getCity() : existingCurrentAddress.getCity());
    existingCurrentAddress.setStayFrom(currentAddress.getStayFrom() != null ? currentAddress.getStayFrom() : existingCurrentAddress.getStayFrom());
    existingCurrentAddress.setStayTo(currentAddress.getStayTo() != null ? currentAddress.getStayTo() : existingCurrentAddress.getStayTo());
    existingCurrentAddress.setEmergencyContactNumber(isValidValue(currentAddress.getEmergencyContactNumber()) ? currentAddress.getEmergencyContactNumber() : existingCurrentAddress.getEmergencyContactNumber());
    existingCurrentAddress.setEmergencyContactNameAndRelationship(isValidValue(currentAddress.getEmergencyContactNameAndRelationship()) ? currentAddress.getEmergencyContactNameAndRelationship() : existingCurrentAddress.getEmergencyContactNameAndRelationship());

    // Save the updated entity
    currentAddressRepo.save(existingCurrentAddress);

    // Return the updated entity
    return existingCurrentAddress;
}

    // Helper method to check if a string value is not null or blank
    private boolean isValidValue(String value) {
        return value != null && !value.trim().isEmpty();
    }

    @Override
    public CurrentAddress deleteCurrentAddressById(Long currentId) {

        CurrentAddress currentAddressDelete = currentAddressRepo.findById(currentId).orElse(null);
        if (currentAddressDelete != null) {
            currentAddressRepo.delete(currentAddressDelete);
            return currentAddressDelete;
        } else {
            throw new RuntimeException("currentAddress not found with id: " + currentId);
        }
    }
}
