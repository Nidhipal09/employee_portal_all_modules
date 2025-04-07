package com.employeeportal.repository;

import com.employeeportal.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByUserIdAndPunchOutTime(Long userId, LocalDateTime o);
    List<Attendance> findByUserId(Long userId);
    List<Attendance> findAll();
    Optional<Attendance> findById(Long userId);

}