/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptu.maintenancemanagersystem.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author lmphi
 */
@Data
@NoArgsConstructor
public class Equipment implements Serializable {
    public int id;
    public int roomId;
    public String equipmentName;

    public LocalDate installationDate;

    public String brand;

    public String description;
}
