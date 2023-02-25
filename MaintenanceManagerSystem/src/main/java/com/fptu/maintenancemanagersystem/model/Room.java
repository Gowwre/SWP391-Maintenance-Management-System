package com.fptu.maintenancemanagersystem.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Room implements Serializable {
    private int roomId;
    private String roomNumber;
    private int floorId;
    private boolean isOccupied;

}