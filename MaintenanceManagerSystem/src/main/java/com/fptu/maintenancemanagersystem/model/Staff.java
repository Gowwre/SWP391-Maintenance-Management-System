package com.fptu.maintenancemanagersystem.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor
public class Staff implements Serializable {

    private int id;
    private String fullname;
    private int managerId;
    private LocalDate dateOfBirth;
    private String specialization;
    private String email;
    private String phoneNumber;
    private String password;
    private LocalDate beginWorkDate;
    private boolean isWorking;


}
