package com.fptu.maintenancemanagersystem.controller;

import com.fptu.maintenancemanagersystem.model.*;
import com.fptu.maintenancemanagersystem.service.*;
import jakarta.servlet.http.HttpSession;
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

    @Autowired
    WorkProgressService workProgressService;

    @GetMapping("/workAssignList")
    public String viewResidentReportedIssue(Model model, HttpSession session) {
        try {
            List<Room> rooms = roomService.getAllRooms();

            var assignedStaffId = (Staff) session.getAttribute("staff");

            List<WorkProgressAndIssueByResidentReportedIssue> workProgressAndIssueByResidentReportedIssuesByStaffId = workProgressService.getWorkProgressAndStaffNameBySignedInStaff(assignedStaffId.getStaffId());


            model.addAttribute("assignedTasks", workProgressAndIssueByResidentReportedIssuesByStaffId);
            model.addAttribute("rooms", rooms);
            
            return "staffPages/workAssignList";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/viewWork/{id}")
    public String getReportedIssueByFaultedDeviceRecord(@PathVariable("id") int issueID, Model model) {
        try {
            ResidentReportedIssue residentReportedIssue = residentReportedIssueService.getResidentReportedIssueById(issueID);
            List<Equipment> equipmentsByIssueId = equipmentService.getEquipmentsByIssueId(issueID);

            model.addAttribute("rooms", roomService.getAllRooms());
            model.addAttribute("equipments", equipmentsByIssueId);
            model.addAttribute("availableIssue", residentReportedIssue);
            return "staffPages/viewWorkAssign";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
