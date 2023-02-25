/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptu.maintenancemanagersystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author lmphi
 */
@Data
@NoArgsConstructor
public class WorkOrder implements Serializable {
    private int workOrderId;
    private boolean isAccepted;
    private String declineReason;
    private LocalDate createdDate;
    private int faultedDeviceId;
    private boolean isResolved;
}
