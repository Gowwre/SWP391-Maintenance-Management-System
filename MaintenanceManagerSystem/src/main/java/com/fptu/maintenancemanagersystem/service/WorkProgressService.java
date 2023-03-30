package com.fptu.maintenancemanagersystem.service;

import com.fptu.maintenancemanagersystem.dao.WorkProgress.WorkProgressRepository;
import com.fptu.maintenancemanagersystem.model.entities.WorkProgress;
import com.fptu.maintenancemanagersystem.model.dto.WorkProgressAndIssueByResidentReportedIssue;
import com.fptu.maintenancemanagersystem.model.dto.WorkProgressAndStaffNameRecord;
import com.fptu.maintenancemanagersystem.model.dto.WorkStatusAndDeadlineDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<WorkProgressAndStaffNameRecord> findAllWorkProgressAndStaffName() {
        return workProgressRepository.findWorkProgressAndStaffName();
    }

    public void setDeadlineByIssueId(int issueId, LocalDate deadline) {
        workProgressRepository.setDeadlineByIssueId(issueId, deadline);
    }

    public WorkProgressAndStaffNameRecord findWorkProgressAndStaffNameByIssueId(int issueID) {
        return workProgressRepository.findWorkProgressAndStaffNameByIssueId(issueID);
    }

    public WorkStatusAndDeadlineDate getWorkStatusAndDeadlineForIssue(int issueID) {
        return workProgressRepository.getWorkStatusAndDeadlineForIssue(issueID);
    }

    public List<WorkProgressAndIssueByResidentReportedIssue> getWorkProgressAndStaffNameBySignedInStaff(int signedInStaffId) {
        return workProgressRepository.getAssignedWorkAndIssueByStaff(signedInStaffId);
    }

    public void markOverdueWork() {
        workProgressRepository.markOverdueWork();
    }
}
