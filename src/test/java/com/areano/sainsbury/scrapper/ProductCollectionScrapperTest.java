package com.areano.sainsbury.scrapper;

import com.areano.sainsbury.Product;
import com.areano.sainsbury.scrapper.providers.CustomDocumentProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductCollectionScrapperTest {

    private ProductCollectionScrapper scrapper;
    private Document document;

    @Before
    public void setUp() throws IOException {
        File input = new File("src/test/resources/products-page.html");
        document = Jsoup.parse(input, StandardCharsets.UTF_8.name(), "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries");
        scrapper = new ProductCollectionScrapper(new CustomDocumentProvider());
    }

    @Test
    public void scrapProductsFromDocuments() {
        List<Product> products = scrapper.scrap(document);
        assertEquals(17, products.size());
    }
}