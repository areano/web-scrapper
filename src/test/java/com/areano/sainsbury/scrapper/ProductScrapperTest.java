package com.areano.sainsbury.scrapper;

import com.areano.sainsbury.Product;
import com.areano.sainsbury.scrapper.providers.CustomDocumentProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class ProductScrapperTest {
    private ElementScrapper<Product> scrapper;
    private Document document;

    @Before
    public void setUp() throws IOException {
        File input = new File("src/test/resources/products-page.html");
        document = Jsoup.parse(input, StandardCharsets.UTF_8.name(), "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries");
        scrapper = new ProductScrapper(new CustomDocumentProvider());
    }

    @Test
    public void scrapProductFromDocument() {
        Element element = document.select(".gridItem").first();
        Product p = scrapper.scrap(element);
        assertEquals("Sainsbury's Strawberries 400g", p.getTitle());
        assertEquals(Integer.valueOf(33), p.getCalories());
        assertEquals(1.75f, p.getPrice(), 0);
        assertEquals("by Sainsbury's strawberries", p.getDescription());
    }
}