/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptu.maintenancemanagersystem.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author lmphi
 */

@Data
@NoArgsConstructor
public class MaintenanceReport implements Serializable {
    private int maintenanceReportId;
    private int workOrderId;
    private boolean fixOrReplace;
    private LocalDate dateSubmitted;
    private String description;
    private boolean isApproved;
    private LocalDate dateUpdated;
}
