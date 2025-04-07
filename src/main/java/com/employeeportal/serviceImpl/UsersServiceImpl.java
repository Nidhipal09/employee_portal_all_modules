package com.employeeportal.serviceImpl;

import com.employeeportal.exception.BadRequestException;
import com.employeeportal.model.Users;
import com.employeeportal.model.dto.SearchDTO;
import com.employeeportal.model.dto.SearchResponseDTO;
import com.employeeportal.repository.UsersRepository;
import com.employeeportal.service.EmailService;
import com.employeeportal.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepo;
    @Autowired
    private EmailService emailService;

    @Override
    public Users saveUsers(Users users) {
        if(usersRepo.existsByEmail(users.getEmail())){
            throw new BadRequestException("user already register");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = users.getPassword();
        String encryptedPassword = passwordEncoder.encode(users.getPassword());
        users.setPassword(encryptedPassword);
        Users addUsers = usersRepo.save(users);
        emailService.sendRegistrationEmail(addUsers.getEmail(),password,"Users Registration Successfully","registration.html");
        return addUsers;
    }

    @Override
    public List<Users> getAllUsers() {
        List<Users> users = usersRepo.findAll();
        return users;
    }

    @Override
    public Users getUsersById(Long usersId) {
        Users users = usersRepo.findById(usersId).orElse(null);
        return users;
    }

    @Override
    public Users updateUsersById(Long usersId, Users users) {
        if(usersRepo.existsByEmail(users.getEmail())){
            throw new BadRequestException("user already register");
        }
        Users existingUsers = usersRepo.findById(usersId).orElseThrow(() -> new RuntimeException("User not found"));
        existingUsers.setEmployeeId(users.getEmployeeId() != null ? users.getEmployeeId() : existingUsers.getEmployeeId());
        existingUsers.setName(users.getName() != null ? users.getName() : existingUsers.getName());
        existingUsers.setRoleName(users.getRoleName() != null ? users.getRoleName() : existingUsers.getRoleName());
        existingUsers.setJoiningDate(users.getJoiningDate() != null ? users.getJoiningDate() : existingUsers.getJoiningDate());
        existingUsers.setDesignation(users.getDesignation() != null ? users.getDesignation() : existingUsers.getDesignation());
        existingUsers.setProjects(users.getProjects() != null ? users.getProjects() : existingUsers.getProjects());
        existingUsers.setProjectManager(users.getProjectManager() != null ? users.getProjectManager() : existingUsers.getProjectManager());
        existingUsers.setEmail(users.getEmail() != null ? users.getEmail() : existingUsers.getEmail());
        existingUsers.setMobileNumber(users.getMobileNumber() != null ? users.getMobileNumber() : existingUsers.getMobileNumber());
        if (users.getPassword() != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            existingUsers.setPassword(passwordEncoder.encode(users.getPassword()));
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = users.getPassword();
        String encryptedPassword = passwordEncoder.encode(users.getPassword());
        users.setPassword(encryptedPassword);
        Users addUsers = usersRepo.save(existingUsers);
        emailService.sendRegistrationEmail(addUsers.getEmail(),password,"Users Registration Successfully","registration.html");
        return existingUsers;
    }

    @Override
    public Users deleteUsersById(Long usersId) {
        Users usersDelete = usersRepo.findById(usersId).orElse(null);
        if (usersDelete != null) {
            usersRepo.delete(usersDelete);
            return usersDelete;
        } else {
            throw new RuntimeException("Users not found with id: " + usersId);
        }
    }

    @Override
    public SearchResponseDTO searchUsers(SearchDTO searchDTO, int page, int size) {
        int pageSize = 0;
        if (page >= 1) {
            pageSize = page - 1;
        }
        PageRequest pageRequest = PageRequest.of(pageSize, size, Sort.Direction.ASC, "name");
        List<Users> users = new ArrayList<>();
        SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
        if (!searchDTO.getName().isEmpty()) {
            Page<Users> nameList = usersRepo.findAllByName(searchDTO.getName(), pageRequest);
            searchResponseDTO.setList(nameList.getContent());
            searchResponseDTO.setPageNumber(page);
            searchResponseDTO.setPageSize(nameList.getNumberOfElements());
            searchResponseDTO.setTotalPageSize(nameList.getTotalPages());
        } else if (searchDTO.getEmployeeId() != 0) {
            Page<Users> nameList = usersRepo.findAllByEmployeeId(searchDTO.getEmployeeId(), pageRequest);
            searchResponseDTO.setList(nameList.getContent());
            searchResponseDTO.setPageNumber(page);
            searchResponseDTO.setPageSize(nameList.getNumberOfElements());
            searchResponseDTO.setTotalPageSize(nameList.getTotalPages());
        } else if (!searchDTO.getProjects().isEmpty()) {
            Page<Users> nameList = usersRepo.findAllByProjects(searchDTO.getProjects(), pageRequest);
            searchResponseDTO.setList(nameList.getContent());
            searchResponseDTO.setPageNumber(page);
            searchResponseDTO.setPageSize(nameList.getNumberOfElements());
            searchResponseDTO.setTotalPageSize(nameList.getTotalPages());
        }
        return searchResponseDTO;
    }
}

