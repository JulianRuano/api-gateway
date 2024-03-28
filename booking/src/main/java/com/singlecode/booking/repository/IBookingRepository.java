package com.singlecode.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.singlecode.booking.entity.BookingEntity;

public interface IBookingRepository extends JpaRepository<BookingEntity, String>{
    
}
