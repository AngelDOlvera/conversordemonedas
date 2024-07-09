package apis;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GeckoAPI {
    private String urlCryptoCoin;

    public GeckoAPI(String originCrypto, String destinyCryptoCoin, double cryptoAmount) {
        this.urlCryptoCoin = "https://api.coingecko.com/api/v3/simple/price?ids="+ originCrypto + "&vs_currencies=" + destinyCryptoCoin+ "TU_API_KEY";
    }

    public String getData() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlCryptoCoin))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
