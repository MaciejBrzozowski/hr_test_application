package pl.brzozowski.maciej.clis.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;

@NoArgsConstructor
@AllArgsConstructor
@Component
public class UnitConverter {


    private final String BASE_URL = "https://www.googleapis.com/customsearch/v1?key=${API_KEY}&cx=017576662512468239146:omuauf_lfve&q=";
    private OkHttpClient client = new OkHttpClient();
    private final String API_KEY = "AIzaSyBqO4qF0xeYpvroTNUw8jwvaAn6pFj8NFI";

    public String getConvertedUnit(String query) {
        Response response;
        String responseBody = "";
        try {
            Request request = new Request.Builder()
                    .url(BASE_URL.replace("${API_KEY}", API_KEY).concat(query))
                    .get()
                    .addHeader("cache-control", "no-cache")
                    .build();

            response = client.newCall(request)
                    .execute();

            if (response.code() >= 300) {
                throw new HTTPException(response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseBody;
    }

}
