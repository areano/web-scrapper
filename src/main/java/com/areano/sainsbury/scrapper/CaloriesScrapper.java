package com.areano.sainsbury.scrapper;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class CaloriesScrapper implements ElementScrapper<Integer> {
    @Override
    public Integer scrap(Element element) {
        if (element == null) {
            return null;
        }

        Elements tables = element.select(".nutritionTable");

        if (tables.size() <= 0) {
            return null;
        }

        String text = tables.get(0)
                .select("tr").get(2)
                .select("td").get(0)
                .text();

        text = text.replace("kcal", "");
        return Integer.valueOf(text);
    }
}
