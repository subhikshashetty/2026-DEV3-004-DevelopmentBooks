package com.bnppf.bookShoppingCart.util;

public class TwentyPercentDiscount implements Discount {

    @Override
    public double applyDiscount(double actualPrice) {
        return actualPrice * 0.20;
    }

    @Override
    public boolean isApplicable(int groupSize) {
        return groupSize == 4;
    }
}
