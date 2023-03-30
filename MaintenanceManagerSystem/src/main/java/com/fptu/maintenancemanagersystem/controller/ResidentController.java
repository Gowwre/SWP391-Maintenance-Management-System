package com.fptu.maintenancemanagersystem.controller;

import com.fptu.maintenancemanagersystem.model.dto.SubmittedReportedIssuesDTO;
import com.fptu.maintenancemanagersystem.model.entities.Equipment;
import com.fptu.maintenancemanagersystem.model.dto.ResidentIssueReportedAndWorkProgress;
import com.fptu.maintenancemanagersystem.model.entities.Room;
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

           List<SubmittedReportedIssuesDTO> submittedIssues = residentReportedIssueService.getSubmittedReportedIssuesByEmail(email);


            model.addAttribute("residentIssueReportedAndWorkProgressByEmail", submittedIssues);
            model.addAttribute("email", email);
            return "residentPages/residentIssueList";

    }

    @GetMapping("/residentFilter")
    public String viewFilteredResidentReportedIssue(@RequestParam("email") String email, @RequestParam("residentConfirmation") String residentConfirmation, Model model){
        List<SubmittedReportedIssuesDTO> submittedIssues = residentReportedIssueService.getSubmittedReportedIssuesByEmail(email);
        if (residentConfirmation.isEmpty()){
            model.addAttribute("residentIssueReportedAndWorkProgressByEmail", submittedIssues);
        } else {

            var filteredSubmittedIssues = submittedIssues.stream()
                    .filter(submittedIssue -> submittedIssue.residentCompletionConfirmation()== Boolean.parseBoolean(residentConfirmation))
                    .toList();

            model.addAttribute("residentIssueReportedAndWorkProgressByEmailFiltered", filteredSubmittedIssues);
            model.addAttribute("filtered",true);
        }
        model.addAttribute("email", email);
        return "residentPages/residentIssueList";
    }

    @GetMapping("/viewResidentIssue/{id}")
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
        return "redirect:/viewResidentIssue/" + issueId;
    }
}
