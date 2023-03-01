package com.fptu.maintenancemanagersystem.controller;

import com.fptu.maintenancemanagersystem.model.Equipment;
import com.fptu.maintenancemanagersystem.model.ResidentReportedIssue;
import com.fptu.maintenancemanagersystem.model.Room;
import com.fptu.maintenancemanagersystem.service.EquipmentService;
import com.fptu.maintenancemanagersystem.service.ResidentReportedIssueService;
import com.fptu.maintenancemanagersystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ResidentReportedIssueController {
    @Autowired
    ResidentReportedIssueService residentReportedIssueService;

    @Autowired
    RoomService roomService;

    @Autowired
    EquipmentService equipmentService;
    @GetMapping("/createNewReport")
    public String showCreateNewReportForm(Model model) {
        model.addAttribute("residentReportedIssue", new ResidentReportedIssue());
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "reportForm/reportFormStep1";
    }

    @PostMapping("/createNewReport/step2")
    public String showCreateNewReportForm(@ModelAttribute("residentReportedIssue") ResidentReportedIssue residentReportedIssue, Model model) {
        List<Equipment> equipments = equipmentService.getAllEquipmentsByRoom(residentReportedIssue.getRoomId());
        model.addAttribute("equipments", equipments);
        return "reportForm/reportFormStep2";
    }

    @PostMapping("/createNewReport")
    public String createNewReport(@ModelAttribute("residentReportedIssue") ResidentReportedIssue residentReportedIssue) {
        residentReportedIssueService.createNewReport(residentReportedIssue);
        return "redirect:/";
    }
}
