package com.fptu.maintenancemanagersystem.service;

import com.fptu.maintenancemanagersystem.dao.ResidentReportedIssue.ResidentReportedIssueRepository;
import com.fptu.maintenancemanagersystem.model.ResidentIssueReportedAndWorkProgress;
import com.fptu.maintenancemanagersystem.model.WorkProgressAndIssueByResidentReportedIssue;
import com.fptu.maintenancemanagersystem.model.ResidentReportedIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidentReportedIssueService {
    @Autowired
    private ResidentReportedIssueRepository residentReportedIssueRepository;

    public List<ResidentReportedIssue> getAllResidentReportedIssue() throws Exception{
        return residentReportedIssueRepository.getAll();
    }
    
    public void createNewReport(ResidentReportedIssue residentReportedIssue, List<Integer> equipmentIds) {
        residentReportedIssueRepository.insertReportForm(residentReportedIssue, equipmentIds);
    }

    public List<WorkProgressAndIssueByResidentReportedIssue> getAllReportedIssueByFaultedDeviceRecords (int issueId) throws Exception {
        return residentReportedIssueRepository.getReportedIssueByFaultedDevice(issueId);
    }

    public ResidentReportedIssue getResidentReportedIssueById(int issueId) throws Exception {
        return residentReportedIssueRepository.getResidentReportedIssueById(issueId);
    }

    public List<ResidentIssueReportedAndWorkProgress> getAllReportedIssueByFaultedDeviceRecordsByEmail(String email) throws Exception {
        return residentReportedIssueRepository.getResidentIssueReportedAndWorkProgressByEmail(email);
    }

    public ResidentIssueReportedAndWorkProgress getResidentIssueReportedAndWorkProgressByIssueId (int issueId) throws Exception {
        return residentReportedIssueRepository.getResidentIssueReportedAndWorkProgressByIssueId(issueId);
    }

    public void confirmWorkCompletion(int issueId, String residentPhoneNumber) {
        residentReportedIssueRepository.confirmWorkCompletion(issueId, residentPhoneNumber);
    }
}
