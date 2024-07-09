package coins;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoConversionData {

    @JsonAlias("usd")
    private Double usd;

    @JsonAlias("mxn")
    private Double mxn;

    @JsonAlias("eur")
    private Double eur;

    public Double getUsd() {
        return usd;
    }

    public Double getMxn() {
        return mxn;
    }

    public Double getEur() {
        return eur;
    }

    public Double getExchangeCryptoCoin(String currency) {
        switch (currency.toLowerCase()) {
            case "usd":
                return usd;
            case "mxn":
                return mxn;
            case "eur":
                return eur;
            default:
                return null;
        }
    }
}
