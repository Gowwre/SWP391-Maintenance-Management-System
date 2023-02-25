package com.fptu.maintenancemanagersystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class Room implements Serializable {
    private int roomId;
    private String roomNumber;
    private int floorId;
    private boolean isOccupied;
    private int staffId;
}