
package com.upinderawat.shoppingcart.user;

import com.upinderawat.shoppingcart.cart.Cart;

public class User{
    private String userid;
    private String name;
    private Cart cart;

    public User(String userid, String name, Cart cart) {
        this.userid = userid;
        this.name = name;
        this.cart = cart;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}