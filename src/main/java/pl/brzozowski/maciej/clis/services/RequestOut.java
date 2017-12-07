package pl.brzozowski.maciej.clis.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
public class RequestOut {

    private final String URL = "https://api.fixer.io/latest?base=";
    OkHttpClient client = new OkHttpClient();

    public double getCurrencyRate(Curency curencyIn, Curency curencyOut) {
        Response response;
        double currenyRate = 0;
        try {
            Request request = new Request.Builder()
                    .url(URL.concat(curencyIn.toString()))
                    .get()
                    .addHeader("cache-control", "no-cache")
                    .build();

            response = client.newCall(request).execute();

            if (response.code() >= 300) {
                throw new HTTPException(response.code());
            }
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.body().string());
            System.out.println(jsonObject.toJSONString());
            currenyRate = ((HashMap<Curency, Double>) jsonObject.get("rates")).get(curencyOut);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return currenyRate;
    }


}
