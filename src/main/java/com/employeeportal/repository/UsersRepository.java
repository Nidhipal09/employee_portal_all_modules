package com.employeeportal.repository;

import com.employeeportal.model.GeneralResponses;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query(value = "select users_id,role_name from users where email = :employeeName", nativeQuery = true)
    GeneralResponses getIdByEmployeename(String employeeName);
    Page<Users> findAllByName(String name, PageRequest pageRequest);

    Page<Users> findAllByEmployeeId(Long employeeId, PageRequest pageRequest);

    Page<Users> findAllByProjects(String projects, PageRequest pageRequest);

    Users findByEmail(String email);

    boolean existsByEmail(String email);
}
