package com.adityaraj.booking_service.service.impl;

import com.adityaraj.booking_service.dto.BookingRequest;
import com.adityaraj.booking_service.dto.BookingResponse;
import com.adityaraj.booking_service.entity.Booking;
import com.adityaraj.booking_service.entity.Product;
import com.adityaraj.booking_service.entity.enums.Status;
import com.adityaraj.booking_service.repository.BookingRepository;
import com.adityaraj.booking_service.repository.ProductRepository;
import com.adityaraj.booking_service.service.BookingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service

public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ProductRepository productRepository;


    public BookingServiceImpl(
            BookingRepository bookingRepository,
            ProductRepository productRepository) {
        this.bookingRepository = bookingRepository;
        this.productRepository = productRepository;
    }

    @Override
    public BookingResponse createBooking(BookingRequest booking) {
        Booking createBooking = new Booking();
        createBooking.setUserId(booking.getUserId());

        Product product = productRepository.getReferenceById(booking.getProductId());
        createBooking.setProduct(product);
        createBooking.setStartTime(booking.getStartTime());
        createBooking.setEndTime(booking.getEndTime());
        createBooking.setStatus(Status.CREATED);
        createBooking.setPaymentStatus(Status.PENDING);
        createBooking.setAmount(new BigDecimal(0));
        createBooking.setCreatedAt(LocalDateTime.now());

        Booking createdBooking = bookingRepository.save(createBooking);
        return generateBookingResponse(createdBooking);
    }

    @Override
    public BookingResponse getBookingById(UUID id) {
        Booking booking =  bookingRepository.findById(id).orElse(null);

        return booking == null ? null : generateBookingResponse(booking);

    }

    @Override
    public List<BookingResponse> getBookingByUserId(UUID userId){
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return bookings.stream()
                .map(this::generateBookingResponse)
                .toList();
    }

    @Override
    public List<BookingResponse> getBookings() {
        List<Booking> bookings = bookingRepository.findAll();

        return  bookings.stream()
                .map(this::generateBookingResponse)
                .toList();
    }

    @Override
    public BookingResponse updateBooking(UUID id, BookingRequest booking) {
        Booking existingBooking = bookingRepository.findById(id).orElse(null);
        if (existingBooking == null) {
            return null;
        }
        existingBooking.setStartTime(booking.getStartTime());
        existingBooking.setEndTime(booking.getEndTime());
        existingBooking.setUpdatedAt(LocalDateTime.now());

        Booking updatedBooking = bookingRepository.save(existingBooking);
        return generateBookingResponse(updatedBooking);
    }

    @Override
    public void cancelBooking(UUID id) {
        Booking booking = bookingRepository.findById(id).orElse(null);

        if (booking == null) {
            return;
        }

        booking.setStatus(Status.CANCELLED);
        booking.setUpdatedAt(LocalDateTime.now());

        bookingRepository.save(booking);
    }


    private BookingResponse generateBookingResponse(Booking booking) {
        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setId(booking.getId());
        bookingResponse.setUserId(booking.getUserId());
        bookingResponse.setProduct(booking.getProduct());
        bookingResponse.setStartTime(booking.getStartTime());
        bookingResponse.setEndTime(booking.getEndTime());
        bookingResponse.setStatus(booking.getStatus());
        bookingResponse.setPaymentStatus(booking.getPaymentStatus());
        bookingResponse.setAmount(booking.getAmount() == null ? new BigDecimal(0) : booking.getAmount());
        bookingResponse.setCreatedAt(booking.getCreatedAt());
        bookingResponse.setUpdatedAt(booking.getUpdatedAt());

        return bookingResponse;
    }
}
