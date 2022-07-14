package example.domain.test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class DoubleConvertor extends JsonDeserializer<Double> {

    private static final String CANNOT_CONVERT_VALUE_TO_DOUBLE = "Cannot convert value {} to double";

    @Override
    public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        try {
            return Double.valueOf(deserializationContext.getParser().getText());
        } catch (NumberFormatException | JsonProcessingException e) {
            log.debug(CANNOT_CONVERT_VALUE_TO_DOUBLE, deserializationContext.getParser().getText());
            return 0d;
        }
    }
}
