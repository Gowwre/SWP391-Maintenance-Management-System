package com.fptu.maintenancemanagersystem.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Staff implements Serializable {

    private int staffId;
    private String fullname;
    private int managerId;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    private String password;
    private LocalDate beginWorkDate;
    private boolean isWorking;
    private int floorId;

}
