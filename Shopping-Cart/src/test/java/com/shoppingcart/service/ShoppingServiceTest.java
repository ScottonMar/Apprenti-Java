package com.shoppingcart.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingServiceTest {

    private ShoppingServiceInterface service;

    @BeforeEach
    void setUp() {
        service = new ShoppingService(); // ✅ FIX: instantiate the class, not the interface
    }

    @Test
    void testAddItemAndCheckoutTotal() {
        service.addItem("Chips", 2.50, 2);
        double total = service.checkout();
        assertEquals(5.00, total, 0.01);
    }

    @Test
    void testRemoveItemPartially() {
        service.addItem("Juice", 3.00, 3);
        service.removeItem("Juice", 1);
        double total = service.checkout();
        assertEquals(6.00, total, 0.01); // 2 left
    }

    @Test
    void testRemoveItemCompletely() {
        service.addItem("Water", 1.00, 2);
        service.removeItem("Water", 2);
        double total = service.checkout();
        assertEquals(0.00, total, 0.01);
    }

    @Test
    void testEmptyCartAfterCheckout() {
        service.addItem("Eggs", 1.50, 1);
        service.checkout();
        double total = service.checkout();
        assertEquals(0.00, total, 0.01);
    }
}
