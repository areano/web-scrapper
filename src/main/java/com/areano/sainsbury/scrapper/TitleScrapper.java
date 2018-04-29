package com.areano.sainsbury.scrapper;

import org.jsoup.nodes.Element;

class TitleScrapper implements ElementScrapper<String> {
    public String scrap(Element element) {
        if (element == null) {
            return null;
        }

        Element anchorElement = element.select(".productNameAndPromotions a").first();
        if (anchorElement == null) {
            return null;
        }
        return anchorElement.text();
    }
}

