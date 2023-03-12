package com.fptu.maintenancemanagersystem.service;

import com.fptu.maintenancemanagersystem.dao.WorkProgress.WorkProgressRepository;
import com.fptu.maintenancemanagersystem.model.WorkProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkProgressService {
    @Autowired
    WorkProgressRepository workProgressRepository;

    public WorkProgress findById(int id) {
        return workProgressRepository.findById(id);
    }
}
