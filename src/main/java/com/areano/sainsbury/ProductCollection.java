package com.areano.sainsbury;

import java.util.ArrayList;

public class ProductCollection {
    private ArrayList<Product> products = new ArrayList<>();

    public void add(Product product) {
        products.add(product);
    }

    public int size() {
        return products.size();
    }

    public float getTotal() {
        return (float) products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public Product[] getProducts() {
        return products.toArray(new Product[products.size()]);
    }
}
