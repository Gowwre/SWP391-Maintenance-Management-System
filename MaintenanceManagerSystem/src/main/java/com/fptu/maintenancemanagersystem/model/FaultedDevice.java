package com.fptu.maintenancemanagersystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class FaultedDevice implements Serializable {
    private int faultedDeviceId;
    private int equipmentId;
    private int issueId;
    private int assignStaffId;

}
