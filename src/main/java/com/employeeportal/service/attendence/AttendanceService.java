package com.employeeportal.service.attendence;

import com.employeeportal.dto.attendence.Attendance;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceService {
    Attendance punchIn(Attendance attendance);

    Attendance punchOut(Attendance attendance);
   // Attendance getAttendanceByUserId(Long userId);
   List<Attendance> getAttendanceByUserId(Long userId);
    List<Attendance> getAllAttendance();
    Attendance updateAttendance(Long Id, Attendance updatedAttendance);

}