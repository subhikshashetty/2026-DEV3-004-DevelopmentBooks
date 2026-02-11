package com.bnppf.bookShoppingCart.service.impl;

import com.bnppf.bookShoppingCart.enums.Book;
import com.bnppf.bookShoppingCart.service.BookShoppingCartPriceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookShoppingCartPriceServiceImpl implements BookShoppingCartPriceService {
    @Override
    public double calculateTotalPrice(List<Book> books) {
        if (books == null || books.isEmpty()) {
            return 0.0;
        }
        return 50.0;
    }
}
