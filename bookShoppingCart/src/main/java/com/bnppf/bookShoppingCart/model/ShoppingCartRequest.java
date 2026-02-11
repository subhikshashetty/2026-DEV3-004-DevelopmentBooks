package com.bnppf.bookShoppingCart.model;

import com.bnppf.bookShoppingCart.enums.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShoppingCartRequest {
    public List<Book> books;
}
