package com.employeeportal.serviceImpl;

import com.employeeportal.config.ApplicationConstant;
import com.employeeportal.exception.EmployeeNotFoundException;
import com.employeeportal.exception.NotFoundException;
import com.employeeportal.model.LoginResponse;
import com.employeeportal.model.Users;
import com.employeeportal.repository.PrimaryDetailsRepository;
import com.employeeportal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
@Service
public class EmployeeDetailServiceImpl  implements UserDetailsService {

    private final PrimaryDetailsRepository primaryDetailsRepository;
    private final UsersRepository usersRepository;
    @Autowired
    public EmployeeDetailServiceImpl(PrimaryDetailsRepository primaryDetailsRepository, UsersRepository usersRepository) {
        this.primaryDetailsRepository = primaryDetailsRepository;
        this.usersRepository = usersRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String principal) throws UsernameNotFoundException {
        com.employeeportal.model.PrimaryDetails primaryDetails = primaryDetailsRepository.findByEmail(principal.trim());
        Users users = usersRepository.findByEmail(principal.trim());
        if (primaryDetails != null) {
            return new User(primaryDetails.getEmail(), primaryDetails.getPassword(), new ArrayList<>());
        } else if (users !=null) {
            return new User(users.getEmail(), users.getPassword(), new ArrayList<>());

        } else {
            throw new EmployeeNotFoundException();

        }
    }

    public LoginResponse findEmployeeDetails(String employeeName) {
        com.employeeportal.model.PrimaryDetails employee= primaryDetailsRepository.findByEmail(employeeName);
        if (!ObjectUtils.isEmpty(employee)) {
            LoginResponse loginResponse=new LoginResponse();
            loginResponse.setId(employee.getPrimaryId());
            loginResponse.setEmail(employee.getEmail());
            loginResponse.setFullName(employee.getFullName());
            loginResponse.setRoleName(employee.getRoleName());

            return loginResponse;
        } else {
            throw new NotFoundException(ApplicationConstant.GET_ERROR_RESPONSE_MSG);
        }
    }
    }

