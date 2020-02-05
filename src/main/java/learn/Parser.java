package learn;

import java.io.IOException;

import com.fasterxml.jackson.databind.*;

public class Parser {

    //Create ObjectMapper instance
    private ObjectMapper objMapper = new ObjectMapper();

    public String getRegisterVPPUserUrl(byte[] jsonData) throws IOException {

        JsonNode rootnode = objMapper.readTree(jsonData);

        //read JSON like DOM Parser
        JsonNode tempnode = rootnode.path("registerUserSrvUrl");
        
        return tempnode.asText();

    }

    public RegisterUser getUser(byte[] jsonData) throws IOException {

        JsonNode rootnode = objMapper.readTree(jsonData);
        
        //read JSON like DOM Parser
        JsonNode tempnode = rootnode.path("user");

        RegisterUser registerUser = objMapper.readValue(tempnode.toString(), RegisterUser.class);

        return registerUser;

    }

}