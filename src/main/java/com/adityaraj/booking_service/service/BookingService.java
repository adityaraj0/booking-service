package com.adityaraj.booking_service.service;

import com.adityaraj.booking_service.dto.BookingRequest;
import com.adityaraj.booking_service.dto.BookingResponse;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    BookingResponse createBooking(BookingRequest booking);

    BookingResponse getBookingById(UUID id);

    List<BookingResponse> getBookingByUserId(UUID userId);

    List<BookingResponse> getBookings();

    BookingResponse updateBooking(UUID id, BookingRequest booking);

    void cancelBooking(UUID id);
}
