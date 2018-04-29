package com.areano.sainsbury.scrapper.providers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CustomDocumentProvider implements DocumentProvider {

    private Document document = null;

    public CustomDocumentProvider() {
        try {
            document = Jsoup.parse(new File("src/test/resources/product-details-page.html"), StandardCharsets.UTF_8.name(), "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Document getDocument(String uri) {
        return document;
    }
}
