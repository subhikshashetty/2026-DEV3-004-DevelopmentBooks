package com.bnppf.bookShoppingCart.service.impl;

import com.bnppf.bookShoppingCart.enums.Book;
import com.bnppf.bookShoppingCart.service.BookShoppingCartPriceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookShoppingCartPriceServiceImpl implements BookShoppingCartPriceService {
    private final double BASE_BOOK_PRICE = 50.0;
    @Override
    public double calculateTotalPrice(List<Book> books) {
        double totalPrice;
        if (books == null || books.isEmpty()) {
            return 0.0;
        }
        if (books.size() == 1) {
            return BASE_BOOK_PRICE;
        } else {
            totalPrice = calculateDiscountPrice(books);
        }
        return totalPrice;
    }

    private double getPriceForEachBook(Book book) {
       return BASE_BOOK_PRICE;
    }

    private double calculateDiscountPrice(List<Book> books) {
        return switch (books.size()) {
            case 5 -> books.size() * BASE_BOOK_PRICE * (1 - 0.25);
            case 4 -> books.size() * BASE_BOOK_PRICE * (1 - 0.20);
            case 3 -> books.size() * BASE_BOOK_PRICE * (1 - 0.10);
            case 2 -> books.size() * BASE_BOOK_PRICE * (1 - 0.05);
            case 1 -> BASE_BOOK_PRICE;
            default -> 0;
        };
    }
}
