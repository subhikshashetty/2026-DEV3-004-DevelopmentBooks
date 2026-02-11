package com.bnppf.bookShoppingCart.util;

public class FivePercentDiscount implements Discount {

    @Override
    public double applyDiscount(double actualPrice) {
        return actualPrice * 0.05;
    }

    @Override
    public boolean isApplicable(int groupSize) {
        return groupSize == 2;
    }
}
