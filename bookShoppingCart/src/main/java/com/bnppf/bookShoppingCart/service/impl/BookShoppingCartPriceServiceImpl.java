package com.bnppf.bookShoppingCart.service.impl;

import com.bnppf.bookShoppingCart.enums.Book;
import com.bnppf.bookShoppingCart.service.BookShoppingCartPriceService;
import com.bnppf.bookShoppingCart.util.BookCollectionAggregator;
import com.bnppf.bookShoppingCart.util.Discount;
import com.bnppf.bookShoppingCart.util.DiscountFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookShoppingCartPriceServiceImpl implements BookShoppingCartPriceService {
    private final double BASE_BOOK_PRICE = 50.0;

    private final List<Discount> discounts;

    public BookShoppingCartPriceServiceImpl() {
        this.discounts = DiscountFactory.getDiscounts();
    }


    @Override
    public double calculateTotalPrice(List<Book> books) {
        double totalPrice;
        if (books == null || books.isEmpty()) {
            return 0.0;
        }
        if (books.size() == 1) {
            return BASE_BOOK_PRICE;
        } else {
            List<List<Book>> groupedBooks = BookCollectionAggregator.getBookCollection(books);
            totalPrice = groupedBooks.stream()
                    .mapToDouble(this::calculateDiscountPrice)
                    .sum();
        }
        return totalPrice;
    }

    private double calculateDiscountPrice(List<Book> books) {
        if (!books.isEmpty()) {
            double originalPrice = books.size() * BASE_BOOK_PRICE;
            double discountPrice = 0;
            for (Discount discount : discounts) {
                if (discount.isApplicable(books.size())) {
                    discountPrice = discount.applyDiscount(originalPrice);
                }
            }
            return originalPrice - discountPrice;
        }
        return 0;
    }
}
