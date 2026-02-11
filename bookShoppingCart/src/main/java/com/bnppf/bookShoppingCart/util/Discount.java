package com.bnppf.bookShoppingCart.util;

public interface Discount {
    double applyDiscount(double actualPrice);
    boolean isApplicable(int groupSize);
}
