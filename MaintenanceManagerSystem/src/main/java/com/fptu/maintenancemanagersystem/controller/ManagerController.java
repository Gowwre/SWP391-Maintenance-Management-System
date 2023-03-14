package com.fptu.maintenancemanagersystem.controller;

import com.fptu.maintenancemanagersystem.model.Manager;
import com.fptu.maintenancemanagersystem.service.ManagerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @GetMapping("/managerLogin")
    public String showManagerLoginForm(Model model, HttpSession session) {
        var loginManager = (Manager) session.getAttribute("manager");

        if (loginManager == null) {
            model.addAttribute("manager", new Manager());
            return "loginPages/maintenanceManagerLogin";
        } else {
            return "redirect:/residentReportedIssues";
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
        return "redirect:/residentReportedIssues";
    }

    @GetMapping("homePages/managerHomePage")
    public String showManagerHomePage(HttpSession session, Model model) {
        Manager manager = (Manager) session.getAttribute("manager");
        if (manager == null)
            return "redirect:/";

        return "redirect:/residentReportedIssues";
    }

    @GetMapping("/homePages/managerHomePage/changePassword")
    public String showChangePassword(HttpSession session) {
        Manager existedManager = (Manager) session.getAttribute("manager");

        if (existedManager == null) return "redirect:/";


        return "passwordProblemPages/changePassword";
    }

}
