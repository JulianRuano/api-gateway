package com.stock.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockDto {
    @NotBlank(message = "Code is required")
    private String code;

    @NotBlank(message = "Quantity is required")
    private Integer quantity;
}
