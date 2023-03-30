package com.fptu.maintenancemanagersystem.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
public class Manager implements Serializable {
    private int managerId;
    private String name;
    private String email;
    private String password;
}
