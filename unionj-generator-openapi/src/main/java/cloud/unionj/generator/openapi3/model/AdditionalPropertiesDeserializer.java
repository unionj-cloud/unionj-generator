package cloud.unionj.generator.openapi3.model;

import cloud.unionj.generator.openapi3.model.Schema;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import java.io.IOException;

public class AdditionalPropertiesDeserializer extends StdDeserializer<Schema> {

    public AdditionalPropertiesDeserializer() {
        this(null);
    }

    public AdditionalPropertiesDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Schema deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        ObjectCodec codec = jp.getCodec();
        JsonNode node = codec.readTree(jp);
        if (node.isBoolean()) {
            return null;
        }
        Schema schema = codec.treeToValue(node, Schema.class);
        return schema;
    }
}