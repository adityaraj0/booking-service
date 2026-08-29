package com.adityaraj.booking_service.controller;

import com.adityaraj.booking_service.dto.BookingRequest;
import com.adityaraj.booking_service.dto.BookingResponse;

import com.adityaraj.booking_service.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable UUID id) {
        BookingResponse bookingResponse = bookingService.getBookingById(id);
        if (bookingResponse == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(bookingResponse);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponse>> getBookingByUserId(@PathVariable UUID userId) {

        List<BookingResponse> bookingResponse = bookingService.getBookingByUserId(userId);
        if (bookingResponse == null) {
            return ResponseEntity.notFound().build();
        } else  {
            return ResponseEntity.ok(bookingResponse);
        }
    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookings() {

        List<BookingResponse> bookingResponses = bookingService.getBookings();

        if (bookingResponses == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookingResponses);

    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponse> updateBooking(@PathVariable UUID id,
                                                        @RequestBody BookingRequest booking) {
                 BookingResponse bookingResponse = bookingService.updateBooking(id, booking);
                 if (bookingResponse == null) {
                     return ResponseEntity.notFound().build();
                 }
                 return ResponseEntity.ok(bookingResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookingResponse> cancelBooking(@PathVariable UUID id) {
        BookingResponse booking = bookingService.getBookingById(id);
        if (booking == null) {
            return ResponseEntity.notFound().build();
        }
        bookingService.cancelBooking(id);
        return ResponseEntity.noContent().build();
    }

}
