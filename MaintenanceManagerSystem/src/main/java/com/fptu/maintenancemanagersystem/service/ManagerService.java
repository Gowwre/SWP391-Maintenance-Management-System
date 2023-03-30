package com.fptu.maintenancemanagersystem.service;

import com.fptu.maintenancemanagersystem.dao.Manager.ManagerRepository;
import com.fptu.maintenancemanagersystem.model.entities.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {
    @Autowired
    ManagerRepository managerRepository;
    public Manager findUserByLogin(String email, String password) {
        return managerRepository.findUserByLogin(email, password);
    }

    public void changePassword(String email, String currentPassword, String newPassword) {
        managerRepository.setNewPassword(email, currentPassword, newPassword);
    }
}
