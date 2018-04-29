package com.areano.sainsbury.scrapper;

import org.jsoup.nodes.Element;

class DescriptionScrapper implements ElementScrapper<String> {
    @Override
    public String scrap(Element element) {
        if (element == null) {
            return null;
        }
        return element.select(".productText p")
                .first()
                .text();
    }
}
