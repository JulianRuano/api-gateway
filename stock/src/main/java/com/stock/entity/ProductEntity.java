package com.stock.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
    @Column(name = "product_id")
    private String id = UUID.randomUUID().toString();

    private String name;

    @OneToOne(mappedBy = "product")
    private StockEntity stock;
}
