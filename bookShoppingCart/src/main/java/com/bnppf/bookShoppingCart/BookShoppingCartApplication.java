package com.bnppf.bookShoppingCart;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Book Shopping Cart API",
				version = "1.0",
				description = "API documentation for calculating the total discounts for the book"
		)
)
@SpringBootApplication
public class BookShoppingCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookShoppingCartApplication.class, args);
	}

}
