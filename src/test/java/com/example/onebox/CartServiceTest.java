package com.example.onebox;

import com.example.onebox.controller.dto.CartItemRequest;
import com.example.onebox.controller.dto.CartResponse;
import com.example.onebox.domain.Cart;
import com.example.onebox.domain.CartItem;
import com.example.onebox.repository.CartItemRepository;
import com.example.onebox.repository.CartRepository;
import com.example.onebox.service.impl.CartServiceImpl;
import com.example.onebox.utils.MessageUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    private MockedStatic<MessageUtils> mockStatic;

    @BeforeEach
    public void setup() {

        Cart cart = TestUtils.buildCartMock();
        List<CartItem> items = TestUtils.buildCartItems();

        mockStatic = Mockito.mockStatic(MessageUtils.class);

        Mockito.when(cartRepository.save(Mockito.any(Cart.class)))
                .thenReturn(cart);

        Mockito.when(cartRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(cart));

        Mockito.when(cartItemRepository.findByCartId(Mockito.anyLong()))
                .thenReturn(items);

        Mockito.when(cartRepository.existsById(Mockito.anyLong()))
                .thenReturn(true);

        Mockito.when(cartItemRepository.save(Mockito.any(CartItem.class)))
                .thenReturn(items.get(0));

        Mockito.doNothing().when(cartItemRepository).deleteAllByCartId(Mockito.anyLong());

        Mockito.clearInvocations(cartRepository);
    }

    @AfterEach
    public void afterEach() {
        mockStatic.close();
    }


    @DisplayName("Create Cart")
    @Test
    public void createCart() {
        CartResponse cart = cartService.createCart();

        Mockito.verify(cartRepository).save(Mockito.any(Cart.class));
        Mockito.verify(cartRepository).findById(Mockito.anyLong());
        Mockito.verify(cartItemRepository).findByCartId(Mockito.anyLong());

        Assertions.assertEquals(1L, cart.getCartId());
    }

    @DisplayName("Get Cart")
    @Test
    public void getCart() {
        CartResponse cart = cartService.getCart(1L);

        Mockito.verify(cartRepository).findById(Mockito.anyLong());
        Mockito.verify(cartItemRepository).findByCartId(Mockito.anyLong());

        Assertions.assertEquals(1L, cart.getCartId());
    }

    @DisplayName("Add item to cart")
    @Test
    public void addItem() {

        CartItemRequest cartItemRequest = TestUtils.buildCartItemRequest();

        CartResponse cart = cartService.addItem(1L, cartItemRequest);

        Mockito.verify(cartRepository).findById(Mockito.anyLong());
        Mockito.verify(cartItemRepository).findByCartId(Mockito.anyLong());
        Mockito.verify(cartItemRepository).save(Mockito.any(CartItem.class));

        Assertions.assertEquals(1L, cart.getCartId());
    }

    @DisplayName("Delete Cart")
    @Test
    public void deleteCart() {
        cartService.deleteCart(1L);

        Mockito.verify(cartRepository).existsById(Mockito.anyLong());
        Mockito.verify(cartItemRepository).deleteAllByCartId(Mockito.anyLong());
        Mockito.verify(cartRepository).deleteById(Mockito.anyLong());
    }
}
