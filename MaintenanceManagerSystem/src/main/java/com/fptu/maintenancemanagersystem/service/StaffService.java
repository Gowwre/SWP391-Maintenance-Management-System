package com.fptu.maintenancemanagersystem.service;

import com.fptu.maintenancemanagersystem.dao.Staff.StaffRepository;
import com.fptu.maintenancemanagersystem.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    @Autowired
    StaffRepository staffRepository;

    public List<Staff> getAllStaff() {
        return staffRepository.getAllStaff();
    }
    public Staff findUserByLogin(String email, String password) {
        return staffRepository.findUserByLogin(email, password);
    }

    public List<Staff> getWorkingStaff() {
        return staffRepository.getWorkingStaff();
    }

    public void changePassword(String email, String currentPassword, String newPassword) {
        staffRepository.setNewPassword(email, currentPassword, newPassword);
    }
}
