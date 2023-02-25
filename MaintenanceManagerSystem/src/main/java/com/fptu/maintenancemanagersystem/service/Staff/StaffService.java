package com.fptu.maintenancemanagersystem.service.Staff;

import com.fptu.maintenancemanagersystem.dao.Staff.StaffRepository;
import com.fptu.maintenancemanagersystem.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService{
    @Autowired
    StaffRepository staffRepository;
    public Staff findUserByLogin(String email, String password) {
        return staffRepository.findUserByLogin(email, password);
    }


}
