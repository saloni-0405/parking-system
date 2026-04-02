package com.parking.service;

import com.parking.model.Booking;
import com.parking.model.ParkingSlot;
import com.parking.repository.BookingRepository;
import com.parking.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SlotRepository slotRepository;

    public Booking bookSlot(Long slotId, String userName, String vehicleNumber) {
        ParkingSlot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        if (slot.getStatus() == ParkingSlot.SlotStatus.OCCUPIED) {
            throw new RuntimeException("Slot is already occupied");
        }

        // Mark slot as occupied
        slot.setStatus(ParkingSlot.SlotStatus.OCCUPIED);
        slotRepository.save(slot);

        // Create booking
        Booking booking = new Booking();
        booking.setSlot(slot);
        booking.setUserName(userName);
        booking.setVehicleNumber(vehicleNumber);
        booking.setStartTime(LocalDateTime.now());
        booking.setStatus(Booking.BookingStatus.ACTIVE);

        return bookingRepository.save(booking);
    }

    public Booking releaseSlot(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getStatus() == Booking.BookingStatus.COMPLETED) {
            throw new RuntimeException("Booking already completed");
        }

        // End booking
        booking.setEndTime(LocalDateTime.now());
        booking.setStatus(Booking.BookingStatus.COMPLETED);
        bookingRepository.save(booking);

        // Free up slot
        ParkingSlot slot = booking.getSlot();
        slot.setStatus(ParkingSlot.SlotStatus.AVAILABLE);
        slotRepository.save(slot);

        return booking;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAllByOrderByStartTimeDesc();
    }

    public List<Booking> getActiveBookings() {
        return bookingRepository.findByStatus(Booking.BookingStatus.ACTIVE);
    }
}
