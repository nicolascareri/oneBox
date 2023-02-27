package com.example.onebox.repository;

import com.example.onebox.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCartId(@Param("cartId") Long cartId);

    void deleteAllByCartId(@Param("cartId") Long cartId);

}
