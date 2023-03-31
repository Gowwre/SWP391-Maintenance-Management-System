package com.fptu.maintenancemanagersystem.controller;

import com.fptu.maintenancemanagersystem.model.dto.WorkProgressAndStaffNameRecord;
import com.fptu.maintenancemanagersystem.model.entities.*;
import com.fptu.maintenancemanagersystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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



    @GetMapping("/filter")
    public String viewFilteredReportedIssues(@RequestParam("workStatus") String workStatus, Model model){
        if (workStatus.equalsIgnoreCase("default")){
            workProgressService.markOverdueWork();
            var residentReportedIssues = residentReportedIssueService.getAllReportedIssue();

            model.addAttribute("residentReportedIssueList", residentReportedIssues);
            return "managerPages/reportedIssueList";
        } else {
            workProgressService.markOverdueWork();
            var filteredReportedIssues = residentReportedIssueService.getAllReportedIssue()
                    .stream()
                    .filter(issue -> Objects.nonNull(issue.workStatus()) && issue.workStatus().equalsIgnoreCase(workStatus))
                    .toList();

            model.addAttribute("filteredReportedIssues", filteredReportedIssues);
            model.addAttribute("filtered",true);
            return "managerPages/reportedIssueList";
        }

    }


    @GetMapping("manager/viewIssue/{id}")
    public String getReportedIssueByFaultedDeviceRecord(@PathVariable("id") int issueID, Model model) {

        ResidentReportedIssue residentReportedIssue = residentReportedIssueService.getResidentReportedIssueById(issueID);
        List<Equipment> equipmentsByIssueId = equipmentService.getEquipmentsByIssueId(issueID);
        List<Staff> workingStaffs = staffService.getWorkingStaff();
        FaultedDevice faultedDevice = new FaultedDevice();
        WorkProgressAndStaffNameRecord workProgressAndStaffNameRecord = workProgressService.findWorkProgressAndStaffNameByIssueId(issueID);

        model.addAttribute("workProgressAndStaffName", workProgressAndStaffNameRecord);
        model.addAttribute("faultedDevice", faultedDevice);
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("equipments", equipmentsByIssueId);
        model.addAttribute("availableIssue", residentReportedIssue);
        model.addAttribute("workingStaffs", workingStaffs);
        return "managerPages/viewIssue";


    }

    @GetMapping("/createReport")
    public String showCreateNewReportForm(Model model) {
        model.addAttribute("residentReportedIssue", new ResidentReportedIssue());

        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "reportForm/reportFormStep1";

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

    @GetMapping("/manager/updateAssignedStaff")
    public String updateAssignStaff(@RequestParam("issueId") int issueId, @RequestParam("staffId") int assignStaffId, @RequestParam("deadline") LocalDate deadline, Model model) {

        faultedDeviceService.updateAssignStaffByIssueId(assignStaffId, issueId);
        workProgressService.setDeadlineByIssueId(issueId, deadline);
        return "redirect:/filter?workStatus=default";

    }

}
