package com.fptu.maintenancemanagersystem.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ResidentReportedIssue implements Serializable {
    private int issueId;
    private int roomNumber;
    private String residentName;
    private String residentEmail;
    private String residentPhoneNumber;
    private String description;
    private LocalDate dateReported;

}
