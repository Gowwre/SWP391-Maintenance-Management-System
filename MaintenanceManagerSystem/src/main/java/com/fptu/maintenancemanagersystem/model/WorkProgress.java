package com.fptu.maintenancemanagersystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WorkProgress {
    private int workProgressId;
    private LocalDate deadlineDate;
    private LocalDate createdDate;
    private String workStatus;
    private LocalDate completedDate;

}
