package com.fptu.maintenancemanagersystem.service;

import com.fptu.maintenancemanagersystem.dao.WorkProgress.WorkProgressRepository;
import com.fptu.maintenancemanagersystem.model.WorkProgress;
import com.fptu.maintenancemanagersystem.model.WorkProgressAndIssueByResidentReportedIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkProgressService {
    @Autowired
    WorkProgressRepository workProgressRepository;

    public WorkProgress findById(int id) {
        return workProgressRepository.findById(id);
    }

    public List<WorkProgressAndIssueByResidentReportedIssue> findWorkProgressAndIssueByResidentReportedIssue() {
        return workProgressRepository.findWorkProgressAndIssueByResidentReportedIssue();
    }
}
