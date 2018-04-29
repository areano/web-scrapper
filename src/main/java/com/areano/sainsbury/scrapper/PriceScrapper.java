package com.areano.sainsbury.scrapper;

import org.jsoup.nodes.Element;

class PriceScrapper implements ElementScrapper<Float> {
    @Override
    public Float scrap(Element element) {
        if (element == null) {
            return null;
        }
        String text = element.select(".pricePerUnit").text();
        text = text.replace("£", "");
        text = text.replace("/unit", "");
        return Float.valueOf(text);
    }
}
