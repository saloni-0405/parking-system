package com.parking.service;

import com.parking.model.ParkingSlot;
import com.parking.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;

    public List<ParkingSlot> getAllSlots() {
        return slotRepository.findAll();
    }

    public List<ParkingSlot> getAvailableSlots() {
        return slotRepository.findByStatus(ParkingSlot.SlotStatus.AVAILABLE);
    }

    public ParkingSlot addSlot(ParkingSlot slot) {
        if (slotRepository.existsBySlotNumber(slot.getSlotNumber())) {
            throw new RuntimeException("Slot number already exists: " + slot.getSlotNumber());
        }
        slot.setStatus(ParkingSlot.SlotStatus.AVAILABLE);
        return slotRepository.save(slot);
    }

    public ParkingSlot updateSlotStatus(Long id, ParkingSlot.SlotStatus status) {
        ParkingSlot slot = slotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Slot not found with id: " + id));
        slot.setStatus(status);
        return slotRepository.save(slot);
    }

    public void deleteSlot(Long id) {
        slotRepository.deleteById(id);
    }

    public Optional<ParkingSlot> getSlotById(Long id) {
        return slotRepository.findById(id);
    }
}
