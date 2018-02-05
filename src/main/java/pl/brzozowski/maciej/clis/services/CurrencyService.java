package pl.brzozowski.maciej.clis.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brzozowski.maciej.clis.utilities.JsonCurrencyParser;

import java.io.IOException;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyService {

    @Autowired
    private Logger logger;
    private final String URL = "https://api.fixer.io/latest?base=";

    private OkHttpClient client = new OkHttpClient();
    private JsonCurrencyParser jsonCurrencyParser = new JsonCurrencyParser();

    public double getCurrencyRate(Currency currencyIn, Currency currencyOut) {
        Response response;
        double currencyRate = 0;
        try {
            Request request = new Request.Builder()
                    .url(URL.concat(currencyIn.toString()))
                    .get()
                    .addHeader("cache-control", "no-cache")
                    .build();

            response = client.newCall(request)
                    .execute();

            if (response.code() >= 300) {
                throw new IOException(response.toString());
            }
            currencyRate = jsonCurrencyParser.jsonParserRequestOut(response.body().string(), currencyOut);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return currencyRate;
    }


}
