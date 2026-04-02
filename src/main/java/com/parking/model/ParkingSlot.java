package com.parking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "parking_slot")
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "slot_number", unique = true, nullable = false)
    private String slotNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SlotStatus status = SlotStatus.AVAILABLE;

    @Column(name = "area")
    private String area;

    public enum SlotStatus {
        AVAILABLE, OCCUPIED
    }

    // Constructors
    public ParkingSlot() {}

    public ParkingSlot(String slotNumber, String area) {
        this.slotNumber = slotNumber;
        this.area = area;
        this.status = SlotStatus.AVAILABLE;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSlotNumber() { return slotNumber; }
    public void setSlotNumber(String slotNumber) { this.slotNumber = slotNumber; }

    public SlotStatus getStatus() { return status; }
    public void setStatus(SlotStatus status) { this.status = status; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
}
