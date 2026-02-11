package com.bnppf.bookShoppingCart.util;

public class TwentyFivePercentDiscount implements Discount {

    @Override
    public double applyDiscount(double actualPrice) {
        return actualPrice * 0.25;
    }

    @Override
    public boolean isApplicable(int groupSize) {
        return groupSize == 5;
    }
}
