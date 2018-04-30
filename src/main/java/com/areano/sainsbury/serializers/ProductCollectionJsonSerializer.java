package com.areano.sainsbury.serializers;

import com.areano.sainsbury.Product;
import com.areano.sainsbury.ProductCollection;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.NumberFormat;

public class ProductCollectionJsonSerializer {
    private final static ObjectMapper mapper = createObjectMapper();
    private final static NumberFormat currencyFormat = createPriceFormatWithTwoDecimalPlaces();

    JsonNode serialize(ProductCollection productCollection) {
        return mapper.convertValue(productCollection, JsonNode.class);
    }

    public String print(ProductCollection productCollection) throws JsonProcessingException {
        return mapper.writeValueAsString(productCollection);
    }

    private static ObjectMapper createObjectMapper() {
        SimpleModule sm = new SimpleModule()
                .addSerializer(Product.class, new ProductSerializer())
                .addSerializer(ProductCollection.class, new CollectionSerializer());

        return new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)
                .registerModule(sm);
    }

    static class ProductSerializer extends StdSerializer<Product> {
        ProductSerializer() {
            super(Product.class);
        }

        @Override
        public void serialize(Product product, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("title", product.getTitle());
            writeCalories(jsonGenerator, product);
            writePrice(product, jsonGenerator);
            jsonGenerator.writeStringField("description", product.getDescription());
            jsonGenerator.writeEndObject();
        }

        private void writeCalories(JsonGenerator jsonGenerator, Product product) throws IOException {
            if (product.getCalories() != null) {
                jsonGenerator.writeNumberField("kcal_per_100g", product.getCalories());
            }
        }

        private void writePrice(Product product, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("unit_price");
            jsonGenerator.writeRawValue(currencyFormat.format(product.getPrice()));
        }

    }

    static class CollectionSerializer extends StdSerializer<ProductCollection> {
        CollectionSerializer() {
            super(ProductCollection.class);
        }

        @Override
        public void serialize(ProductCollection productCollection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("results", productCollection.getProducts());
            writeTotal(productCollection, jsonGenerator);
            jsonGenerator.writeEndObject();
        }

        private void writeTotal(ProductCollection productCollection, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("total");
            jsonGenerator.writeRawValue(currencyFormat.format(productCollection.getTotal()));
        }
    }

    private static NumberFormat createPriceFormatWithTwoDecimalPlaces() {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        return nf;
    }
}
