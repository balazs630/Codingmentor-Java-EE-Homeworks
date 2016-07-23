package hu.oktatas.java.ee.webshop.beans;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

public class Main {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Main() {
    }

    public static void main(String[] args) throws IOException {

        List<UserDTO> users = MAPPER.readValue(
                Main.class.getClassLoader().getResource("json/users.json"),
                new TypeReference<List<UserDTO>>() {
        });

        List<MobileType> mobiles = MAPPER.readValue(
                Main.class.getClassLoader().getResource("json/mobiles.json"),
                new TypeReference<List<MobileType>>() {
        });

    }
}
