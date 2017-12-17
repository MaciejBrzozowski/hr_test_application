package pl.brzozowski.maciej.clis.services;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.Properties;


public class HttpService {

    private OkHttpClient client = new OkHttpClient();

    public Response getSend(String url, Properties properties) {
        Response response = null;
        try {
            Request request = new Request.Builder()
                    .url(url.concat(""))
                    .get()
                    .addHeader("cache-control", "no-cache")
                    .build();

            response = client.newCall(request).execute();

            if (response.code() >= 300) {
                throw new HTTPException(response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
