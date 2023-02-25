package com.fptu.maintenancemanagersystem.controller;

import com.fptu.maintenancemanagersystem.model.ResidentReportedIssue;
import com.fptu.maintenancemanagersystem.model.Room;
import com.fptu.maintenancemanagersystem.service.ResidentReportedIssue.ResidentReportedIssueService;
import com.fptu.maintenancemanagersystem.service.Room.RoomService;
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

    @GetMapping("/createNewReport")
    public String showCreateNewReportForm(Model model) {
        model.addAttribute("residentReportedIssue", new ResidentReportedIssue());
        List<Room> allRooms = roomService.getAllRooms();
        model.addAttribute("allRooms", allRooms);
        return "reportForm/residentReportFaultDevice";
    }

    @PostMapping("/createNewReport")
    public String createNewReport(@ModelAttribute("residentReportedIssue") ResidentReportedIssue residentReportedIssue) {
        residentReportedIssueService.createNewReport(residentReportedIssue);
        return "redirect:/";
    }
}
