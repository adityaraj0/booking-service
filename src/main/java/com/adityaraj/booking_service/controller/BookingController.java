package com.adityaraj.booking_service.controller;

import com.adityaraj.booking_service.dto.BookingRequest;
import com.adityaraj.booking_service.dto.BookingResponse;

import com.adityaraj.booking_service.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/booking")
public class BookingController {

    public BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest booking) {
        BookingResponse bookingResponse = bookingService.createBooking(booking);
        return ResponseEntity.ok(bookingResponse);
    }

    @GetMapping
    public ResponseEntity<BookingResponse> getBookingById(@RequestParam UUID id) {
        BookingResponse bookingResponse = bookingService.getBookingById(id);
        if (bookingResponse == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(bookingResponse);
        }
    }

}
