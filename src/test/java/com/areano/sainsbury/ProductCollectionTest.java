package com.areano.sainsbury;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ProductCollectionTest {

    private ProductCollection productCollection = new ProductCollection();

    @Test
    public void addProduct() {
        productCollection.add(createProduct());
        assertEquals(1, productCollection.size());
    }

    @Test
    public void addProductShouldUpdateTotal() {
        Product p = createProduct();
        productCollection.add(p);
        assertEquals(12.3f, productCollection.total(), 0);
    }

    @Test
    public void totalIsZeroIfThereAreNoProducts() {
        assertEquals(0, productCollection.total(), 0);
    }

    @Test
    public void getListOfProducts() {
        Product p = createProduct();
        productCollection.add(p);
        assertArrayEquals(new Product[]{p}, productCollection.getProducts());
    }

    private Product createProduct() {
        Product p = new Product();
        p.setPrice(12.3f);
        return p;
    }
}