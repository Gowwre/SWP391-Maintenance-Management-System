package com.fptu.maintenancemanagersystem.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class MainController {


    @RequestMapping(value = {"/","/index"},method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = {"/viewAllRooms"},method = RequestMethod.GET)
    public String viewAllRooms(){
        return "viewRooms/viewAllRooms";
    }

    @RequestMapping(value = {"/report"},method = RequestMethod.GET)
    public String report(){
        return "reportForm/residentReportFaultDevice";
    }



    @RequestMapping(value = {"/forgotPassword"},method = RequestMethod.GET)
    public String forgotPassword(){
        return "passwordProblemPages/forgotPassword";
    }


    
    //Gain temporary access to create new password page
    @RequestMapping(value = {"/createNewPassword"},method = RequestMethod.GET)
    public String createNewPassword(){
        return "passwordProblemPages/createNewPassword";
    }

    //Gain temporary access to change password page
    @RequestMapping(value = {"/changePassword"},method = RequestMethod.GET)
    public String changePassword(){
        return "passwordProblemPages/changePassword";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
