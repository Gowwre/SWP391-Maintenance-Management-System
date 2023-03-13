package com.fptu.maintenancemanagersystem.controller;

import com.fptu.maintenancemanagersystem.model.*;
import com.fptu.maintenancemanagersystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ResidentReportedIssueController {
    @Autowired
    ResidentReportedIssueService residentReportedIssueService;

    @Autowired
    RoomService roomService;

    @Autowired
    StaffService staffService;

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    FaultedDeviceService faultedDeviceService;

    @Autowired
    WorkProgressService workProgressService;



    @GetMapping("/residentReportedIssues")
    public String viewResidentReportedIssue(Model model) {
        try {
            List<Room> rooms = roomService.getAllRooms();
            List<WorkProgressAndIssueByResidentReportedIssue> workProgressAndIssueByResidentReportedIssues = workProgressService.findWorkProgressAndIssueByResidentReportedIssue();
            List<Staff> staffs = staffService.getAllStaff();

            model.addAttribute("staffs", staffs);
            model.addAttribute("rooms", rooms);
            model.addAttribute("workProgressAndResidentReportedIssueList", workProgressAndIssueByResidentReportedIssues);
            return "managerPages/reportedIssueList";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/viewIssue/{id}")
    public String getReportedIssueByFaultedDeviceRecord(@PathVariable("id") int issueID, Model model) {
        try {
            ResidentReportedIssue residentReportedIssue = residentReportedIssueService.getResidentReportedIssueById(issueID);
            List<Equipment> equipmentsByIssueId = equipmentService.getEquipmentsByIssueId(issueID);
            List<Staff> workingStaffs = staffService.getWorkingStaff();
            FaultedDevice faultedDevice = new FaultedDevice();

        model.addAttribute("faultedDevice",faultedDevice);
            model.addAttribute("rooms", roomService.getAllRooms());
            model.addAttribute("equipments", equipmentsByIssueId);
            model.addAttribute("availableIssue", residentReportedIssue);
            model.addAttribute("workingStaffs", workingStaffs);
            return "managerPages/viewIssue";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/createReport")
    public String showCreateNewReportForm(Model model) {
        model.addAttribute("residentReportedIssue", new ResidentReportedIssue());
        try {
            List<Room> rooms = roomService.getAllRooms();
            model.addAttribute("rooms", rooms);
            return "reportForm/reportFormStep1";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/createReport/step2")
    public String showCreateNewReportFormStep2(@ModelAttribute("residentReportedIssue") ResidentReportedIssue residentReportedIssue, Model model) {
        int roomId = residentReportedIssue.getRoomId();
        List<Equipment> equipments = equipmentService.getAllEquipmentsByRoom(roomId);
        model.addAttribute("equipments", equipments);
        return "reportForm/reportFormStep2";
    }

    @PostMapping("/createReport")
    public String createNewReport(@ModelAttribute("residentReportedIssue") ResidentReportedIssue residentReportedIssue, @RequestParam("equipmentIds") List<Integer> equipmentIds) {
        residentReportedIssueService.createNewReport(residentReportedIssue, equipmentIds);
        return "redirect:/";
    }

    @GetMapping("/updateAssignedStaff")
    public String updateAssignStaff(@RequestParam("issueId") int issueId, @RequestParam("staffId") int assignStaffId,Model model) {
        try {
            faultedDeviceService.updateAssignStaffByIssueId(assignStaffId,issueId);
            return viewResidentReportedIssue(model);
        } catch (Exception e) {
            return "error";
        }
    }

}
