package com.slipper.core.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.slipper.common.enums.DesensitizationEnum;

import java.io.IOException;
import java.util.Objects;

/**
 * @author gumingchen
 */
public class DesensitizationSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private DesensitizationEnum desensitizationEnum;

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(desensitizationEnum.serialize().apply(value));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        Desensitization annotation = property.getAnnotation(Desensitization.class);
        if (Objects.nonNull(annotation) && Objects.equals(String.class,
                property.getType().getRawClass())) {
            this.desensitizationEnum = annotation.value();
            return this;
        }
        return prov.findValueSerializer(property.getType(), property);
    }
}
