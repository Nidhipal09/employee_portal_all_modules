package com.employeeportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data

@Entity
@Table(name = "employee_relatives")
public class EmployeeRelative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeRelativeId;
    private Boolean hasRelative;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "primary_id", nullable = false)
    private PrimaryDetails primaryDetails;
    @OneToMany(mappedBy = "employeeRelative", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<RelativeInfo> relativeInfoList;


}
