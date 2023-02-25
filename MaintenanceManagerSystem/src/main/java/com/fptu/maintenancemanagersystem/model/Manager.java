package com.fptu.maintenancemanagersystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter @Setter @NoArgsConstructor
public class Manager implements Serializable {
    private int managerId;
    private String name;
    private String email;
    private String password;
}
