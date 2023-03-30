package com.fptu.maintenancemanagersystem.controller;

import com.fptu.maintenancemanagersystem.model.entities.Staff;
import com.fptu.maintenancemanagersystem.service.FloorService;
import com.fptu.maintenancemanagersystem.service.StaffService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StaffController {
    @Autowired
    StaffService staffService;

    @Autowired
    FloorService floorService;

    @Autowired
    WorkAssignController workAssignController;

    @GetMapping("/staffLogin")
    public String showStaffLoginForm(Model model, HttpSession session) {
        var loginStaff = (Staff) session.getAttribute("staff");
        if (loginStaff == null) {
            model.addAttribute("staff", new Staff());
            return "loginPages/maintenanceStaffLogin";
        } else {
            return "redirect:/staffFilter?workStatus=default";
        }
    }

    @PostMapping("/staffLogin")
    public String staffLogin(@ModelAttribute Staff staff, Model model, HttpSession session) {
        Staff existedStaff = staffService.findUserByLogin(staff.getEmail(), staff.getPassword());


        if (existedStaff == null) {
            String errorMessage = "Sai thông tin đăng nhập. Vui lòng thử lại.";
            model.addAttribute("errorMessage", errorMessage);
            return "loginPages/maintenanceStaffLogin";
        }

        session.setAttribute("staff", existedStaff);
        return "redirect:/staffFilter?workStatus=default";
    }

    @GetMapping("/staff/changePassword")
    public String showChangePassword(HttpSession session) {
        Staff existedStaff = (Staff) session.getAttribute("staff");

        if (existedStaff == null) return "redirect:/";


        return "passwordProblemPages/changePassword";
    }

    @GetMapping("/staff/completeTask/{issueId}")
    public String completeTask(@PathVariable("issueId") int issueId, Model model, HttpSession session) {
        staffService.markWorkProgressAsComplete(issueId);

        return workAssignController.getReportedIssueByFaultedDeviceRecord(issueId, model);
    }
}
