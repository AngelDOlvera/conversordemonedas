package coins;

public class Coin {
    private String originCoin;
    private String destinyCoin;
    private double amount;

    public Coin(String originCoin, String destinyCoin, double amount) {this.originCoin = originCoin; this.destinyCoin = destinyCoin; this.amount = amount;
    }

    public String getOriginCoin() {
        return originCoin;
    }

    public void setOriginCoin(String originCoin) {
        this.originCoin = originCoin;
    }

    public String getDestinyCoin() {
        return destinyCoin;
    }

    public void setDestinyCoin(String destinyCoin) {
        this.destinyCoin = destinyCoin;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    private String originCrypto;
    private String destinyCryptoCoin;
    private double cryptoCurrencyAmount;

    public String getOriginCrypto() {return originCrypto;
    }

    public void setOriginCrypto(String originCrypto) {this.originCrypto = originCrypto;
    }

    public String getDestinyCryptoCoin() {return destinyCryptoCoin;
    }

    public void setDestinyCryptoCoin(String destinyCryptoCoin) {this.destinyCryptoCoin = destinyCryptoCoin;
    }

    public double getCryptoCurrencyAmount() {return cryptoCurrencyAmount;
    }

    public void setCryptoCurrencyAmount(double cryptoCurrency) {this.cryptoCurrencyAmount = cryptoCurrency;
    }
}
