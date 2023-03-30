package com.fptu.maintenancemanagersystem.controller;

import com.fptu.maintenancemanagersystem.model.entities.Manager;
import com.fptu.maintenancemanagersystem.model.entities.Staff;
import com.fptu.maintenancemanagersystem.service.ManagerService;
import com.fptu.maintenancemanagersystem.service.StaffService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordChangeController {
    @Autowired
    ManagerService managerService;

    @Autowired
    StaffService staffService;

    @PostMapping("/passwordProblemPages/changePassword/change")
    public String changePassword(@RequestParam("current-password") String currentPassword,
                                 @RequestParam("new-password") String newPassword,
                                 @RequestParam("confirm-password") String confirmPassword,
                                 Model model,
                                 HttpSession session) {

        Staff currentLoggedInStaff = (Staff) session.getAttribute("staff");
        Manager currentLoggedInManager = (Manager) session.getAttribute("manager");

        boolean isNoUserLoggedIn = currentLoggedInManager == null && currentLoggedInStaff == null;

        if (isNoUserLoggedIn) {
            return "redirect:/";
        }

        String errorMessage = validatePasswordChange(currentLoggedInStaff, currentLoggedInManager, currentPassword, newPassword, confirmPassword);
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            return "passwordProblemPages/changePassword";
        }

        if (currentLoggedInManager != null) {
            managerService.changePassword(currentLoggedInManager.getEmail(), currentPassword, newPassword);
        } else {
            staffService.changePassword(currentLoggedInStaff.getEmail(), currentPassword, newPassword);
        }

        session.invalidate();
        return "redirect:/";
    }

    private String validatePasswordChange(Staff currentLoggedInStaff, Manager currentLoggedInManager, String currentPassword, String newPassword, String confirmPassword) {
        boolean isNewPasswordNotConfirmed = !newPassword.equals(confirmPassword);
        boolean isCurrentPasswordInvalid = (currentLoggedInStaff != null && !currentPassword.equals(currentLoggedInStaff.getPassword())) ||
                (currentLoggedInManager != null && !currentPassword.equals(currentLoggedInManager.getPassword()));


        if (isNewPasswordNotConfirmed) {
            return "Mật khẩu xác nhận không trùng khớp. Vui lòng thử lại.";
        }
        if (isCurrentPasswordInvalid) {
            return "Mật khẩu hiện tại không đúng. Vui lòng thử lại.";
        }

        return null;
    }


}
