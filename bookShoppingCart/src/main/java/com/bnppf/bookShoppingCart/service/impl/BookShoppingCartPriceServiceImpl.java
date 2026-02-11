package com.bnppf.bookShoppingCart.service.impl;

import com.bnppf.bookShoppingCart.enums.Book;
import com.bnppf.bookShoppingCart.service.BookShoppingCartPriceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            Map<Book, Integer> bookCountsByTitle = new HashMap<>();
            List<List<Book>> groupedBooks = new ArrayList<>();
            for (Book book : books) {
                bookCountsByTitle.put(book, bookCountsByTitle.getOrDefault(book, 0) + 1);
            }
            while (!bookCountsByTitle.isEmpty()) {
                List<Book> group = new ArrayList<>();
                for (Book books1 : new ArrayList<>(bookCountsByTitle.keySet())) {
                    group.add(books1);
                    decrementOrRemove(books1, bookCountsByTitle);
                }
                groupedBooks.add(group);
            }
            totalPrice =  groupedBooks.stream()
                    .mapToDouble(this::calculateDiscountPrice)
                    .sum();
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

    private static void decrementOrRemove(Book title, Map<Book, Integer> map) {
        map.computeIfPresent(title, (k, v) -> v - 1);
        if (map.get(title) != null && map.get(title) <= 0) {
            map.remove(title);
        }
    }
}
