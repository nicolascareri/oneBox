package com.example.onebox.service.impl;

import com.example.onebox.controller.dto.CartItemRequest;
import com.example.onebox.controller.dto.CartResponse;
import com.example.onebox.domain.Cart;
import com.example.onebox.domain.CartItem;
import com.example.onebox.repository.CartItemRepository;
import com.example.onebox.repository.CartRepository;
import com.example.onebox.service.CartService;
import com.example.onebox.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartResponse createCart() {
        Cart save = cartRepository.save(new Cart());
        return getCart(save.getId());
    }

    @Override
    public CartResponse getCart(Long cartId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() ->
                        new IllegalArgumentException(MessageUtils.getMessage("cart.not.found")));

        List<CartItem> items = cartItemRepository.findByCartId(cartId);

        return CartResponse.builder()
                .cartId(cart.getId())
                .items(items)
                .build();
    }

    @Override
    public CartResponse addItem(Long cartId, CartItemRequest cartItemRequest) {

        if (!cartRepository.existsById(cartId))
            throw new IllegalArgumentException(MessageUtils.getMessage("cart.not.found"));

        CartItem cartItem = CartItem.builder()
                .cartId(cartId)
                .amount(cartItemRequest.getAmount())
                .description(cartItemRequest.getDescription())
                .build();

        cartItemRepository.save(cartItem);

        return getCart(cartId);
    }

    @Override
    public void deleteCart(Long cartId) {
        cartItemRepository.deleteAllByCartId(cartId);
        cartRepository.deleteById(cartId);
    }
}
