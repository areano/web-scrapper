package com.areano.sainsbury;

import com.areano.sainsbury.scrapper.ProductCollectionScrapper;
import com.areano.sainsbury.scrapper.providers.DocumentProvider;
import com.areano.sainsbury.serializers.ProductCollectionJsonSerializer;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class App {

    private static final String PRODUCTS_URI = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

    public static void main(String[] args) {
        DocumentProvider dp = new JsoupDocumentProvider();
        ProductCollectionScrapper pcs = new ProductCollectionScrapper(dp);
        ProductCollectionJsonSerializer serializer = new ProductCollectionJsonSerializer();

        try {
            Document document = dp.getDocument(PRODUCTS_URI);
            ProductCollection pc = pcs.scrap(document);
            String results = serializer.print(pc);
            System.out.println(results);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
