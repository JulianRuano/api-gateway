package com.singlecode.booking.entity;

import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
public class BookingEntity {

    @Id
    private String id = UUID.randomUUID().toString();

    private String name;
    
}
