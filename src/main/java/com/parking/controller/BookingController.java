package com.parking.controller;

import com.parking.model.Booking;
import com.parking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // POST book a slot
    @PostMapping("/book")
    public ResponseEntity<?> bookSlot(@RequestBody Map<String, String> body) {
        try {
            Long slotId = Long.parseLong(body.get("slotId"));
            String userName = body.get("userName");
            String vehicleNumber = body.get("vehicleNumber");
            Booking booking = bookingService.bookSlot(slotId, userName, vehicleNumber);
            return ResponseEntity.ok(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // PUT release a slot
    @PutMapping("/release/{bookingId}")
    public ResponseEntity<?> releaseSlot(@PathVariable Long bookingId) {
        try {
            Booking booking = bookingService.releaseSlot(bookingId);
            return ResponseEntity.ok(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // GET all bookings
    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    // GET active bookings only
    @GetMapping("/bookings/active")
    public ResponseEntity<List<Booking>> getActiveBookings() {
        return ResponseEntity.ok(bookingService.getActiveBookings());
    }
}
