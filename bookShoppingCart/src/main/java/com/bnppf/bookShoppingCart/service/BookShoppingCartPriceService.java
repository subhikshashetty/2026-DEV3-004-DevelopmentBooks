package com.bnppf.bookShoppingCart.service;

import com.bnppf.bookShoppingCart.enums.Book;

import java.util.List;

public interface BookShoppingCartPriceService {

    double calculateTotalPrice(List<Book> books);
}
