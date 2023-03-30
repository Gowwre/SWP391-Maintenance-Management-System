/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptu.maintenancemanagersystem.model.entities;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author lmphi
 */

@Data
@NoArgsConstructor
public class FaultedDevice implements Serializable{
   int faultDeviceId;
   int equipmentId;
   int issueId;
   int assignedStaffId;
   int workProgressId;
}
