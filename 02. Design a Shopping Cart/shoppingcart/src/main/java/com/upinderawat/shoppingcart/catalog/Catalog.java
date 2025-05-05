package com.upinderawat.shoppingcart.catalog;

import com.upinderawat.shoppingcart.product.Product;

import java.util.HashMap;
import java.util.Map;

public class Catalog {
    private Map<String, Product> products;

    public Catalog() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product){
        this.products.put(product.getProductId(), product);
    }
    public Product getProduct(String productId){
        if(products.containsKey(productId)){
            return products.get(productId);
        }
        else{
            return null;
        }
    }
    public String getProductName(String productId){
        if(products.containsKey(productId)){
            return products.get(productId).getProductName();
        }
        else{
            return null;
        }
    }



}
