package com.fptu.maintenancemanagersystem.model.dto;

import java.time.LocalDate;

public record WorkStatusAndDeadlineDate(String workStatus, LocalDate deadlineDate) {
}
