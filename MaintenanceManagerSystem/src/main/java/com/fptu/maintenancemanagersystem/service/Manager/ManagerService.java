package com.fptu.maintenancemanagersystem.service.Manager;

import com.fptu.maintenancemanagersystem.dao.Manager.ManagerRepository;
import com.fptu.maintenancemanagersystem.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {
    @Autowired
    ManagerRepository managerRepository;
    public Manager findUserByLogin(String email, String password) {
        return managerRepository.findUserByLogin(email, password);
    }
}
