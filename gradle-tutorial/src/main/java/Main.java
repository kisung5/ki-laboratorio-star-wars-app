import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {

//    public static void main(String[] args) {
//        System.out.println("Hello, World!");
//    }

    private static ObjectMapper mapper = new ObjectMapper();
    private static ApiResponse<Character> parsedResponse = new ApiResponse<>();

    public static void main(String[] args) throws IOException, IOException {

        Logger logger = LoggerFactory.getLogger(Main.class);

        GuiTable gui = new GuiTable();
        List<Character> characters;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet("https://swapi.dev/api/people");
            CloseableHttpResponse response = httpClient.execute(request);
            try {
                logger.debug(String.valueOf(response.getProtocolVersion()));              // HTTP/1.1
                logger.debug(String.valueOf(response.getStatusLine().getStatusCode()));   // 200
                logger.debug(response.getStatusLine().getReasonPhrase()); // OK
                logger.debug(response.getStatusLine().toString());        // HTTP/1.1 200 OK

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    var result = EntityUtils.toString(entity);
                     parsedResponse = mapper.readValue(
                            result, mapper.getTypeFactory().constructParametricType(
                                    ApiResponse.class, Character.class));
                }
            } catch (Exception exception) {
                logger.error(exception.getMessage());
            } finally {
                response.close();
            }
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
        finally {
            httpClient.close();
        }

        characters = parsedResponse.getResults();
        List<String[]> characterlist = new ArrayList<String[]>();

        for (Character character:characters) {
            List<String> characterString = new ArrayList<String>();
            characterString.add(character.getName());
            characterString.add(character.getHeight());
            characterString.add(character.getBirth_year());
            String[] stringChar = characterString.toArray( new String[] {} ); //passing the toArray method
            characterlist.add(stringChar);
        }

        gui.addTable(characterlist.toArray( new String[][]{} ));

        gui.show();

    }
}
