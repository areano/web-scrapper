package com.areano.sainsbury.scrapper;

import org.jsoup.nodes.Element;

public interface ElementScrapper<T> {
    T scrap(Element element);
}
