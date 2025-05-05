package com.upinderawat.shoppingcart;

import com.upinderawat.shoppingcart.cart.Cart;
import com.upinderawat.shoppingcart.catalog.Catalog;
import com.upinderawat.shoppingcart.product.Product;
import com.upinderawat.shoppingcart.user.User;

public class CartService {
    private Catalog catalog;
    public CartService(Catalog catalog) {
        this.catalog = catalog;
    }
    public void addProductToCart(User user, Product product, int quantity) {
        user.getCart().addCartItemToCart(product, quantity);
        System.out.println("Product " + product.getProductName() + " Quantity "+quantity+" added to cart for user " + user.getName());

    }
    public void removeFromCart(User user, Product product, int quantity) {
        int updatedQuantity = user.getCart().removeCartItemFromCart(product, quantity);
        if(updatedQuantity !=-1){
            System.out.println("Product " + product.getProductName() + " removed from cart for user " + user.getName());
            System.out.println("Product " + product.getProductName() + " current quantity for user " + updatedQuantity);
        }
        else{
            System.out.println("Product " + product.getProductName() + " not found in cart for user " + user.getName());
        }
    }
    public void viewCart(User user){
        Cart cart = user.getCart();
        for(String productId : cart.getCartItems().keySet()){
            System.out.println("Product Name: " + catalog.getProductName(productId)+" quantity: " + cart.getCartItems().get(productId).getQuantity());
        }
        System.out.println("User "+user.getName()+", \nYour total bill:\n"+cart.calculateCost());
//        System.out.println(user.getCart().toString());
    }
}
