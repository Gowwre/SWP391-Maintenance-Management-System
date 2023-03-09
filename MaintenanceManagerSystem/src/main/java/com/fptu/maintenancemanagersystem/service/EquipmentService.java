/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptu.maintenancemanagersystem.service;

import com.fptu.maintenancemanagersystem.dao.Equipment.EquipmentRepository;
import com.fptu.maintenancemanagersystem.model.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author lmphi
 */
@Service
public class EquipmentService {

    @Autowired
    EquipmentRepository equipmentRepository;

    public List<Equipment> getAllEquipmentsByRoom(int roomId) {
        return equipmentRepository.getAllEquipmentsByRoom(roomId);
    }

    public List<Equipment> getAllEquipments() {
        return equipmentRepository.getAllEquipments();
    }
}
