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
    private int equipmentId;
    private int roomId;
    private String equipmentName;

    private LocalDate installationDate;

    private String brand;

    private String description;
}
