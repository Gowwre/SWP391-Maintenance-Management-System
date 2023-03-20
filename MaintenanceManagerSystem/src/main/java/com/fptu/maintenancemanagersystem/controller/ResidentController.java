package com.fptu.maintenancemanagersystem.controller;

import com.fptu.maintenancemanagersystem.model.Equipment;
import com.fptu.maintenancemanagersystem.model.ResidentIssueReportedAndWorkProgress;
import com.fptu.maintenancemanagersystem.model.Room;
import com.fptu.maintenancemanagersystem.service.EquipmentService;
import com.fptu.maintenancemanagersystem.service.ResidentReportedIssueService;
import com.fptu.maintenancemanagersystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ResidentController {
    @Autowired
    ResidentReportedIssueService residentReportedIssueService;

    @Autowired
    RoomService roomService;

    @Autowired
    EquipmentService equipmentService;

    @GetMapping("resident/residentIssueListByEmail")
    public String viewResidentReportedIssue(@RequestParam("email") String email, Model model) {

            List<ResidentIssueReportedAndWorkProgress> residentIssueReportedAndWorkProgressByEmail = residentReportedIssueService.getAllReportedIssueByFaultedDeviceRecordsByEmail(email);
            List<Room> roomList = roomService.getAllRooms();

            model.addAttribute("rooms", roomList);
            model.addAttribute("residentIssueReportedAndWorkProgressByEmail", residentIssueReportedAndWorkProgressByEmail);
            return "residentPages/residentIssueList";

    }

    @GetMapping("resident/viewResidentIssue/{id}")
    public String viewIssueDetail(@PathVariable("id") int issueID, Model model) {

            ResidentIssueReportedAndWorkProgress residentIssueReportedAndWorkProgressByIssueId = residentReportedIssueService.getResidentIssueReportedAndWorkProgressByIssueId(issueID);
            List<Room> roomList = roomService.getAllRooms();
            List<Equipment> equipmentsByIssueId = equipmentService.getEquipmentsByIssueId(issueID);

            model.addAttribute("roomList", roomList);
            model.addAttribute("equipmentsByIssueId", equipmentsByIssueId);
            model.addAttribute("residentIssueReportedAndWorkProgressByIssueId", residentIssueReportedAndWorkProgressByIssueId);
            return "residentPages/residentIssueDetail";

    }

    @PostMapping("/resident/confirmWorkCompletion")
    public String confirmWorkCompletion(@RequestParam("issueId") int issueId, @RequestParam("residentPhoneNumber") String residentPhoneNumber, Model model) {
        residentReportedIssueService.confirmWorkCompletion(issueId, residentPhoneNumber);
        return "redirect:/resident/viewResidentIssue/" + issueId;
    }
}
