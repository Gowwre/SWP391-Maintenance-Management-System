package com.fptu.maintenancemanagersystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Room implements Serializable {
    private int roomId;
    private String roomNumber;
    private int floorId;
    private boolean isOccupied;

}