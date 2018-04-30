package com.areano.sainsbury;

import com.areano.sainsbury.scrapper.providers.DocumentProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupDocumentProvider implements DocumentProvider {
    @Override
    public Document getDocument(String uri) {
        Document document = null;
        try {
            document = Jsoup.connect(uri).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}
