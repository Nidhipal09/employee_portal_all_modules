package com.employeeportal.controller;

import com.employeeportal.exception.ResourceNotFoundException;
import com.employeeportal.model.PassportDetails;
import com.employeeportal.model.PermanentAddress;
import com.employeeportal.model.PersonalDetails;
import com.employeeportal.model.Users;
import com.employeeportal.model.dto.SearchDTO;
import com.employeeportal.model.dto.SearchResponseDTO;
import com.employeeportal.service.PersonalDetailsService;
import com.employeeportal.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    public ResponseEntity<Users> saveUsers(@RequestBody Users user) {
        Users addUsers = usersService.saveUsers(user);
        return new ResponseEntity<>(addUsers, HttpStatus.CREATED);

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Users> getUsersById(@PathVariable("userId") Long usersId) {
        Users usersById = usersService.getUsersById(usersId);

        if (usersById == null) {
            throw new ResourceNotFoundException("User not found with id " + usersId);
        }
        return new ResponseEntity<>(usersById, HttpStatus.OK);
    }

    @GetMapping("/allUsers")

    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> allUsers = usersService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PutMapping("/update/{usersId}")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    public ResponseEntity<Users> updateUsersById(@PathVariable("usersId") Long usersId, @RequestBody Users users) {
        Users updatedUsers = usersService.updateUsersById(usersId, users);
        return new ResponseEntity<>(updatedUsers, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{usersId}")
    public ResponseEntity<String> deleteUsersById(@PathVariable("usersId") Long usersId) {
        Users isDeleted = usersService.deleteUsersById(usersId);
        return new ResponseEntity<>("data deleted", HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchUsers(@RequestBody SearchDTO searchDTO, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        SearchResponseDTO usersList = usersService.searchUsers(searchDTO, page, size);
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }
}

