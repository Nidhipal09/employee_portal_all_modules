package com.employeeportal.serviceImpl;

import com.employeeportal.exception.BadRequestException;
import com.employeeportal.exception.NotFoundException;
import com.employeeportal.model.*;
import com.employeeportal.model.dto.AddressDTO;
import com.employeeportal.model.dto.AddressDetailsDTO;
import com.employeeportal.repository.AddressDetailsRepository;
import com.employeeportal.repository.CurrentAddressRepository;
import com.employeeportal.repository.PermanentAddressRepository;
import com.employeeportal.repository.PrimaryDetailsRepository;
import com.employeeportal.service.AddressDetailsService;
import com.employeeportal.service.PrimaryDetailsService;
import io.netty.util.internal.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressDetailsServiceImpl  implements AddressDetailsService {
    @Autowired
    private AddressDetailsRepository addressDetailsRepo;
    @Autowired
    private PrimaryDetailsRepository primaryDetailsRepository;
    @Autowired
    private PermanentAddressRepository permanentAddressRepository;
    @Autowired
    private CurrentAddressRepository currentAddressRepository;
    @Override
    public AddressResponseDTO saveAddressDetails(AddressDTO addressDetails) {
        Optional<PrimaryDetails> primaryDetailsOptional = primaryDetailsRepository.findById(addressDetails.getPrimaryId());
        Optional<PermanentAddress> permanentAddressOptional = permanentAddressRepository.findByPrimaryDetails(primaryDetailsOptional.get());
        Optional<CurrentAddress> currentAddressOptional = currentAddressRepository.findByPrimaryDetails(primaryDetailsOptional.get());
        List<AddressDetails> addressDetailsList = addressDetailsRepo.findByPrimaryDetails(primaryDetailsOptional.get());
        if(permanentAddressOptional.isPresent() && currentAddressOptional.isPresent() && !addressDetailsList.isEmpty()){
            throw new BadRequestException("address already exists");
        }
       if(!primaryDetailsOptional.isPresent()){
           throw new NotFoundException("user not found");
       }
        PermanentAddress permanent = null;
        if(!ObjectUtils.isEmpty(addressDetails.getPermanentAddressDTO())){
            PermanentAddress permanentAddress = new PermanentAddress();
            permanentAddress.setHouseNumber(addressDetails.getPermanentAddressDTO().getHouseNumber());
            permanentAddress.setStreetName(addressDetails.getPermanentAddressDTO().getStreetName());
            permanentAddress.setTown(addressDetails.getPermanentAddressDTO().getTown());
            permanentAddress.setPincode(addressDetails.getPermanentAddressDTO().getPincode());
            permanentAddress.setState(addressDetails.getPermanentAddressDTO().getState());
            permanentAddress.setCity(addressDetails.getPermanentAddressDTO().getCity());
            permanentAddress.setStayFrom(addressDetails.getPermanentAddressDTO().getStayFrom());
            permanentAddress.setStayTo(addressDetails.getPermanentAddressDTO().getStayTo());
            permanentAddress.setEmergencyContactNumber(addressDetails.getPermanentAddressDTO().getEmergencyContactNumber());
            permanentAddress.setEmergencyContactNameAndRelationship(addressDetails.getPermanentAddressDTO().getEmergencyContactNameAndRelationship());
            permanentAddress.setPrimaryDetails(primaryDetailsOptional.get());
             permanent = permanentAddressRepository.save(permanentAddress);

        }
        CurrentAddress current = null;
        if(!ObjectUtils.isEmpty(addressDetails.getCurrentAddressDTO())){
            CurrentAddress address = new CurrentAddress();
            address.setSameAsPermanentAddress(addressDetails.getCurrentAddressDTO().getSameAsPermanentAddress());
            address.setHouseNumber(addressDetails.getCurrentAddressDTO().getHouseNumber());
            address.setStreetName(addressDetails.getCurrentAddressDTO().getStreetName());
            address.setTown(addressDetails.getCurrentAddressDTO().getTown());
            address.setPincode(addressDetails.getCurrentAddressDTO().getPincode());
            address.setState(addressDetails.getCurrentAddressDTO().getState());
            address.setCity(addressDetails.getCurrentAddressDTO().getCity());
            address.setStayFrom(addressDetails.getCurrentAddressDTO().getStayFrom());
            address.setStayTo(addressDetails.getCurrentAddressDTO().getStayTo());
            address.setEmergencyContactNumber(addressDetails.getCurrentAddressDTO().getEmergencyContactNumber());
            address.setEmergencyContactNameAndRelationship(addressDetails.getCurrentAddressDTO().getEmergencyContactNameAndRelationship());
            address.setPrimaryDetails(primaryDetailsOptional.get());
             current = currentAddressRepository.save(address);
        }
        List<AddressDetails> li = new ArrayList<>();
        if(!ObjectUtils.isEmpty(addressDetails.getAddressDetailsDTOList())){
            List<AddressDetails> list = new ArrayList<>();
            for (AddressDetailsDTO lists : addressDetails.getAddressDetailsDTOList()) {
                AddressDetails address = new AddressDetails();
                address.setPincode(lists.getPincode());
                address.setAddressLine(lists.getAddressLine());
                address.setCountry(lists.getCountry());
                address.setStayFrom(lists.getStayFrom());
                address.setContactNumberWithRelationship(lists.getContactNumberWithRelationship());
                address.setStayTo(lists.getStayTo());
                address.setPrimaryDetails(primaryDetailsOptional.get());
                list.add(address);
            }
            li = addressDetailsRepo.saveAll(list);

        }

        AddressResponseDTO addAddressDetails = new AddressResponseDTO();
        addAddressDetails.setCurrentAddress(current);
        addAddressDetails.setPermanentAddress(permanent);
        addAddressDetails.setAddressDetailsList(li);
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

    @Override
    public AddressResponseDTO updateAddressDetails(AddressDTO addressDetails) {
           Optional<PermanentAddress> existingPermanentAddress = permanentAddressRepository.findByPermanentId(addressDetails.getPermanentAddressDTO().getPermanentId());
        Optional<CurrentAddress> existingCurrentAddress = currentAddressRepository.findByCurrentId(addressDetails.getCurrentAddressDTO().getCurrentId());



        PermanentAddress permanent = null;
        if(existingPermanentAddress.isPresent()) {
            if (!ObjectUtils.isEmpty(addressDetails.getPermanentAddressDTO())) {

                existingPermanentAddress.get().setHouseNumber(isValidValue(addressDetails.getPermanentAddressDTO().getHouseNumber()) ? addressDetails.getPermanentAddressDTO().getHouseNumber() : existingPermanentAddress.get().getHouseNumber());
                existingPermanentAddress.get().setStreetName(isValidValue(addressDetails.getPermanentAddressDTO().getStreetName()) ? addressDetails.getPermanentAddressDTO().getStreetName() : existingPermanentAddress.get().getStreetName());
                existingPermanentAddress.get().setTown(isValidValue(addressDetails.getPermanentAddressDTO().getTown()) ? addressDetails.getPermanentAddressDTO().getTown() : existingPermanentAddress.get().getTown());
                existingPermanentAddress.get().setPincode(isValidValue(addressDetails.getPermanentAddressDTO().getPincode()) ? addressDetails.getPermanentAddressDTO().getPincode() : existingPermanentAddress.get().getPincode());
                existingPermanentAddress.get().setState(isValidValue(addressDetails.getPermanentAddressDTO().getState()) ? addressDetails.getPermanentAddressDTO().getState() : existingPermanentAddress.get().getState());
                existingPermanentAddress.get().setCity(isValidValue(addressDetails.getPermanentAddressDTO().getCity()) ? addressDetails.getPermanentAddressDTO().getCity() : existingPermanentAddress.get().getCity());
                existingPermanentAddress.get().setStayFrom(addressDetails.getPermanentAddressDTO().getStayFrom() != null ? addressDetails.getPermanentAddressDTO().getStayFrom() : existingPermanentAddress.get().getStayFrom());
                existingPermanentAddress.get().setStayTo(addressDetails.getPermanentAddressDTO().getStayTo() != null ? addressDetails.getPermanentAddressDTO().getStayTo() : existingPermanentAddress.get().getStayTo());
                existingPermanentAddress.get().setEmergencyContactNumber(isValidValue(addressDetails.getPermanentAddressDTO().getEmergencyContactNumber()) ? addressDetails.getPermanentAddressDTO().getEmergencyContactNumber() : existingPermanentAddress.get().getEmergencyContactNumber());
                existingPermanentAddress.get().setEmergencyContactNameAndRelationship(isValidValue(addressDetails.getPermanentAddressDTO().getEmergencyContactNameAndRelationship()) ? addressDetails.getPermanentAddressDTO().getEmergencyContactNameAndRelationship() : existingPermanentAddress.get().getEmergencyContactNameAndRelationship());

                permanent = permanentAddressRepository.save(existingPermanentAddress.get());

            }
        }
        CurrentAddress current = null;
        if(existingCurrentAddress.isPresent()) {
            if (!ObjectUtils.isEmpty(addressDetails.getCurrentAddressDTO())) {
                existingCurrentAddress.get().setSameAsPermanentAddress(addressDetails.getCurrentAddressDTO().getSameAsPermanentAddress() != null ? addressDetails.getCurrentAddressDTO().getSameAsPermanentAddress() : existingCurrentAddress.get().getSameAsPermanentAddress());
                existingCurrentAddress.get().setHouseNumber(isValidValue(addressDetails.getCurrentAddressDTO().getHouseNumber()) ? addressDetails.getCurrentAddressDTO().getHouseNumber() : existingCurrentAddress.get().getHouseNumber());
                existingCurrentAddress.get().setStreetName(isValidValue(addressDetails.getCurrentAddressDTO().getStreetName()) ? addressDetails.getCurrentAddressDTO().getStreetName() : existingCurrentAddress.get().getStreetName());
                existingCurrentAddress.get().setTown(isValidValue(addressDetails.getCurrentAddressDTO().getTown()) ? addressDetails.getCurrentAddressDTO().getTown() : existingCurrentAddress.get().getTown());
                existingCurrentAddress.get().setPincode(isValidValue(addressDetails.getCurrentAddressDTO().getPincode()) ? addressDetails.getCurrentAddressDTO().getPincode() : existingCurrentAddress.get().getPincode());
                existingCurrentAddress.get().setState(isValidValue(addressDetails.getCurrentAddressDTO().getState()) ? addressDetails.getCurrentAddressDTO().getState() : existingCurrentAddress.get().getState());
                existingCurrentAddress.get().setCity(isValidValue(addressDetails.getCurrentAddressDTO().getCity()) ? addressDetails.getCurrentAddressDTO().getCity() : existingCurrentAddress.get().getCity());
                existingCurrentAddress.get().setStayFrom(addressDetails.getCurrentAddressDTO().getStayFrom() != null ? addressDetails.getCurrentAddressDTO().getStayFrom() : existingCurrentAddress.get().getStayFrom());
                existingCurrentAddress.get().setStayTo(addressDetails.getCurrentAddressDTO().getStayTo() != null ? addressDetails.getCurrentAddressDTO().getStayTo() : existingCurrentAddress.get().getStayTo());
                existingCurrentAddress.get().setEmergencyContactNumber(isValidValue(addressDetails.getCurrentAddressDTO().getEmergencyContactNumber()) ? addressDetails.getCurrentAddressDTO().getEmergencyContactNumber() : existingCurrentAddress.get().getEmergencyContactNumber());
                existingCurrentAddress.get().setEmergencyContactNameAndRelationship(isValidValue(addressDetails.getCurrentAddressDTO().getEmergencyContactNameAndRelationship()) ? addressDetails.getCurrentAddressDTO().getEmergencyContactNameAndRelationship() : existingCurrentAddress.get().getEmergencyContactNameAndRelationship());

                current = currentAddressRepository.save(existingCurrentAddress.get());
            }
        }
        List<AddressDetails> addressDetailsList = new ArrayList<>();
        if(!ObjectUtils.isEmpty(addressDetails.getAddressDetailsDTOList())){
            for (AddressDetailsDTO address : addressDetails.getAddressDetailsDTOList()) {
                Optional<AddressDetails> existingAddressDetails = addressDetailsRepo.findByAddressId(address.getAddressId());
                if (existingAddressDetails.isPresent()) {
                    existingAddressDetails.get().setStayFrom(address.getStayFrom() != null ? address.getStayFrom() : existingAddressDetails.get().getStayFrom());
                    existingAddressDetails.get().setStayTo(address.getStayTo() != null ? address.getStayTo() : existingAddressDetails.get().getStayTo());
                    existingAddressDetails.get().setAddressLine(isValidValue(address.getAddressLine()) ? address.getAddressLine() : existingAddressDetails.get().getAddressLine());
                    existingAddressDetails.get().setPincode(isValidValue(address.getPincode()) ? address.getPincode() : existingAddressDetails.get().getPincode());
                    existingAddressDetails.get().setCountry(isValidValue(address.getCountry()) ? address.getCountry() : existingAddressDetails.get().getCountry());
                    existingAddressDetails.get().setContactNumberWithRelationship(isValidValue(address.getContactNumberWithRelationship()) ? address.getContactNumberWithRelationship() : existingAddressDetails.get().getContactNumberWithRelationship());

                    // Save the updated address details
                    addressDetailsList.add(addressDetailsRepo.save(existingAddressDetails.get()));
                }
            }
            }



        AddressResponseDTO addAddressDetails = new AddressResponseDTO();
        addAddressDetails.setCurrentAddress(current);
        addAddressDetails.setPermanentAddress(permanent);
        addAddressDetails.setAddressDetailsList(addressDetailsList);
        return addAddressDetails;
    }
}

