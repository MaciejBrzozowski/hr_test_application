package pl.brzozowski.maciej.clis.utilities;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.brzozowski.maciej.clis.services.Curency;

import java.io.IOException;

public class JsonParser {

    public double jsonParserRequestOut(String response, Curency curency) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
        jsonObject = (JSONObject)jsonObject.get("rates");
        return (Double) jsonObject.getOrDefault(curency.name(), 0D);
    }
}
