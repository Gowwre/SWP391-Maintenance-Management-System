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
        model.addAttribute("faultDevices", faultedDeviceService.findAll());
        return "faultDevicePages/faultDevices";
    }
}
