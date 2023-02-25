/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptu.maintenancemanagersystem.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author lmphi
 */

@Getter @Setter @NoArgsConstructor
public class MaintainanceReport {
    public int id;
    public int workOrderId;
    public boolean fixOrReplace;
    public LocalDate dateSubmitted;
    public String description;
    public boolean isApproved;
    public LocalDate dateUpdated;
}
