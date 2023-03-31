package com.fptu.maintenancemanagersystem.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class CommonController {


    @RequestMapping(value = {"/","/index"},method = RequestMethod.GET)
    public String index(){
        return "index";
    }
    
    @RequestMapping(value = {"/createNewPassword"},method = RequestMethod.GET)
    public String createNewPassword(){
        return "passwordProblemPages/createNewPassword";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
