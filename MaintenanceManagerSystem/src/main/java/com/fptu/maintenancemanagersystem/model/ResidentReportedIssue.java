package com.fptu.maintenancemanagersystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ResidentReportedIssue implements Serializable {
    private int issueId;
    private int roomId;
    private String residentName;
    private String residentEmail;
    private String residentPhoneNumber;
    private String description;
    private LocalDate dateReported;

    private boolean residentCompletionConfirmation;
}
