package com.fptu.maintenancemanagersystem.model;

import java.time.LocalDate;

public record WorkStatusAndDeadlineDate(String workStatus, LocalDate deadlineDate) {
}
