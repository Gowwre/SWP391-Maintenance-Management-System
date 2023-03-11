package com.fptu.maintenancemanagersystem.controller;

import com.fptu.maintenancemanagersystem.model.*;
import com.fptu.maintenancemanagersystem.service.EquipmentService;
import com.fptu.maintenancemanagersystem.service.FaultedDeviceService;
import com.fptu.maintenancemanagersystem.service.ResidentReportedIssueService;
import com.fptu.maintenancemanagersystem.service.RoomService;
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
    EquipmentService equipmentService;

    @Autowired
    FaultedDeviceService faultedDeviceService;

    @GetMapping("/residentReportedIssues")
    public String viewResidentReportedIssue(Model model) {
        try {
            List<Room> rooms = roomService.getAllRooms();
            List<ResidentReportedIssue> residentReportedIssues = residentReportedIssueService.getAllResidentReportedIssue();

            model.addAttribute("rooms", rooms);
            model.addAttribute("residentReportedIssueList", residentReportedIssues);
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

            model.addAttribute("rooms", roomService.getAllRooms());
            model.addAttribute("equipments", equipmentsByIssueId);
            model.addAttribute("availableIssue", residentReportedIssue);
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
}
