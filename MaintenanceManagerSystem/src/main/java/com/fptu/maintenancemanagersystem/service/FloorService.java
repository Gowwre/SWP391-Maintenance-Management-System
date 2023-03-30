/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptu.maintenancemanagersystem.service;

import com.fptu.maintenancemanagersystem.dao.Floor.FloorRepository;
import com.fptu.maintenancemanagersystem.model.entities.Floor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author lmphi
 */
@Service
public class FloorService {
    @Autowired
    FloorRepository floorRepository;

    public List<Floor> getAll() {
        return floorRepository.getAll();
    }
    
}
