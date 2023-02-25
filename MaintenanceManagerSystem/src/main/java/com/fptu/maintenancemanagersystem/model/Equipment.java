/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptu.maintenancemanagersystem.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author lmphi
 */
@Getter @Setter @NoArgsConstructor
public class Equipment {
    public int id;
    public String equipmentName;
    public String equipmentCategory;
    public LocalDate installationDate;
    public LocalDate warrantyDate;
    public String brand;
    public LocalDate replacedDate;
    public String description;
}
