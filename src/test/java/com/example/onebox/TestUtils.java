package com.example.onebox;

import com.example.onebox.controller.dto.CartItemRequest;
import com.example.onebox.domain.Cart;
import com.example.onebox.domain.CartItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public static Cart buildCartMock() {
        return Cart.builder()
                .id(1L)
                .creationDate(LocalDateTime.now())
                .build();
    }

    public static List<CartItem> buildCartItems() {
        return Arrays.asList(
                CartItem.builder()
                        .id(1L)
                        .cartId(1L)
                        .amount(BigDecimal.valueOf(1))
                        .description("Item 1")
                        .build(),
                CartItem.builder()
                        .id(2L)
                        .cartId(1L)
                        .amount(BigDecimal.valueOf(2))
                        .description("Item 2")
                        .build());
    }

    public static CartItemRequest buildCartItemRequest() {
        return CartItemRequest.builder()
                .amount(BigDecimal.valueOf(1))
                .description("Item 1")
                .build();
    }

}
