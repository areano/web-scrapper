package com.areano.sainsbury.scrapper;

import com.areano.sainsbury.Product;
import com.areano.sainsbury.scrapper.providers.DocumentProvider;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.stream.Collectors;

public class ProductCollectionScrapper implements ElementScrapper<List<Product>> {

    private final ProductScrapper productScrapper;

    ProductCollectionScrapper(DocumentProvider documentProvider) {
        productScrapper = new ProductScrapper(documentProvider);
    }

    @Override
    public List<Product> scrap(Element element) {
        return element.select(".gridItem")
                .stream()
                .map(productScrapper::scrap)
                .collect(Collectors.toList());
    }
}
