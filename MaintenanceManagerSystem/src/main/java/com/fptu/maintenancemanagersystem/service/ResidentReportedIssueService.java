package com.fptu.maintenancemanagersystem.service;

import com.fptu.maintenancemanagersystem.dao.ResidentReportedIssue.ResidentReportedIssueRepository;
import com.fptu.maintenancemanagersystem.model.ResidentIssueReportedAndWorkProgressByPhoneNum;
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

    public List<ResidentIssueReportedAndWorkProgressByPhoneNum> getAllReportedIssueByFaultedDeviceRecordsByPhoneNum (String phoneNumber) throws Exception {
        return residentReportedIssueRepository.getResidentIssueReportedAndWorkProgressByPhoneNum(phoneNumber);
    }

    public ResidentIssueReportedAndWorkProgressByPhoneNum getResidentIssueReportedAndWorkProgressByIssueId (int issueId) throws Exception {
        return residentReportedIssueRepository.getResidentIssueReportedAndWorkProgressByIssueId(issueId);
    }
}
