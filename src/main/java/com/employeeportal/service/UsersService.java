package com.employeeportal.service;

import com.employeeportal.model.Users;
import com.employeeportal.model.dto.SearchDTO;
import com.employeeportal.model.dto.SearchResponseDTO;

import java.util.List;

public interface UsersService {
    Users saveUsers (Users  users);
    List<Users> getAllUsers();
    Users getUsersById (Long  usersId);
    Users updateUsersById (Long  usersId,Users  users);
    Users deleteUsersById(Long usersId);
    SearchResponseDTO searchUsers(SearchDTO searchDTO, int page, int size);
}
