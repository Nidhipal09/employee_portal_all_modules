package com.employeeportal.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "holiday")
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long holidayId;

    @Column(name = "holiday_date", nullable = false, unique = true)
    private LocalDate holidayDate;

    @Column(name = "description", nullable = false)
    private String description;

    public Holiday() {
    }

    public Holiday(Long holidayId, LocalDate holidayDate, String description) {
        this.holidayId = holidayId;
        this.holidayDate = holidayDate;
        this.description = description;
    }

    public Long getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Long holidayId) {
        this.holidayId = holidayId;
    }

    public LocalDate getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(LocalDate holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
