package com.fptu.maintenancemanagersystem.controller;

import com.fptu.maintenancemanagersystem.model.Equipment;
import com.fptu.maintenancemanagersystem.model.FaultedDevice;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    
    @RequestMapping(value = {"/residentReportedIssues"},method = RequestMethod.GET)
    public String viewResidentReportedIssue(Model model) {
        try {
            List<FaultedDevice> faultedDevices = faultedDeviceService.getAllFaultedDevices();
            model.addAttribute("faultDevices", faultedDevices);
            List<Room> rooms = roomService.getAllRooms();
            model.addAttribute("rooms", rooms);
            List<ResidentReportedIssue> residentReportedIssues = residentReportedIssueService.getAllResidentReportedIssue();
            model.addAttribute("residentReportedIssueList", residentReportedIssues);
            return "managerPages/reportedIssueList";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "errorPage";
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
           return "errorPage";
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
        residentReportedIssueService.createNewReport(residentReportedIssue,equipmentIds);
        return "redirect:/";
    }
}
