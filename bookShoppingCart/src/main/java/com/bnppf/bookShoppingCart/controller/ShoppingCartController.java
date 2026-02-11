package com.bnppf.bookShoppingCart.controller;

import com.bnppf.bookShoppingCart.model.ShoppingCartRequest;
import com.bnppf.bookShoppingCart.service.BookShoppingCartPriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookprice")
@Tag(name = "Book Price Calculator API", description = "Calculate total price with discounts for selected books")
public class ShoppingCartController {

    private final BookShoppingCartPriceService bookShoppingCartPriceService;


    public ShoppingCartController(BookShoppingCartPriceService bookShoppingCartPriceService) {
        this.bookShoppingCartPriceService = bookShoppingCartPriceService;
    }

    @PostMapping("total")
    @Operation(
            summary = "Calculate total price",
            description = "Calculates total price of books considering discounts for combinations")
    public ResponseEntity<Double> getTotalPrice(@RequestBody ShoppingCartRequest books) {
        Double totalPrice = this.bookShoppingCartPriceService.calculateTotalPrice(books.getBooks());
        return ResponseEntity.ok().body(totalPrice);
    }
}
