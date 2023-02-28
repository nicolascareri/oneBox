package com.example.onebox.controller;

import com.example.onebox.controller.dto.CartItemRequest;
import com.example.onebox.controller.dto.CartResponse;
import com.example.onebox.service.CartService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "Cart API", consumes = "application/json", produces = "application/json", tags = "Cart API")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<CartResponse> createCart() {
        return new ResponseEntity<>(cartService.createCart(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getCart(@PathVariable("id") Long id) {
        return new ResponseEntity<>(cartService.getCart(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartResponse> addItem(@PathVariable("id") Long cartId,
                                                @RequestBody @Valid CartItemRequest cartItemRequest) {

        return new ResponseEntity<>(cartService.addItem(cartId, cartItemRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable("id") Long cartId) {
        cartService.deleteCart(cartId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
