package com.fptu.maintenancemanagersystem.controller;

import com.fptu.maintenancemanagersystem.model.Floor;
import com.fptu.maintenancemanagersystem.model.Manager;
import com.fptu.maintenancemanagersystem.model.Staff;
import com.fptu.maintenancemanagersystem.service.FloorService;
import com.fptu.maintenancemanagersystem.service.StaffService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StaffController {
    @Autowired
    StaffService staffService;

    @Autowired
    FloorService floorService;

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
        return "redirect:/workAssignList";
    }

    @GetMapping("/homePages/maintenanceStaffHomePage")
    public String showStaffHomePage(HttpSession session) {
        Staff staff = (Staff) session.getAttribute("staff");
        if (staff == null) return "redirect:/";

        return "redirect:/workAssignList";
    }

    @GetMapping("/homePages/maintenanceStaffHomePage/changePassword")
    public String showChangePassword(HttpSession session) {
        Staff existedStaff = (Staff) session.getAttribute("staff");

        if (existedStaff == null) return "redirect:/";


        return "passwordProblemPages/changePassword";
    }

    @GetMapping("/staffList")
    public String showStaffList(Model model) {
        List<Staff> staffList = staffService.getAllStaff();
        List<Floor> floorList = floorService.getAll();

        model.addAttribute("floorList", floorList);
        model.addAttribute("staffList", staffList);
        return "managerPages/staffManagementList";
    }

    @GetMapping("/viewStaff/{staffId}")
    public String viewStaff(@PathVariable("staffId") int staffId, Model model) {
        Staff staff = staffService.getStaffById(staffId);
        List<Floor> floorList = floorService.getAll();
        model.addAttribute("staff", staff);
        model.addAttribute("floorList", floorList);
        return "managerPages/viewStaff";
    }
}
