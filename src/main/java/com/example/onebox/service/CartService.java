package com.example.onebox.service;

import com.example.onebox.controller.dto.CartItemRequest;
import com.example.onebox.controller.dto.CartResponse;

public interface CartService {

    CartResponse createCart();

    CartResponse getCart(Long cartId);

    CartResponse addItem(Long cartId, CartItemRequest cartItemRequest);

    void deleteCart(Long cartId);

}
