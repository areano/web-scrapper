package com.areano.sainsbury.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class TitleScrapperTest {

    private ElementScrapper<String> scrapper;
    private Document document;

    @Before
    public void setUp() throws IOException {
        File input = new File("src/test/resources/products-page.html");
        document = Jsoup.parse(input, StandardCharsets.UTF_8.name(), "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries");
        scrapper = new TitleScrapper();
    }

    @Test
    public void scrapeTitleFromDocument() {
        Element element = document.select(".gridItem").first();
        assertEquals("Sainsbury's Strawberries 400g", scrapper.scrap(element));
    }

    @Test
    public void returnNullIfElementIsNull() {
        assertEquals(null, scrapper.scrap(null));
    }

    @Test
    public void returnNullIfValueIsNotPresent() {
        assertEquals(null, scrapper.scrap(document.getElementById("dietAndLifestyle")));
    }
}