package com.bnppf.bookShoppingCart.util;

public class TenPercentDiscount implements Discount {
    @Override
    public double applyDiscount(double actualPrice) {
        return actualPrice * 0.10;
    }

    @Override
    public boolean isApplicable(int groupSize) {
        return groupSize == 3;
    }
}
