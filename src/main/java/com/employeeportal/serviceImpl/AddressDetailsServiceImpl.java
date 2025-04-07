package com.employeeportal.serviceImpl;

import com.employeeportal.model.AddressDetails;
import com.employeeportal.model.PersonalDetails;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.repository.AddressDetailsRepository;
import com.employeeportal.repository.PrimaryDetailsRepository;
import com.employeeportal.service.AddressDetailsService;
import com.employeeportal.service.PrimaryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class AddressDetailsServiceImpl  implements AddressDetailsService {
    @Autowired
    private AddressDetailsRepository addressDetailsRepo;
    @Override
    public AddressDetails saveAddressDetails(AddressDetails addressDetails) {
        AddressDetails addAddressDetails = addressDetailsRepo.save(addressDetails);
        return addAddressDetails;
    }

    @Override
    public List<AddressDetails> getAllAddressDetails() {
        List<AddressDetails> addressDetails= addressDetailsRepo.findAll();
        return addressDetails;

    }

    @Override
    public AddressDetails getAddressDetailsById(Long addressId) {
        AddressDetails addressDetail = addressDetailsRepo.findById(addressId).orElse(null);
        return addressDetail;
    }

    @Override
    public AddressDetails updateAddressDetailsById(Long addressId, AddressDetails addressDetails) {
        // Retrieve the existing address details
        AddressDetails existingAddressDetails = addressDetailsRepo.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + addressId));

        // Update the fields using the values from the input if they are valid (not null or blank)
        existingAddressDetails.setStayFrom(addressDetails.getStayFrom() != null ? addressDetails.getStayFrom() : existingAddressDetails.getStayFrom());
        existingAddressDetails.setStayTo(addressDetails.getStayTo() != null ? addressDetails.getStayTo() : existingAddressDetails.getStayTo());
        existingAddressDetails.setAddressLine(isValidValue(addressDetails.getAddressLine()) ? addressDetails.getAddressLine() : existingAddressDetails.getAddressLine());
        existingAddressDetails.setPincode(isValidValue(addressDetails.getPincode()) ? addressDetails.getPincode() : existingAddressDetails.getPincode());
        existingAddressDetails.setCountry(isValidValue(addressDetails.getCountry()) ? addressDetails.getCountry() : existingAddressDetails.getCountry());
        existingAddressDetails.setContactNumberWithRelationship(isValidValue(addressDetails.getContactNumberWithRelationship()) ? addressDetails.getContactNumberWithRelationship() : existingAddressDetails.getContactNumberWithRelationship());

        // Save the updated address details
        addressDetailsRepo.save(existingAddressDetails);

        // Return the updated entity
        return existingAddressDetails;
    }

    // Helper method to check if a string value is not null or blank
    private boolean isValidValue(String value) {
        return value != null && !value.trim().isEmpty();
    }



    @Override
    public AddressDetails deleteAddressDetailsById(Long addressId) {
        AddressDetails addressDetailsDelete = addressDetailsRepo.findById(addressId).orElse(null);
        if (addressDetailsDelete != null) {
            addressDetailsRepo.delete(addressDetailsDelete);
            return addressDetailsDelete;
        } else {
            throw new RuntimeException("AddressDetails not found with id: " + addressId);
        }

    }

}

