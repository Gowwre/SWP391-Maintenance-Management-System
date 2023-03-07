/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptu.maintenancemanagersystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 *
 * @author lmphi
 */
@Data
@NoArgsConstructor
public class Floor implements Serializable {
    private int id;
    private String name;
    
}
