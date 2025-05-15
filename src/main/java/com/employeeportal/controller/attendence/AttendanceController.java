package com.employeeportal.controller.attendence;

import com.employeeportal.config.ApplicationConstant;
import com.employeeportal.dto.attendence.Attendance;
import com.employeeportal.exception.BadRequestException;
import com.employeeportal.model.login.ResponseDTO;
import com.employeeportal.service.attendence.AttendanceService;
import com.employeeportal.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {
    @Autowired
    private final AttendanceService attendanceService;
    private final ResponseUtil responseUtil;

    public AttendanceController(AttendanceService attendanceService, ResponseUtil responseUtil) {
        this.attendanceService = attendanceService;
        this.responseUtil = responseUtil;
    }

    @PostMapping("/punchIn")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> punchIn(@RequestBody Attendance attendance) {
        try {
            Attendance punchedInAttendance = attendanceService.punchIn(attendance);
            ResponseDTO response = responseUtil.prepareResponseDto(punchedInAttendance, "User PunchIn Successfully", HttpStatus.CREATED.value(), true);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ResponseDTO response = responseUtil.prepareResponseDto(null, e.getMessage(), HttpStatus.BAD_REQUEST.value(), false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/punchOut")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> punchOut(@RequestBody Attendance attendance) {
        try {
            Attendance result = attendanceService.punchOut(attendance);
            ResponseDTO response = responseUtil.prepareResponseDto(result, "User PunchOut Successfully", HttpStatus.OK.value(), true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO response = responseUtil.prepareResponseDto(null, e.getMessage(), HttpStatus.BAD_REQUEST.value(), false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


@GetMapping("/{userId}")
@PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
public ResponseEntity<ResponseDTO> getAttendance(@PathVariable Long userId) {
    try {
        List<Attendance> attendanceList = attendanceService.getAttendanceByUserId(userId); // Fetch all attendance records
        ResponseDTO response = responseUtil.prepareResponseDto(attendanceList, "Attendance records retrieved successfully", HttpStatus.OK.value(), true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
        ResponseDTO response = responseUtil.prepareResponseDto(null, e.getMessage(), HttpStatus.BAD_REQUEST.value(), false);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}



    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> getAllAttendance() {
        try {
            List<Attendance> attendanceList = attendanceService.getAllAttendance();
            ResponseDTO response = responseUtil.prepareResponseDto(attendanceList, "All Attendance Records Retrieved Successfully", HttpStatus.OK.value(), true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO response = responseUtil.prepareResponseDto(null, e.getMessage(), HttpStatus.BAD_REQUEST.value(), false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

//    @PutMapping("/edit/{Id}")
//    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
//    public ResponseEntity<ResponseDTO> editAttendance(@PathVariable Long Id, @RequestBody Attendance updatedAttendance) {
//        try {
//            Attendance updatedRecord = attendanceService.updateAttendance(Id, updatedAttendance);
//            ResponseDTO response = responseUtil.prepareResponseDto(updatedRecord, "Attendance Updated Successfully", HttpStatus.OK.value(), true);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            ResponseDTO response = responseUtil.prepareResponseDto(null, e.getMessage(), HttpStatus.BAD_REQUEST.value(), false);
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }

   // }
   @PutMapping("/edit/{Id}")
   @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
   public ResponseEntity<ResponseDTO> editAttendance(@PathVariable Long Id, @RequestBody Attendance updatedAttendance) {
       try {
           Attendance updatedRecord = attendanceService.updateAttendance(Id, updatedAttendance);
           ResponseDTO response = responseUtil.prepareResponseDto(updatedRecord, "Attendance Updated Successfully", HttpStatus.OK.value(), true);
           return new ResponseEntity<>(response, HttpStatus.OK);
       } catch (BadRequestException e) {
           ResponseDTO response = responseUtil.prepareResponseDto(null, e.getMessage(), HttpStatus.BAD_REQUEST.value(), false);
           return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
       } catch (Exception e) {
           ResponseDTO response = responseUtil.prepareResponseDto(null, e.getMessage(), HttpStatus.BAD_REQUEST.value(), false);
           return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
       }
   }
}