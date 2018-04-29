package com.areano.sainsbury.scrapper.providers;

import org.jsoup.nodes.Document;

public interface DocumentProvider {
    Document getDocument(String uri);
}
