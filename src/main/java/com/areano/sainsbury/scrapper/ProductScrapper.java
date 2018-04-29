package com.areano.sainsbury.scrapper;

import com.areano.sainsbury.Product;
import com.areano.sainsbury.scrapper.providers.DocumentProvider;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

class ProductScrapper implements ElementScrapper<Product> {

    private static TitleScrapper titleScrapper = new TitleScrapper();
    private static UriScrapper uriScrapper = new UriScrapper();
    private static CaloriesScrapper caloriesScrapper = new CaloriesScrapper();
    private static PriceScrapper priceScrapper = new PriceScrapper();
    private static DescriptionScrapper descriptionScrapper = new DescriptionScrapper();
    private DocumentProvider documentProvider;

    ProductScrapper(DocumentProvider documentProvider) {
        this.documentProvider = documentProvider;
    }

    @Override
    public Product scrap(Element element) {
        Product p = new Product();

        p.setTitle(titleScrapper.scrap(element));

        Document document = documentProvider.getDocument(uriScrapper.scrap(element));

        p.setCalories(caloriesScrapper.scrap(document));
        p.setPrice(priceScrapper.scrap(document));
        p.setDescription(descriptionScrapper.scrap(document));

        return p;
    }

}
