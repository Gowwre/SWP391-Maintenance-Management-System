package com.fptu.maintenancemanagersystem.service;

import com.fptu.maintenancemanagersystem.dao.ResidentReportedIssue.ResidentReportedIssueRepository;
import com.fptu.maintenancemanagersystem.model.ReportedIssueByFaultedDeviceRecord;
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

    public List<ReportedIssueByFaultedDeviceRecord> getAllReportedIssueByFaultedDeviceRecords (int issueId) throws Exception {
        return residentReportedIssueRepository.getReportedIssueByFaultedDevice(issueId);
    }

}
