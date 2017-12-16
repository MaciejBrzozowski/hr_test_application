package pl.brzozowski.maciej.clis.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.logging.Logger;

import static java.util.Optional.ofNullable;

@NoArgsConstructor
@AllArgsConstructor
public class UnitConverter {

    private Request.Builder builder = new Request.Builder();
    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    private final String BASE_URL = "https://www.google.pl/search?biw=1535&bih=785&ei=Wn8zWvedBqKR6ASsrLIg&q=${query}&oq=1+mte&gs_l=psy-ab.3.1.0i10i19k1l6j0i22i10i30i19k1l3j0i22i30i19k1.19094045.19096576.0.19098784.5.5.0.0.0.0.83.366.5.5.0....0...1c.1.64.psy-ab..0.5.361...0j0i131k1j0i67k1.0.TILmMzUujd8";
    private OkHttpClient client = new OkHttpClient();

    public String getConvertedUnit(double quantity, String unitIn, String unitOut) {
        String query = quantity + "+" + unitIn + "+to+" + unitOut;
        logger.info(query);
        String responseBody = null;
        Response response;
        try {
            Request request = builder.url(BASE_URL.replace("${query}", query))
                    .get()
                    .addHeader("cache-control", "no-cache")
                    .build();

            response = client.newCall(request)
                    .execute();
            responseBody = response.body().string();
            logger.info(String.valueOf(response.code()));
            if (response.code() >= 300) {
                throw new HTTPException(response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info(responseBody);
        return ofNullable(responseBody).isPresent() ? responseBody : "";
    }

}
