package com.upinderawat.shoppingcart.cartitem;

import com.upinderawat.shoppingcart.product.Product;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }
    public boolean decreaseQuantity(int quantity) {
        if(this.quantity >= quantity){
            this.quantity -= quantity;
            return true;
        }
        else{
            return false;
        }
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double calculatePrice(){
        return this.product.getPrice() * this.quantity;
    }
}
