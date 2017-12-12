package pl.brzozowski.maciej.clis.utilities;

import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import pl.brzozowski.maciej.clis.services.Curency;

import java.io.IOException;

@Component
@NoArgsConstructor
public class JsonParser {

    public double jsonParserRequestOut(String response, Curency curency) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
        jsonObject = (JSONObject)jsonObject.get("rates");
        return (Double) jsonObject.getOrDefault(curency.name().toUpperCase(), 0D);
    }
}
