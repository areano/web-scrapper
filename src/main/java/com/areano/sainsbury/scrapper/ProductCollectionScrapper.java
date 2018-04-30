package com.areano.sainsbury.scrapper;

import com.areano.sainsbury.ProductCollection;
import com.areano.sainsbury.scrapper.providers.DocumentProvider;
import org.jsoup.nodes.Element;

public class ProductCollectionScrapper implements ElementScrapper<ProductCollection> {

    private final ProductScrapper productScrapper;

    public ProductCollectionScrapper(DocumentProvider documentProvider) {
        productScrapper = new ProductScrapper(documentProvider);
    }

    @Override
    public ProductCollection scrap(Element element) {
        ProductCollection pc = new ProductCollection();

        element.select(".gridItem")
                .stream()
                .map(productScrapper::scrap)
                .forEach(pc::add);

        return pc;
    }
}
