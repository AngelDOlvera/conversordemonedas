package apis;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeAPI {
    private String urlNationalCoin;

    public ExchangeAPI(String originCoin, String destinyCoin, double amount) {
        this.urlNationalCoin = "https://v6.exchangerate-api.com/v6/8b6fd0aebe78e7b32143d760/pair/" + originCoin + "/" + destinyCoin + "/" + amount;
    }

    public String getData() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlNationalCoin))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
