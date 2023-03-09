package com.fptu.maintenancemanagersystem.controller;

import com.fptu.maintenancemanagersystem.service.FaultedDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FaultDeviceController {

    @Autowired
    FaultedDeviceService faultedDeviceService;

    @GetMapping("/faultDevices")
    public String showFaultDevices(Model model) {
        try {
            model.addAttribute("faultDevices", faultedDeviceService.getAllFaultedDevices());
            return "faultDevicePages/faultDevices";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
