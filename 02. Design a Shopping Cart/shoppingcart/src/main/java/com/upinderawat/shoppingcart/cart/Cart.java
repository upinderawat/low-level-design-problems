package com.upinderawat.shoppingcart.cart;

import com.upinderawat.shoppingcart.cartitem.CartItem;
import com.upinderawat.shoppingcart.product.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Cart {
    private String cardId;
    private Map<String, CartItem> cartItems;

    public Cart() {
        this.cardId = UUID.randomUUID().toString();
        this.cartItems = new HashMap<>();
    }
    public boolean addCartItemToCart(Product product, int quantity){
        if(product == null || quantity <= 0){
            return false;//TODO: throw illegal argument exception
        }
        if(this.cartItems.containsKey(product.getProductId())){
            cartItems.get(product.getProductId()).increaseQuantity(quantity);
        }
        else{
            cartItems.put(product.getProductId(), new CartItem(product, quantity));
        }
        return true;
    }
    public int removeCartItemFromCart(Product product, int quantity){
        if(cartItems.containsKey(product.getProductId())){
            CartItem cartItem = cartItems.get(product.getProductId());
            cartItem.decreaseQuantity(quantity);
            return cartItem.getQuantity();
        }
        else{
            return -1;//TODO: throw illegal argument exception
        }
    }
    public double calculateCost(){
        double totalCost = 0;
        for(CartItem cartItem : cartItems.values()){
            totalCost += cartItem.calculatePrice();
        }
        return totalCost;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<String, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cart ID: ").append(cardId).append("\n");
        sb.append("Cart Items:\n");
        for (Map.Entry<String, CartItem> entry : cartItems.entrySet()) {
            sb.append("Product ID: ").append(entry.getKey()).append(", Quantity: ").append(entry.getValue().getQuantity()).append("\n");
        }
        return sb.toString();
    }
}
