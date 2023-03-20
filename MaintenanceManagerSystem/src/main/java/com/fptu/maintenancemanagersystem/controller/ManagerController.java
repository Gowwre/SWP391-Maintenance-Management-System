package com.fptu.maintenancemanagersystem.controller;

import com.fptu.maintenancemanagersystem.model.Floor;
import com.fptu.maintenancemanagersystem.model.Manager;
import com.fptu.maintenancemanagersystem.model.Staff;
import com.fptu.maintenancemanagersystem.service.FloorService;
import com.fptu.maintenancemanagersystem.service.ManagerService;
import com.fptu.maintenancemanagersystem.service.StaffService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @Autowired
    StaffService staffService;

    @Autowired
    FloorService floorService;

    @GetMapping("/managerLogin")
    public String showManagerLoginForm(Model model, HttpSession session) {
        var loginManager = (Manager) session.getAttribute("manager");

        if (loginManager == null) {
            model.addAttribute("manager", new Manager());
            return "loginPages/maintenanceManagerLogin";
        } else {
            return "redirect:/manager/residentReportedIssues";
        }
    }

    @PostMapping(value = {"/managerLogin"})
    public String managerLogin(@ModelAttribute("manager") Manager manager, HttpSession session, Model model) {
        Manager existedManager = managerService.findUserByLogin(manager.getEmail(), manager.getPassword());
        if (existedManager == null) {
            String errorMessage = "Sai thông tin đăng nhập. Vui lòng thử lại.";
            model.addAttribute("errorMessage", errorMessage);
            return "loginPages/maintenanceManagerLogin";
        }

        session.setAttribute("manager", existedManager);
        return "redirect:/manager/residentReportedIssues";
    }

    @GetMapping("/manager/changePassword")
    public String showChangePassword(HttpSession session) {
        Manager existedManager = (Manager) session.getAttribute("manager");

        if (existedManager == null) return "redirect:/";


        return "passwordProblemPages/changePassword";
    }

    @GetMapping("manager/staffList")
    public String showStaffList(Model model) {
        List<Staff> staffList = staffService.getAllStaff();
        List<Floor> floorList = floorService.getAll();

        model.addAttribute("floorList", floorList);
        model.addAttribute("staffList", staffList);
        return "managerPages/staffManagementList";
    }

    @GetMapping("/manager/staffEditor/{id}")
    public String showStaffEditor(@PathVariable("id") int staffId, Model model) {
        Staff staff = staffService.getStaffById(staffId);
        model.addAttribute("currentStaffInfo", staff);
        return "managerPages/staffEditor";
    }

    @PostMapping("/manager/staffEditor/updateStaff")
    public String updateStaff(@ModelAttribute("currentStaffInfo") Staff staff, Model model) {
staffService.updateStaff(staff);
        return "redirect:/manager/viewStaff/" + staff.getStaffId();
    }

    @GetMapping("/manager/viewStaff/{staffId}")
    public String viewStaff(@PathVariable("staffId") int staffId, Model model) {
        Staff staff = staffService.getStaffById(staffId);
        List<Floor> floorList = floorService.getAll();
        model.addAttribute("staff", staff);
        model.addAttribute("floorList", floorList);
        return "managerPages/viewStaff";
    }

    @GetMapping("/manager/getForCreateStaff")
    public String getForCreateStaff(Model model) {
        List<Floor> floorList = floorService.getAll();

        model.addAttribute("floorList", floorList);
        model.addAttribute("newStaff", new Staff());
        return "managerPages/staffCreator";
    }

    @PostMapping("/manager/createStaff/createNewStaff")
    public String createStaff(@ModelAttribute("newStaff") Staff staff, Model model) {
        staffService.createStaff(staff);
        return "redirect:/manager/staffList";
    }
}
