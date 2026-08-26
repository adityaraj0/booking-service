package com.adityaraj.booking_service.service;

import com.adityaraj.booking_service.dto.BookingRequest;
import com.adityaraj.booking_service.dto.BookingResponse;

import java.util.UUID;

public interface BookingService {
    BookingResponse createBooking(BookingRequest booking);

    BookingResponse getBookingById(UUID id);
}
