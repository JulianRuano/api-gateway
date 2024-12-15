package com.singlecode.product.entity;

import java.util.UUID;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {

    @Id
    private String id = UUID.randomUUID().toString();

    private String name;
    private String description;
    private Double price;
    private String image;
}
