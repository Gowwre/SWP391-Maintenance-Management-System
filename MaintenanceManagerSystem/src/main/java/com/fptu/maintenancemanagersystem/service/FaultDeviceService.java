/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptu.maintenancemanagersystem.service;

import com.fptu.maintenancemanagersystem.dao.FaultDevice.FaultDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lmphi
 */
@Service
public class FaultDeviceService {
@Autowired
FaultDeviceRepository faultDeviceRepository;

public void assignStaff(int faultDeviceId, int assignedStaffId) {
    faultDeviceRepository.updateAssignedStaff(faultDeviceId, assignedStaffId);
}
}
