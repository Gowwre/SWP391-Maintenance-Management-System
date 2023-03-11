package com.fptu.maintenancemanagersystem.controller;

import com.fptu.maintenancemanagersystem.model.FaultedDevice;
import com.fptu.maintenancemanagersystem.model.ReportedIssueByFaultedDeviceRecord;
import com.fptu.maintenancemanagersystem.model.ResidentReportedIssue;
import com.fptu.maintenancemanagersystem.model.Room;
import com.fptu.maintenancemanagersystem.service.EquipmentService;
import com.fptu.maintenancemanagersystem.service.FaultedDeviceService;
import com.fptu.maintenancemanagersystem.service.ResidentReportedIssueService;
import com.fptu.maintenancemanagersystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class WorkAssignController {
    @Autowired
    ResidentReportedIssueService residentReportedIssueService;

    @Autowired
    RoomService roomService;

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    FaultedDeviceService faultedDeviceService;

    @GetMapping("/workAssignList")
    public String viewResidentReportedIssue(Model model) {
        try {
            List<FaultedDevice> faultedDevices = faultedDeviceService.getAllFaultedDevices();
            model.addAttribute("faultDevices", faultedDevices);
            List<Room> rooms = roomService.getAllRooms();
            model.addAttribute("rooms", rooms);
            List<ResidentReportedIssue> residentReportedIssues = residentReportedIssueService.getAllResidentReportedIssue();
            model.addAttribute("residentReportedIssueList", residentReportedIssues);
            return "staffPages/workAssignList";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/viewWork/{id}")
    public String getReportedIssueByFaultedDeviceRecord (@PathVariable("id") int issueID, Model model) {
        try {
            List<ReportedIssueByFaultedDeviceRecord> reportedIssueByFaultedDeviceRecords = residentReportedIssueService.getAllReportedIssueByFaultedDeviceRecords(issueID);
            model.addAttribute("equipments", equipmentService.getEquipmentsByIssueId(issueID));


            model.addAttribute("avaiable", reportedIssueByFaultedDeviceRecords);

            return "staffPages/viewWorkAssign";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
