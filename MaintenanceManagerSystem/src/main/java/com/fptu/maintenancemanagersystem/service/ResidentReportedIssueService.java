package com.fptu.maintenancemanagersystem.service;

import com.fptu.maintenancemanagersystem.dao.ResidentReportedIssue.ResidentReportedIssueRepository;
import com.fptu.maintenancemanagersystem.model.ResidentReportedIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidentReportedIssueService {
    @Autowired
    private ResidentReportedIssueRepository residentReportedIssueRepository;

    public void createNewReport(ResidentReportedIssue residentReportedIssue, List<Integer> equipmentIds) {
        residentReportedIssueRepository.insertResidentReportedIssue(residentReportedIssue, equipmentIds);
    }

}
