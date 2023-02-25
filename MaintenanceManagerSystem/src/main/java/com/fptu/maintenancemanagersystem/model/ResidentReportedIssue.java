package com.fptu.maintenancemanagersystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor @Getter @Setter
public class ResidentReportedIssue implements Serializable {
    private int issueId;
    private int roomNumber;
    private String residentName;
    private String residentEmail;
    private String residentPhoneNumber;
    private String description;
    private LocalDate dateReported;

}
