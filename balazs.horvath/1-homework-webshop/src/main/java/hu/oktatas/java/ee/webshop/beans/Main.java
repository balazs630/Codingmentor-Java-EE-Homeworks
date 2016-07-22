package hu.oktatas.java.ee.webshop.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;

public class Main {

    // private static final Logger LOG = Logger.getLogger(Main.class.getName());
    private Main() {
    }

    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        List<UserDTO> userList
                = Arrays.asList(mapper.readValue("json/users.json", UserDTO.class));

        List<MobileType> mobileList
                = Arrays.asList(mapper.readValue("json/mobiles.json", MobileType.class));
    }
}
