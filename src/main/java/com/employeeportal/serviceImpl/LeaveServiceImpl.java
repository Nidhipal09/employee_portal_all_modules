package com.employeeportal.serviceImpl;
import com.employeeportal.config.EmailConstant;
import com.employeeportal.model.Leave;
import com.employeeportal.model.PrimaryDetails;
import com.employeeportal.model.StatusType;
import com.employeeportal.model.dto.LeaveDTO;
import com.employeeportal.model.dto.ReportingManagerDTO;
import com.employeeportal.model.dto.ReportingManagerResponseDTO;
import com.employeeportal.repository.HolidayRepository;
import com.employeeportal.repository.LeaveRepository;
import com.employeeportal.repository.PrimaryDetailsRepository;
import com.employeeportal.service.EmailService;
import com.employeeportal.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private HolidayRepository holidayRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PrimaryDetailsRepository primaryDetailsRepository;

    @Override
    public Leave applyLeave(LeaveDTO request) {
        validateDates(request.getFromDate(), request.getToDate());

        List<LocalDate> holidays = holidayRepository.findAllHolidayDates();
        int totalDays = calculateBusinessDays(request.getFromDate(), request.getToDate(), holidays);
        if (totalDays <= 0) {
            throw new RuntimeException("No working days selected for leave.");
        }

        // 🔐 Get logged-in employee email
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        // ✅ Fetch employee info from PrimaryDetails
        PrimaryDetails employee = primaryDetailsRepository.findByEmail(currentEmail);
        if (employee == null) {
            throw new RuntimeException("Employee not found with email: " + currentEmail);
        }

        // 💾 Save leave
        Leave leave = new Leave();
        leave.setFromDate(request.getFromDate());
        leave.setToDate(request.getToDate());
        leave.setLeaveType(request.getLeaveType());
        leave.setReason(request.getReason());
        leave.setReportingManagerId(request.getReportingManagerId());
        leave.setNotifyManager(request.isNotifyManager());
        leave.setTotalLeaveDays(totalDays);
        leave.setDayOfLeave(request.getDayOfLeave());
        leave.setStatusType(StatusType.PENDING);
        leave.setPrimaryDetails(employee);
        Leave savedLeave = leaveRepository.save(leave);


        // 📧 Send email to HR
        List<PrimaryDetails> primaryDetails = primaryDetailsRepository.findByRoleName("HR");
        if (!primaryDetails.isEmpty()) {
            for (PrimaryDetails hrEmail : primaryDetails) {
                emailService.sendLeaveNotification(
                        hrEmail.getEmail(), employee.getFullName(),
                        savedLeave.getLeaveType(),
                        savedLeave.getFromDate().toString(),
                        savedLeave.getToDate().toString(),
                        totalDays,
                        savedLeave.getReason(),
                        String.valueOf(savedLeave.getStatusType()),
                        EmailConstant.LEAVE_NOTIFICATION_SUBJECT,
                        EmailConstant.LEAVE_NOTIFICATION_TEMPLATE_NAME,
                        String.valueOf(savedLeave.getDayOfLeave())

                        // ✅ this will show employee name in email
                );
            }
        }

        // 📧 Notify manager if needed
        if (request.isNotifyManager()) {
            Optional<PrimaryDetails> managerOpt = primaryDetailsRepository.findById(request.getReportingManagerId());
            if (managerOpt.isPresent()) {
                String managerEmail = managerOpt.get().getEmail();
                emailService.sendLeaveNotification(
                        managerEmail,
                      employee.getFullName(),
                        savedLeave.getLeaveType(),
                        savedLeave.getFromDate().toString(),
                        savedLeave.getToDate().toString(),
                        totalDays,
                        savedLeave.getReason(),
                        String.valueOf(savedLeave.getStatusType()),
                        EmailConstant.LEAVE_NOTIFICATION_SUBJECT,
                        EmailConstant.LEAVE_NOTIFICATION_TEMPLATE_NAME,
                        String.valueOf(savedLeave.getDayOfLeave())

                );
            }
        }

        return savedLeave;
    }

    @Override
    public List<ReportingManagerResponseDTO> getAllReportingManagers() {
        List<PrimaryDetails> getManagers = primaryDetailsRepository.findByRoleName("MANAGER");
        List<ReportingManagerResponseDTO> getManager = new ArrayList<>();
       getManagers.forEach(manager ->{
           ReportingManagerResponseDTO obj = new ReportingManagerResponseDTO();
           obj.setReportingManagerName(manager.getFullName());
           obj.setReportingMangerId(manager.getPrimaryId());
           getManager.add(obj);

       });


        return getManager;

    }

    private int calculateBusinessDays(LocalDate start, LocalDate end, List<LocalDate> holidays) {
        int count = 0;
        LocalDate date = start;
        while (!date.isAfter(end)) {
            boolean isWeekend = (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY);
            boolean isHoliday = holidays.contains(date);
            if (!isWeekend && !isHoliday) {
                count++;
            }
            date = date.plusDays(1);
        }
        return count;
    }
    private void validateDates(LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            throw new RuntimeException("Dates cannot be null");
        }
        if (from.isAfter(to)) {
            throw new RuntimeException("From date must be before To date");
        }
    }



}

