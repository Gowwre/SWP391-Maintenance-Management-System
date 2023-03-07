package com.fptu.maintenancemanagersystem.controller;

import com.fptu.maintenancemanagersystem.model.Manager;
import com.fptu.maintenancemanagersystem.model.Staff;
import com.fptu.maintenancemanagersystem.service.StaffService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


        if (existedStaff == null) {
            String errorMessage = "Sai thông tin đăng nhập. Vui lòng thử lại.";
            model.addAttribute("errorMessage", errorMessage);
            return "loginPages/maintenanceStaffLogin";
        }

        session.setAttribute("staff", existedStaff);
        return "homePages/maintenanceStaffHomePage";
    }

    @GetMapping("/homePages/maintenanceStaffHomePage")
    public String showStaffHomePage(HttpSession session) {
        Staff staff = (Staff) session.getAttribute("staff");
        if (staff == null) return "redirect:/";

        return "homePages/maintenanceStaffHomePage";
    }

    @GetMapping("/homePages/maintenanceStaffHomePage/changePassword")
    public String showChangePassword(HttpSession session) {
        Staff existedStaff = (Staff) session.getAttribute("staff");

        if (existedStaff == null) return "redirect:/";


        return "passwordProblemPages/changePassword";
    }


}
