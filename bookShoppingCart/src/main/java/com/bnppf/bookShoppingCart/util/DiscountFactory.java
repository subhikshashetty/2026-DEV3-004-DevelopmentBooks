package com.bnppf.bookShoppingCart.util;

import java.util.List;

public class DiscountFactory {
    public static List<Discount> getDiscounts() {
        return List.of(new TwentyFivePercentDiscount(),
                new TwentyPercentDiscount(),
                new TenPercentDiscount(),
                new FivePercentDiscount());
    }
}
