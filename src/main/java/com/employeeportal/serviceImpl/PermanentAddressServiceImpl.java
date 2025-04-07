package com.employeeportal.serviceImpl;

import com.employeeportal.model.PermanentAddress;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.repository.PermanentAddressRepository;
import com.employeeportal.repository.PrimaryDetailsRepository;
import com.employeeportal.service.EmployeeDetailsService;
import com.employeeportal.service.PermanentAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermanentAddressServiceImpl implements PermanentAddressService{
    @Autowired
    private PermanentAddressRepository  permanentAddressRepo;
    @Override
    public PermanentAddress savePermanentAddress(PermanentAddress permanentAddress) {
        PermanentAddress addPermanentAddress = permanentAddressRepo.save(permanentAddress);
        return addPermanentAddress;
    }

    @Override
    public List<PermanentAddress> getAllPermanentAddress() {
        List<PermanentAddress> permanentAddress = permanentAddressRepo.findAll();
        return permanentAddress;
    }

    @Override
    public PermanentAddress getPermanentAddressById(Long permanentId) {
        PermanentAddress permanentAddres = permanentAddressRepo.findById(permanentId).orElse(null);
        return permanentAddres;
    }


//    @Override
//    public PermanentAddress updatePermanentAddressById(Long permanentId, PermanentAddress permanentAddress) {
//        // Retrieve the existing PermanentAddress from the database by ID
//        PermanentAddress existingPermanentAddress = permanentAddressRepo.findById(permanentId).orElseThrow(() ->
//                new RuntimeException("Permanent Address not found for ID: " + permanentId));
//
//        // Update the existingPermanentAddress fields using the provided permanentAddress instance
//        existingPermanentAddress.setHouseNumber(permanentAddress.getHouseNumber());
//        existingPermanentAddress.setStreetName(permanentAddress.getStreetName());
//        existingPermanentAddress.setTown(permanentAddress.getTown());
//        existingPermanentAddress.setPincode(permanentAddress.getPincode());
//        existingPermanentAddress.setState(permanentAddress.getState());
//        existingPermanentAddress.setCity(permanentAddress.getCity());
//        existingPermanentAddress.setStayFrom(permanentAddress.getStayFrom());
//        existingPermanentAddress.setStayTo(permanentAddress.getStayTo());
//        existingPermanentAddress.setEmergencyContactNumber(permanentAddress.getEmergencyContactNumber());
//        existingPermanentAddress.setEmergencyContactNameAndRelationship(permanentAddress.getEmergencyContactNameAndRelationship());
//
//        // Save the updated entity
//        permanentAddressRepo.save(existingPermanentAddress);
//        return existingPermanentAddress;
//    }
//@Override
//public PermanentAddress updatePermanentAddressById(Long permanentId, PermanentAddress permanentAddress) {
//    // Retrieve the existing PermanentAddress from the database by ID
//    PermanentAddress existingPermanentAddress = permanentAddressRepo.findById(permanentId).orElseThrow(() ->
//            new RuntimeException("Permanent Address not found for ID: " + permanentId));
//
//    // Check if the fields are not null before setting
//    existingPermanentAddress.setHouseNumber(permanentAddress.getHouseNumber() != null ? permanentAddress.getHouseNumber() : existingPermanentAddress.getHouseNumber());
//    existingPermanentAddress.setStreetName(permanentAddress.getStreetName() != null ? permanentAddress.getStreetName() : existingPermanentAddress.getStreetName());
//    existingPermanentAddress.setTown(permanentAddress.getTown() != null ? permanentAddress.getTown() : existingPermanentAddress.getTown());
//    existingPermanentAddress.setPincode(permanentAddress.getPincode() != null ? permanentAddress.getPincode() : existingPermanentAddress.getPincode());
//    existingPermanentAddress.setState(permanentAddress.getState() != null ? permanentAddress.getState() : existingPermanentAddress.getState());
//    existingPermanentAddress.setCity(permanentAddress.getCity() != null ? permanentAddress.getCity() : existingPermanentAddress.getCity());
//    existingPermanentAddress.setStayFrom(permanentAddress.getStayFrom() != null ? permanentAddress.getStayFrom() : existingPermanentAddress.getStayFrom());
//    existingPermanentAddress.setStayTo(permanentAddress.getStayTo() != null ? permanentAddress.getStayTo() : existingPermanentAddress.getStayTo());
//    existingPermanentAddress.setEmergencyContactNumber(permanentAddress.getEmergencyContactNumber() != null ? permanentAddress.getEmergencyContactNumber() : existingPermanentAddress.getEmergencyContactNumber());
//    existingPermanentAddress.setEmergencyContactNameAndRelationship(permanentAddress.getEmergencyContactNameAndRelationship() != null ? permanentAddress.getEmergencyContactNameAndRelationship() : existingPermanentAddress.getEmergencyContactNameAndRelationship());
//
//    // Save the updated entity
//    permanentAddressRepo.save(existingPermanentAddress);
//    return existingPermanentAddress;
//}

    @Override
    public PermanentAddress updatePermanentAddressById(Long permanentId, PermanentAddress permanentAddress) {
        // Retrieve the existing PermanentAddress from the database by ID
        PermanentAddress existingPermanentAddress = permanentAddressRepo.findById(permanentId).orElseThrow(() ->
                new RuntimeException("Permanent Address not found for ID: " + permanentId));

        // Check if the fields are not null or blank before setting (only set if the value is not null or blank)
        existingPermanentAddress.setHouseNumber(isValidValue(permanentAddress.getHouseNumber()) ? permanentAddress.getHouseNumber() : existingPermanentAddress.getHouseNumber());
        existingPermanentAddress.setStreetName(isValidValue(permanentAddress.getStreetName()) ? permanentAddress.getStreetName() : existingPermanentAddress.getStreetName());
        existingPermanentAddress.setTown(isValidValue(permanentAddress.getTown()) ? permanentAddress.getTown() : existingPermanentAddress.getTown());
        existingPermanentAddress.setPincode(isValidValue(permanentAddress.getPincode()) ? permanentAddress.getPincode() : existingPermanentAddress.getPincode());
        existingPermanentAddress.setState(isValidValue(permanentAddress.getState()) ? permanentAddress.getState() : existingPermanentAddress.getState());
        existingPermanentAddress.setCity(isValidValue(permanentAddress.getCity()) ? permanentAddress.getCity() : existingPermanentAddress.getCity());
        existingPermanentAddress.setStayFrom(permanentAddress.getStayFrom() != null ? permanentAddress.getStayFrom() : existingPermanentAddress.getStayFrom());
        existingPermanentAddress.setStayTo(permanentAddress.getStayTo() != null ? permanentAddress.getStayTo() : existingPermanentAddress.getStayTo());
        existingPermanentAddress.setEmergencyContactNumber(isValidValue(permanentAddress.getEmergencyContactNumber()) ? permanentAddress.getEmergencyContactNumber() : existingPermanentAddress.getEmergencyContactNumber());
        existingPermanentAddress.setEmergencyContactNameAndRelationship(isValidValue(permanentAddress.getEmergencyContactNameAndRelationship()) ? permanentAddress.getEmergencyContactNameAndRelationship() : existingPermanentAddress.getEmergencyContactNameAndRelationship());

        // Save the updated entity
        permanentAddressRepo.save(existingPermanentAddress);
        return existingPermanentAddress;
    }

    // Helper method to check if a string value is not null or empty
    private boolean isValidValue(String value) {
        return value != null && !value.trim().isEmpty();
    }


    @Override
    public PermanentAddress deletePermanentAddressById(Long permanentId) {
        PermanentAddress permanentAddressDelete = permanentAddressRepo.findById(permanentId).orElse(null);
        if (permanentAddressDelete != null) {
            permanentAddressRepo.delete(permanentAddressDelete);
            return permanentAddressDelete;
        } else {
            throw new RuntimeException("permanentAddress not found with id: " + permanentId);
        }
    }
}
