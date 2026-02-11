package com.bnppf.bookShoppingCart.service;

import com.bnppf.bookShoppingCart.enums.Book;
import com.bnppf.bookShoppingCart.service.impl.BookShoppingCartPriceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookShoppingCartPriceServiceImplTest {
    private BookShoppingCartPriceService bookShoppingCartPriceService;

    @BeforeEach
    void setUp() {
        bookShoppingCartPriceService = new BookShoppingCartPriceServiceImpl();
    }

    @Test
    void getBookEmptyCartPrice() {
        double result = bookShoppingCartPriceService.calculateTotalPrice(List.of());
        assertEquals(0.0, result);
    }
}
