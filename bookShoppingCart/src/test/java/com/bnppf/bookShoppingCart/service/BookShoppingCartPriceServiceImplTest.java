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

    @Test
    void getBookCartPriceForSingleBook() {
        double result = bookShoppingCartPriceService.calculateTotalPrice(List.of(Book.CLEAN_CODE));
        assertEquals(50.0, result);
    }

    @Test
    void getBookCartPriceForTwoBooksWithDiscount5Percent() {
        Double result = bookShoppingCartPriceService.calculateTotalPrice(List.of(Book.CLEAN_CODE, Book.CLEAN_CODER));
        assertEquals(95.0, result);
    }
    @Test
    void getBookCartPriceForThreeBooksWithDiscount10Percent() {
        Double result = bookShoppingCartPriceService.calculateTotalPrice(List.of(Book.CLEAN_CODE, Book.CLEAN_CODER, Book.CLEAN_ARCHITECTURE));
        assertEquals(135.0, result);
    }
    @Test
    void getBookCartPriceForFourBooksWithDiscount20Percent() {
        Double result = bookShoppingCartPriceService.calculateTotalPrice(List.of(Book.CLEAN_CODE, Book.CLEAN_CODER, Book.CLEAN_ARCHITECTURE, Book.TEST_DRIVEN_DEVELOPMENT_BY_EXAMPLE));
        assertEquals(160.0, result);
    }
    @Test
    void getBookCartPriceForAllBooksWithDiscount25Percent() {
        Double result = bookShoppingCartPriceService.calculateTotalPrice(List.of(Book.CLEAN_CODE, Book.CLEAN_CODER, Book.CLEAN_ARCHITECTURE, Book.TEST_DRIVEN_DEVELOPMENT_BY_EXAMPLE,Book.WORKING_EFFECTIVELY_WITH_LEGACY_CODE));
        assertEquals(187.5, result);
    }

    @Test
    void getBookCartPriceFor2MultipleSetDiscount() {
        Double result = bookShoppingCartPriceService.calculateTotalPrice(List.of(Book.CLEAN_CODE,Book.CLEAN_CODE,Book.CLEAN_CODER, Book.CLEAN_CODER));
        assertEquals(190.0, result);
    }

    @Test
    void getBookCartPriceForMultipleSetDiscount() {
        Double result = bookShoppingCartPriceService.calculateTotalPrice(List.of(Book.CLEAN_CODE,Book.CLEAN_CODE,Book.CLEAN_CODER, Book.CLEAN_CODER, Book.CLEAN_ARCHITECTURE, Book.CLEAN_ARCHITECTURE,  Book.TEST_DRIVEN_DEVELOPMENT_BY_EXAMPLE, Book.WORKING_EFFECTIVELY_WITH_LEGACY_CODE));
        assertEquals(320.0, result);
    }

}
