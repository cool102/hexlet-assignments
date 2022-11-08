package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@Getter
@AllArgsConstructor
@ToString
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
            return  json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static Car unserialize(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, Car.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // END
}
