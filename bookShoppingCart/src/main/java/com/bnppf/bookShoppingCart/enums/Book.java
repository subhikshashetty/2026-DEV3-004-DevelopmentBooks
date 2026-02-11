package com.bnppf.bookShoppingCart.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Book {
    CLEAN_CODE(1, "Clean Code","Robert Martin", 2008, 50.0),
    CLEAN_CODER(2, "The Clean Coder","Robert Martin", 2011, 50.0),
    CLEAN_ARCHITECTURE(3, "Clean Architecture","Robert Martin", 2017, 50.0),
    TEST_DRIVEN_DEVELOPMENT_BY_EXAMPLE(4, "Test Driven Development by Example","Kent Beck", 2003, 50.0),
    WORKING_EFFECTIVELY_WITH_LEGACY_CODE(5, "Working Effectively With Legacy Code","Michael C. Feathers", 2004, 50.0);

    private final int id;
    private final String title;
    private final String author;
    private final int year;
    private final double price;
}
