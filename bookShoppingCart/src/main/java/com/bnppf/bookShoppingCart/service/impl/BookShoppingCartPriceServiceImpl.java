package com.bnppf.bookShoppingCart.service.impl;


import com.bnppf.bookShoppingCart.enums.Book;
import com.bnppf.bookShoppingCart.service.BookShoppingCartPriceService;
import com.bnppf.bookShoppingCart.util.BookCollectionAggregator;
import com.bnppf.bookShoppingCart.util.Discount;
import com.bnppf.bookShoppingCart.util.DiscountFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookShoppingCartPriceServiceImpl implements BookShoppingCartPriceService {

    private static final Logger logger = LoggerFactory.getLogger(BookShoppingCartPriceServiceImpl.class);

    private final double BASE_BOOK_PRICE = 50.0;

    private final List<Discount> discounts;

    public BookShoppingCartPriceServiceImpl() {
        this.discounts = DiscountFactory.getDiscounts();
    }


    @Override
    public double calculateTotalPrice(List<Book> books) {
        logger.trace("calculateTotalPrice() method start");
        double totalPrice;
        if (books == null || books.isEmpty()) {
            logger.debug("Book Shopping cart is empty");
            return 0.0;
        }
        logger.info("Calculating the total book price for listed books: {}", books);
        logger.debug("Book Shopping cart size is: {}", books.size());
        if (books.size() == 1) {
            totalPrice = BASE_BOOK_PRICE;
        } else {
            List<List<Book>> groupedBooks = BookCollectionAggregator.getBookCollection(books);
            totalPrice = groupedBooks.stream()
                    .mapToDouble(this::calculateDiscountPrice)
                    .sum();
        }
        logger.info("Total book price: {}", totalPrice);
        logger.trace("calculateTotalPrice() method end");
        return totalPrice;
    }

    private double calculateDiscountPrice(List<Book> books) {
        logger.trace("calculateDiscountPrice() method start");
        if (!books.isEmpty()) {
            double originalPrice = books.size() * BASE_BOOK_PRICE;
            double discountPrice = 0;
            for (Discount discount : discounts) {
                if (discount.isApplicable(books.size())) {
                    discountPrice = discount.applyDiscount(originalPrice);
                }
            }
            logger.info("Total discount on all books: {}", discountPrice);
            return originalPrice - discountPrice;
        }
        logger.trace("calculateDiscountPrice() method end");
        return 0;
    }
}
