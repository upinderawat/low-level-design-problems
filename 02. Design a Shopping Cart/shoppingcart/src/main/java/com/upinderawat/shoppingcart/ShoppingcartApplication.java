package com.upinderawat.shoppingcart;

import com.upinderawat.shoppingcart.cart.Cart;
import com.upinderawat.shoppingcart.catalog.Catalog;
import com.upinderawat.shoppingcart.product.Product;
import com.upinderawat.shoppingcart.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShoppingcartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingcartApplication.class, args);
		System.out.println("----Cart Service----");
		Catalog catalog = new Catalog();
		catalog.addProduct(new Product("1", "Biscuit", 10.0));
		catalog.addProduct(new Product("2", "Detergent", 245.0));
		catalog.addProduct(new Product("3", "Shampoo", 150.0));


		Cart upinderCart = new Cart();

		User user1 = new User("1", "Upinder", upinderCart);
		CartService cartService = new CartService(catalog);
		cartService.addProductToCart(user1, catalog.getProduct("1"), 2);
		cartService.addProductToCart(user1, catalog.getProduct("1"), 1);
		cartService.addProductToCart(user1, catalog.getProduct("1"), 2);

		cartService.viewCart(user1);

	}

}
