package com.parking.repository;

import com.parking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByStatus(Booking.BookingStatus status);
    Optional<Booking> findBySlotIdAndStatus(Long slotId, Booking.BookingStatus status);
    List<Booking> findAllByOrderByStartTimeDesc();
}
