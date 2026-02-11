package com.bnppf.bookShoppingCart.util;

import com.bnppf.bookShoppingCart.enums.Book;

import java.util.List;

public interface Discount {
    double applyDiscount(double actualPrice);
    boolean isApplicable(int groupSize);
}
