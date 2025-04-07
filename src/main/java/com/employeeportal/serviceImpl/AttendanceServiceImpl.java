package com.employeeportal.serviceImpl;

import com.employeeportal.exception.BadRequestException;
import com.employeeportal.model.Attendance;
import com.employeeportal.repository.AttendanceRepository;
import com.employeeportal.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public Attendance punchIn(Attendance attendance) {
        List<Attendance> attendanceList = attendanceRepository.findByUserId(attendance.getUserId());
        LocalDate today = LocalDate.now();
        int count = 0;
        for (Attendance attendances : attendanceList) {
            if (attendances.getPunchInTime() != null && attendances.getPunchInTime().toLocalDate().isEqual(today)) {
                count++;
            }
        }
        if (count == 0) {
            Attendance obj = new Attendance();
            obj.setUserId(attendance.getUserId());
            obj.setPunchInTime(LocalDateTime.now());
            obj.setWfh(attendance.getWfh());
            obj.setUserName(attendance.getUserName());
            return attendanceRepository.save(obj);
        } else {
            throw new BadRequestException(" Today This User Already PunchIn");
        }
    }

    @Override
    public Attendance punchOut(Attendance attendance) {
        Optional<Attendance> obj = attendanceRepository.findByUserIdAndPunchOutTime(attendance.getUserId(), null);
        if (obj.isPresent()) {
            Attendance currentAttendance = obj.get();
            currentAttendance.setPunchOutTime(LocalDateTime.now());
            return attendanceRepository.save(currentAttendance);
        } else {
            throw new BadRequestException("User already punched out");
        }
    }

    //    @Override
//    public Attendance getAttendanceByUserId(Long userId) {
//        List<Attendance> attendanceList = attendanceRepository.findByUserId(userId);
//        if (attendanceList.isEmpty()) {
//            throw new BadRequestException("Attendance not found for the given userId.");
//        } else {
//            return attendanceList.get(0);
//        }
//    }
    @Override
    public List<Attendance> getAttendanceByUserId(Long userId) {
        List<Attendance> attendanceList = attendanceRepository.findByUserId(userId);
        if (attendanceList.isEmpty()) {
            throw new BadRequestException("No attendance records found for the given userId.");
        } else {
            return attendanceList;
        }
    }


    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }


    //@Override
//public Attendance updateAttendance(Long Id, Attendance updatedAttendance) {
//    Optional<Attendance> existingAttendanceOpt = attendanceRepository.findById(Id);
//
//    // Check if the attendance exists
//    if (!existingAttendanceOpt.isPresent()) {
//        throw new BadRequestException("Attendance record not found for the given ID.");
//    }
//
//    Attendance existingAttendance = existingAttendanceOpt.get();
//
//    // If the punchInTime is not null, update only the time part (preserving the date)
//    if (updatedAttendance.getPunchInTime() != null) {
//        // Preserve the original date and update only the time part
//        existingAttendance.setPunchInTime(existingAttendance.getPunchInTime().toLocalDate().atTime(updatedAttendance.getPunchInTime().toLocalTime()));
//    }
//
//    // If the punchOutTime is not null, update only the time part (preserving the date)
//    if (updatedAttendance.getPunchOutTime() != null) {
//        // Preserve the original date and update only the time part
//        existingAttendance.setPunchOutTime(existingAttendance.getPunchOutTime().toLocalDate().atTime(updatedAttendance.getPunchOutTime().toLocalTime()));
//    }
//
//    return attendanceRepository.save(existingAttendance);
//}
//@Override
//public Attendance updateAttendance(Long Id, Attendance updatedAttendance) {
//    Optional<Attendance> existingAttendanceOpt = attendanceRepository.findById(Id);
//
//    // Check if the attendance exists
//    if (!existingAttendanceOpt.isPresent()) {
//        throw new BadRequestException("Attendance record not found for the given ID.");
//    }
//
//    Attendance existingAttendance = existingAttendanceOpt.get();
//
//    // Check if punchInTime is missing in the updatedAttendance
//    if (updatedAttendance.getPunchInTime() == null) {
//        throw new BadRequestException("Punch-In Time is required");
//    }
//
//    // Check if punchOutTime is missing in the updatedAttendance
//    if (updatedAttendance.getPunchOutTime() == null) {
//        throw new BadRequestException("Punch-Out Time is required");
//    }
//
//    // If the punchInTime is provided, update only the time part (preserving the date)
//    if (updatedAttendance.getPunchInTime() != null) {
//        existingAttendance.setPunchInTime(
//                existingAttendance.getPunchInTime().toLocalDate().atTime(updatedAttendance.getPunchInTime().toLocalTime()));
//    }
//
//    // If the punchOutTime is provided, update only the time part (preserving the date)
//    if (updatedAttendance.getPunchOutTime() != null) {
//        existingAttendance.setPunchOutTime(
//                existingAttendance.getPunchOutTime().toLocalDate().atTime(updatedAttendance.getPunchOutTime().toLocalTime()));
//    }
//
//    // Save and return the updated attendance record
//    return attendanceRepository.save(existingAttendance);
//}
    @Override
    public Attendance updateAttendance(Long Id, Attendance updatedAttendance) {
        Optional<Attendance> existingAttendanceOpt = attendanceRepository.findById(Id);

        if (!existingAttendanceOpt.isPresent()) {
            throw new BadRequestException("Attendance record not found for the given ID.");
        }

        Attendance existingAttendance = existingAttendanceOpt.get();

        if (updatedAttendance.getPunchInTime() == null && updatedAttendance.getPunchOutTime() == null) {
            throw new BadRequestException("Punch-in and Punch-out times are required");
        }

        if (updatedAttendance.getPunchInTime() == null) {
            throw new BadRequestException("Punch-In Time is required");
        }

        if (updatedAttendance.getPunchOutTime() == null) {
            throw new BadRequestException("Punch-Out Time is required");
        }

        if (updatedAttendance.getPunchInTime().isEqual(updatedAttendance.getPunchOutTime())) {
            throw new BadRequestException("Punch-in and punch-out time cannot be the same");
        }

        if (updatedAttendance.getPunchInTime().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("Punch-In Time cannot be in the future");
        }


        if (updatedAttendance.getPunchInTime().isAfter(LocalDateTime.now()) &&
                updatedAttendance.getPunchOutTime().isBefore(updatedAttendance.getPunchInTime())) {
            throw new BadRequestException("User cannot do Future Punch-In and Past Punch-Out");
        }

        if (updatedAttendance.getPunchOutTime().isBefore(updatedAttendance.getPunchInTime())) {
            throw new BadRequestException("Punch-Out Time cannot be before Punch-In Time");
        }

        if (updatedAttendance.getPunchInTime() != null) {
            LocalDateTime currentPunchInTime = existingAttendance.getPunchInTime();
            existingAttendance.setPunchInTime(
                    currentPunchInTime.toLocalDate().atTime(updatedAttendance.getPunchInTime().toLocalTime()));
        }

        if (updatedAttendance.getPunchOutTime() != null) {
            LocalDateTime currentPunchOutTime = existingAttendance.getPunchOutTime();
            existingAttendance.setPunchOutTime(
                    currentPunchOutTime.toLocalDate().atTime(updatedAttendance.getPunchOutTime().toLocalTime()));
        }

        return attendanceRepository.save(existingAttendance);
    }

}


