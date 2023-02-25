package com.fptu.maintenancemanagersystem.controller;

import com.fptu.maintenancemanagersystem.model.Staff;
import com.fptu.maintenancemanagersystem.service.Staff.StaffService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StaffController {
    @Autowired
    StaffService staffService;


    @GetMapping("/staffLogin")
    public String showStaffLoginForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "loginPages/maintenanceStaffLogin";
    }

    @PostMapping("/staffLogin")
    public String staffLogin(@ModelAttribute Staff staff, Model model, HttpSession session) {
        Staff existedStaff = staffService.findUserByLogin(staff.getEmail(), staff.getPassword());
        if (existedStaff != null) {
            session.setAttribute("staff", existedStaff);
            return "homePages/maintenanceStaffHomePage";
        } else {
            String errorMessage = "Sai thông tin đăng nhập. Vui lòng thử lại.";
            model.addAttribute("errorMessage", errorMessage);
            return "loginPages/maintenanceStaffLogin";
        }
    }



}
