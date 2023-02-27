package com.example.onebox.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemRequest {

    @NotBlank(message = "{cart.item.validation.description}")
    private String description;

    @NotNull(message = "{cart.item.validation.amount}")
    @Min(value = 0, message = "{cart.item.validation.amount}")
    private BigDecimal amount;

}
