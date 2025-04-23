package com.employeeportal.repository;

import com.employeeportal.model.Holiday;
import com.employeeportal.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HolidayRepository  extends JpaRepository<Holiday,Long> {
    @Query("SELECT h.holidayDate FROM Holiday h")
 //@Query(value = "select holiday_id,role_name from Holiday where email = ?1", nativeQuery = true)
    List<LocalDate> findAllHolidayDates();
}
