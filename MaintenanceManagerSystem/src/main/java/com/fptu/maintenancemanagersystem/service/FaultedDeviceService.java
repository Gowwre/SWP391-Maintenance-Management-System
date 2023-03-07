/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptu.maintenancemanagersystem.service;

import com.fptu.maintenancemanagersystem.dao.FaultDevice.FaultedDeviceRepository;
import com.fptu.maintenancemanagersystem.model.FaultedDevice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lmphi
 */
@Service
public class FaultedDeviceService {

    @Autowired
    FaultedDeviceRepository faultDeviceRepository;

    public List<FaultedDevice> getAllFaultedDevices() throws Exception {
        return faultDeviceRepository.getAll();
    }

    public void assignStaff(int faultDeviceId, int assignedStaffId) {
        faultDeviceRepository.updateAssignedStaff(faultDeviceId, assignedStaffId);
    }
}
