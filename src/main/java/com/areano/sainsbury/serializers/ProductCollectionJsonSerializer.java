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

public class ProductCollectionJsonSerializer {

    private ObjectMapper mapper = createObjectMapper();

    public JsonNode serialize(ProductCollection productCollection) {
        return mapper.convertValue(productCollection, JsonNode.class);
    }

    public String print(ProductCollection productCollection) throws JsonProcessingException {
        return mapper.writeValueAsString(productCollection);
    }

    private ObjectMapper createObjectMapper() {
        ObjectMapper om = new ObjectMapper();

        SimpleModule sm = new SimpleModule();
        sm.addSerializer(Product.class, new ProductSerializer());
        sm.addSerializer(ProductCollection.class, new CollectionSerializer());

        om.registerModule(sm);
        om.enable(SerializationFeature.INDENT_OUTPUT);
        return om;
    }

    class ProductSerializer extends StdSerializer<Product> {

        ProductSerializer() {
            super(Product.class);
        }

        @Override
        public void serialize(Product product, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("title", product.getTitle());
            jsonGenerator.writeNumberField("kcal_per_100g", product.getCalories());
            jsonGenerator.writeNumberField("unit_price", product.getPrice());
            jsonGenerator.writeStringField("description", product.getDescription());
            jsonGenerator.writeEndObject();
        }
    }

    class CollectionSerializer extends StdSerializer<ProductCollection> {

        CollectionSerializer() {
            super(ProductCollection.class);
        }

        @Override
        public void serialize(ProductCollection productCollection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("results", productCollection.getProducts());
            jsonGenerator.writeNumberField("total", productCollection.getTotal());
            jsonGenerator.writeEndObject();
        }
    }
}
