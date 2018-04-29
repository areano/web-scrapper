package com.areano.sainsbury.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class DescriptionScrapperTest {
    private ElementScrapper<String> scrapper;
    private Document document;

    @Before
    public void setUp() throws IOException {
        File input = new File("src/test/resources/product-details-page.html");
        document = Jsoup.parse(input, StandardCharsets.UTF_8.name(), "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries");
        scrapper = new DescriptionScrapper();
    }

    @Test
    public void scrapPriceFromDocument() {
        assertEquals("by Sainsbury's strawberries", scrapper.scrap(document));
    }

    @Test
    public void returnNullIfElementIsNull() {
        assertEquals(null, scrapper.scrap(null));
    }
}